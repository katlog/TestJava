package name.katlog.common.instant;

import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static org.junit.Assert.*;

/**
 * Created by fw on 2021/9/26
 *
 * 该类的实例是一个不可变对象，只提供了简单的日期，并不含当天的时间信息，也不附带任何与时区相关的信息。
 */
public class TestLocalDate {

    @Test
    public void of(){
        LocalDate date = LocalDate.of(2021, 1, 2);

        // 年 月 日
        assertEquals(2021, date.getYear());
        assertEquals(1, date.getMonth().getValue());
        assertEquals(1, date.getMonthValue());
        assertEquals(2, date.getDayOfMonth());

    }

    @Test
    public void now(){
        LocalDate now = LocalDate.now();
        System.out.println("now = " + now);
    }

    @Test
    public void get(){
        LocalDate date = LocalDate.of(2021, 1, 2);

        // 年 月 日
        assertEquals(2021, date.get(ChronoField.YEAR));
        assertEquals(1, date.get(ChronoField.MONTH_OF_YEAR));
        assertEquals(2, date.get(ChronoField.DAY_OF_MONTH));

    }


    /** 可生成 LocalDateTime */
    @Test
    public void atTime(){
        LocalDate date = LocalDate.parse("2021-01-02");
        LocalDateTime localDateTime = date.atTime(13, 45, 20);

        assertNotNull(localDateTime);
    }

    /** 通过with方法调整日期，不过生成的是副本，不会改变原日期
     *       dayOfWeekInMonth  创建一个新的日期，它的值为同一个月中每一周的第几天
     *       firstDayOfMonth  创建一个新的日期，它的值为当月的第一天
     *       firstDayOfNextMonth  创建一个新的日期，它的值为下月的第一天
     *       firstDayOfNextYear  创建一个新的日期，它的值为明年的第一天
     *       firstDayOfYear  创建一个新的日期，它的值为当年的第一天
     *       firstInMonth  创建一个新的日期，它的值为同一个月中，第一个符合星期几要求的值
     *       lastDayOfMonth  创建一个新的日期，它的值为当月的最后一天
     *       lastDayOfNextMonth  创建一个新的日期，它的值为下月的最后一天
     *       lastDayOfNextYear  创建一个新的日期，它的值为明年的最后一天
     *       lastDayOfYear  创建一个新的日期，它的值为今年的最后一天
     *       lastInMonth  创建一个新的日期，它的值为同一个月中，最后一个符合星期几要求的值
     *       next/previous 创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期
     *       nextOrSame/previousOrSame 创建一个新的日期，并将其值设定为日期调整后或者调整前，第一个符合指定星期几要求的日期，如果该日期已经符合要求，直接返回该对象
     * */
    @Test
    public void with_(){

        LocalDate date = LocalDate.of(2021, 1, 2);
        date = date.with(nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(date);
        date = date.with(lastDayOfMonth());
        System.out.println(date);

        date = date.with(new NextWorkingDay());
        System.out.println(date);
        date = date.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);
        date = date.with(new NextWorkingDay());
        System.out.println(date);

        date = date.with(nextOrSame(DayOfWeek.FRIDAY));
        System.out.println(date);
        date = date.with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println(date);
    }

    private static class NextWorkingDay implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            if (dow == DayOfWeek.SATURDAY) dayToAdd = 2;
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        }
    }

    //region 解析与格式化 parse format
    @Test
    public void parse(){
        LocalDate date = LocalDate.parse("2021-01-02");

        assertEquals(2021, date.get(ChronoField.YEAR));
        assertEquals(1, date.get(ChronoField.MONTH_OF_YEAR));
        assertEquals(2, date.get(ChronoField.DAY_OF_MONTH));

        LocalDate date1 = LocalDate.parse("2021-01-02",DateTimeFormatter.ISO_LOCAL_DATE);

        assertEquals(date, date1);
    }

    @Test
    public void format(){
        LocalDate date = LocalDate.parse("2021-01-02");
        assertEquals("2021-01-02", date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        assertEquals("2021-01-02", date.format(DateTimeFormatter.ISO_DATE));
        assertEquals("20210102", date.format(DateTimeFormatter.BASIC_ISO_DATE));
    }
    //endregion
}
