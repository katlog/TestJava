package lambdasinaction.appc;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Adapted from http://mail.openjdk.java.net/pipermail/lambda-dev/2013-November/011516.html
 */
public class StreamForker<T> {

    private final Stream<T> stream;
    private final Map<Object, Function<Stream<T>, ?>> forks = new HashMap<>();

    public StreamForker(Stream<T> stream) {
        this.stream = stream;
    }

    public StreamForker<T> fork(Object key, Function<Stream<T>, ?> f) {
        //用一个键对流上的函数进行索引
        forks.put(key, f);
        //返回this 从而保证多次流畅地调用fork方法
        return this;
    }

    public Results getResults() {
        ForkingStreamConsumer<T> consumer = build();
        try {
            stream.sequential().forEach(consumer);
        } finally {
            consumer.finish();
        }
        return consumer;
    }

    private ForkingStreamConsumer<T> build() {
        //创建由队列组成的列表，每一个队列对应一个操作
        List<BlockingQueue<T>> queues = new ArrayList<>();

        // 建立用于标识操作的键与包含操作结果的Future间的映射关系
        Map<Object, Future<?>> actions =
                forks.entrySet().stream().reduce(
                        new HashMap<Object, Future<?>>(),
                        (map, e) -> {
                            map.put(e.getKey(),
                                    getOperationResult(queues, e.getValue()));
                            return map;
                        },
                        (m1, m2) -> {
                            m1.putAll(m2);
                            return m1;
                        });

        return new ForkingStreamConsumer<>(queues, actions);
    }

    private Future<?> getOperationResult(List<BlockingQueue<T>> queues, Function<Stream<T>, ?> f) {
        BlockingQueue<T> queue = new LinkedBlockingQueue<>();
        // 创建一个队列，并将其添加到队列的列表中
        queues.add(queue);

        //创建一个Spliterator，遍历队列中的元素【这步如何实现的？】
        Spliterator<T> spliterator =new BlockingQueueSpliterator<>(queue);
        //创建一个流，将Spliterator作为数据源
        Stream<T> source = StreamSupport.stream(spliterator, false);
        //创建一个Future对象，以异步方式计算在流上执行特定函数的结果
        return CompletableFuture.supplyAsync( () -> f.apply(source) );
    }

    public static interface Results {
        public <R> R get(Object key);
    }

    /**  主要任务就是处理流中的元素，将它们分发到多个BlockingQueues中处理 */
    private static class ForkingStreamConsumer<T> implements Consumer<T>, Results {
        static final Object END_OF_STREAM = new Object();

        private final List<BlockingQueue<T>> queues;
        private final Map<Object, Future<?>> actions;

        ForkingStreamConsumer(List<BlockingQueue<T>> queues, Map<Object, Future<?>> actions) {
            this.queues = queues;
            this.actions = actions;
        }

        @Override
        public void accept(T t) {
            //将流中遍历的元素添加到所有的队列中
            queues.forEach(q -> q.add(t));
        }

        @Override
        public <R> R get(Object key) {
            try {
                //等待Future完成相关的计算，返回由特定键标识的处理结果
                return ((Future<R>) actions.get(key)).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        void finish() {
            //将最后一个元素添加到队列中，表明该流已经结束
            accept((T) END_OF_STREAM);
        }
    }

    private static class BlockingQueueSpliterator<T> implements Spliterator<T> {
        private final BlockingQueue<T> q;

        BlockingQueueSpliterator(BlockingQueue<T> q) {
            this.q = q;
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            T t;
            while (true) {
                try {
                    t = q.take();
                    break;
                }
                catch (InterruptedException e) {
                }
            }

            if (t != ForkingStreamConsumer.END_OF_STREAM) {
                action.accept(t);
                return true;
            }

            return false;
        }

        @Override
        public Spliterator<T> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return 0;
        }

        @Override
        public int characteristics() {
            return 0;
        }
    }
}
