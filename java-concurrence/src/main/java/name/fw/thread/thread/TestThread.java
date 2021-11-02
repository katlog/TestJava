package name.fw.thread.thread;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by fw on 2019/6/24
 */
public class TestThread {

    @Test
    public void sleep() throws InterruptedException {
        long before = System.currentTimeMillis();

        int i = 0;
        while (i++<10000) {
            Thread.sleep(1);
        }

        long after = System.currentTimeMillis();

        long diff = after - before;
        System.out.println("diff = " + diff);
    }

    /** 等待终止指定的线程
     *
     *  可让一个线程强制运行，线程强制运行期间其他线程无法运行，必须等此线程完成后才可继续执行。
     *
     *  线程A中调用另一个线程B的join，线程A将会等待线程B执行完毕后再执行
     * */
    @Test
    public void join() throws InterruptedException {
        Object lock = new Object();

        Thread a = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("a is running  " + i);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        a.start();

        Thread b = new Thread(() -> {

            // 先让main获取a锁
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (a) {
                System.err.println("b get lock...");

                for (int i = 0; i < 10; i++) {
                    if (i == 3) {
                        try {
                            System.err.println("b ready to release lock...");
                            // 线程执行完毕了exit过程有一个notifyAll操作
                            a.join();
                            System.err.println("b re get lock...");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    System.out.println(" b is running " + i);
                }
            }
        });
        b.start();

        synchronized (a) {
            System.err.println("main get lock...");
            a.wait();
            System.err.println("main re get lock... ");
        }
        System.out.println("over... ");
    }

    /**
     * 暂停当前正在执行的线程对象，并执行其他线程。
     *
     * 与join的区别 yield()让当前线程状态改为可运行状态，和其它线程一起竞争资源
     *
     * Thread.yield()方法作用是：
     *          暂停当前正在执行的线程对象，并执行其他线程。
     *          yield()应该做的是让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。
     *          因此，使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。
     *          但，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。
     *          它仅能使一个线程从运行状态转到可运行状态，而不是等待或阻塞状态
     *
     * 结论：yield()从未导致线程转到等待/睡眠/阻塞状态。在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果。
     *
     *  */
    @Test
    public void yeild1() throws InterruptedException {
        Thread producer = new Producer();
        Thread consumer = new Consumer();

        producer.setPriority(Thread.NORM_PRIORITY); //Min Priority
        consumer.setPriority(Thread.NORM_PRIORITY); //Max Priority

        producer.start();
        consumer.start();
    }
    static class Consumer extends Thread {
        @Override
        public void run()        {
            for (int i = 0; i < 15; i++)            {
                System.out.println("I am Consumer : Consumed Item " + i);
                Thread.yield();
            }
        }
    }

    static class Producer extends Thread {
        @Override
        public void run(){
            for (int i = 0; i < 5; i++){
                System.out.println("I am Producer : Produced Item " + i);
                Thread.yield();
            }
        }
    }

    /**
     * 获取全部线程栈
     *
     */
    @Test
    public void getAllStackTraces(){
        Thread.getAllStackTraces().forEach((thread, stackTraceElements) -> {
            if (thread == Thread.currentThread()) {

                for (StackTraceElement element : stackTraceElements) {
                    System.out.println("element = " + element);
                    int lineNumber = element.getLineNumber();
                }

            }
        });
    }

    /**
     * 5种线程状态 new runnable blocked wait terminate
     *  */
    @Test
    public void getState(){
        Thread.State state = Thread.currentThread().getState();
        assertEquals(Thread.State.RUNNABLE, state);

        for (Thread.State value : Thread.State.values()) {
            System.out.println("value = " + value);
        }
    }

    @Test
    public void active(){
        /** 获取的不是很靠谱 */
        int count = Thread.activeCount();
        assertEquals(1, count);
    }

}
