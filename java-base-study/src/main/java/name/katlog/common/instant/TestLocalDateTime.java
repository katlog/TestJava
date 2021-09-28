package name.katlog.common.instant;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

/**
 * 2021/9/26
 *
 * LocalDateTime，是LocalDate和LocalTime的合体。它同时表示了日期和时间，但不带有时区信息
 *
 * LocalDateTime和Instant是为不同的目的而设计的，一个是为了便于人阅读使用，另一个是为了便于机器处理
 */
public class TestLocalDateTime {

    LocalDateTime dateTime = LocalDateTime.of(2021, 1, 2, 13, 45, 20);

    @Test
    public void of(){

        // 年 月 日
        assertEquals(1, dateTime.getMonth().getValue());
        assertEquals(1, dateTime.getMonthValue());
        assertEquals(2, dateTime.getDayOfMonth());

        // 时 分 秒
        assertEquals(13, dateTime.getHour());
        assertEquals(45, dateTime.getMinute());
        assertEquals(20, dateTime.getSecond());
    }

    @Test
    public void toLocalDate(){
        LocalDate localDate = dateTime.toLocalDate();

        // 年 月 日
        assertEquals(1, localDate.getMonth().getValue());
        assertEquals(1, localDate.getMonthValue());
        assertEquals(2, localDate.getDayOfMonth());
    }

    @Test
    public void parse(){
        LocalDateTime parseDateTime = LocalDateTime.parse("2021-01-02T13:45:20");

        assertEquals(parseDateTime, dateTime);

        parseDateTime = LocalDateTime.parse("2021-01-02T13:45:20", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        assertEquals(parseDateTime, dateTime);

        parseDateTime = LocalDateTime.parse("2021 01 02 13:45:20", DateTimeFormatter.ofPattern("yyyy MM dd HH:mm:ss"));
        assertEquals(parseDateTime, dateTime);
    }
    
    @Test
    public void toLocalTime(){
        LocalTime localTime = dateTime.toLocalTime();

        // 时 分 秒
        assertEquals(13, localTime.getHour());
        assertEquals(45, localTime.getMinute());
        assertEquals(20, localTime.getSecond());
    }
}
