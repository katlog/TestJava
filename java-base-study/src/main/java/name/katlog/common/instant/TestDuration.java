package name.katlog.common.instant;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;

/**
 * 2021/9/26
 *  Duration类主要用于以秒和纳秒衡量时间的长短。
 */
public class TestDuration {

    /**
     * 要创建两个Temporal对象之间的duration。可通过Duration类的静态工厂方法between，
     * 能创建两个LocalTimes对象、两个LocalDateTimes对象，或两个Instant对象之间的duration
     *
     * Duration类主要用于以秒和纳秒衡量时间的长短，不能向between传递一个LocalDate对象做参数。
     *      注意，若要以年、月或日的方式对多个时间单位建模，可用Period类。
     * */
    @Test
    public void _static_between(){
        Duration d1 = Duration.between(LocalTime.of(1,1), LocalTime.of(1,3));
        System.out.println("d1 = " + d1);

        // Duration d2 = Duration.between(dateTime1, dateTime2);
        // Duration d3 = Duration.between(instant1, instant2);
    }
}
