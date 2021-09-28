package name.katlog.common.instant;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;

import static org.junit.Assert.*;

/**
 * 一天中的时间，比如13:45:20，可用LocalTime类表示
 * */
public class TestLocalTime {


    @Test
    public void of(){
        LocalTime time = LocalTime.of(13, 45, 20);

        // 时 分 秒
        assertEquals(13, time.getHour());
        assertEquals(45, time.getMinute());
        assertEquals(20, time.getSecond());

        //
        assertEquals(13, time.get(ChronoField.HOUR_OF_DAY));
        assertEquals(45, time.get(ChronoField.MINUTE_OF_HOUR));
        assertEquals(20, time.get(ChronoField.SECOND_OF_MINUTE));
    }
    @Test
    public void now() {
        LocalTime now1 = LocalTime.now();
        LocalTime now2 = LocalTime.now();

        assertEquals(now1, now2);
    }

    @Test
    public void parse(){
        LocalTime time = LocalTime.parse("13:45:20");

        // 时 分 秒
        assertEquals(13, time.getHour());
        assertEquals(45, time.getMinute());
        assertEquals(20, time.getSecond());
    }


    /** 可以生成 LocalDateTime */
    @Test
    public void atDate(){
        LocalTime time = LocalTime.parse("13:45:20");
        LocalDateTime localDateTime = time.atDate(LocalDate.of(2021, 1, 2));

        assertNotNull(localDateTime);
    }
}
