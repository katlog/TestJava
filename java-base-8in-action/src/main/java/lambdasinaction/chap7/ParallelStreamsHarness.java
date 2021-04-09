package lambdasinaction.chap7;

import java.util.concurrent.*;
import java.util.function.*;

public class ParallelStreamsHarness {

    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static void main(String[] args) {
        // iterate不能分割成独立执行的小块，普遍较慢
        System.out.println("Iterative Sum done in: " + measureSumPerf(ParallelStreams::iterativeSum, 10_000_000L) + " msecs");
        System.out.println("Sequential Sum done in: " + measureSumPerf(ParallelStreams::sequentialSum, 10_000_000L) + " msecs");
        System.out.println("Parallel forkJoinSum done in: " + measureSumPerf(ParallelStreams::parallelSum, 10_000_000L) + " msecs" );

        // 采用LongStream.rangeClosed,没拆箱操作、容易拆分成小块，普遍较快
        System.out.println("Range forkJoinSum done in: " + measureSumPerf(ParallelStreams::rangedSum, 10_000_000L) + " msecs");
        System.out.println("Parallel range forkJoinSum done in: " + measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000L) + " msecs" );

        //
        System.out.println("ForkJoin sum done in: " + measureSumPerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000L) + " msecs" );

        // 操作中有共享状态，parallel操作有数据竞争，没同步时，结果不正确
        System.out.println("SideEffect sum done in: " + measureSumPerf(ParallelStreams::sideEffectSum, 10_000_000L) + " msecs" );
        System.out.println("SideEffect prallel sum done in: " + measureSumPerf(ParallelStreams::sideEffectParallelSum, 10_000_000L) + " msecs" );
        System.out.println("remove SideEffect prallel sum done in: " + measureSumPerf(ParallelStreams::removeSideEffectParallelSum, 10_000_000L) + " msecs" );
    }

    public static <T, R> long measureSumPerf(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + result);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
}
