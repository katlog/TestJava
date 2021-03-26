package name.katlog.guava;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by fw on 2021/3/22
 */
public class TestStopWatch {

    @Test
    public void common(){
        Stopwatch stopwatch = Stopwatch.createStarted();

        // do something
        IntStream.range(1, 200).forEach(value -> {

        });

        long seconds = stopwatch.elapsed(TimeUnit.SECONDS);
        long mills = stopwatch.elapsed(TimeUnit.MILLISECONDS);
        long nanos = stopwatch.elapsed(TimeUnit.NANOSECONDS);

        System.out.println(seconds);
        System.out.println(mills);
        System.out.println(nanos);
    }
}
