package lambdasinaction.chap7;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.*;

public class ParallelStreams {

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(Long::sum).get();
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(Long::sum).getAsLong();
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(Long::sum).getAsLong();
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static class Accumulator {
        private long total = 0;
        /** 非原子操作 */
        public void add(long value) {
            total += value;
        }
    }

    public static class AccumulatorAtom {
        private AtomicLong total = new AtomicLong(0); ;

        public void add(long value) {
            total.addAndGet(value);
        }
    }

    public static long removeSideEffectParallelSum(long n) {
        AccumulatorAtom accumulator = new AccumulatorAtom();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total.get();
    }
}
