package name.katlog.common.instant;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.time.Instant.*;
import static java.time.Instant.ofEpochSecond;
import static org.junit.Assert.assertEquals;

/**
 *  人类习惯于以星期几、几号、几点、几分这样的方式理解日期和时间，但对于计算机而言并不容易理解
 *
 *  从计算机的角度来看，建模时间最自然的格式是表示一个持续时间段上某个点的单一大整型数。
 *
 *  即Instant类对时间建模的方式，以Unix元年时间（传统的设定为UTC时区1970年1月1日午夜时分）开始所经历的秒数进行计算
 * */
public class TestInstant {


    /**
     * 通过向静态工厂方法ofEpochSecond传递一个代表秒数的值创建实例。
     *
     * 静态工厂方法ofEpochSecond还有一个增强的重载版本，它接收第二个以纳秒为单位的参数值，对传入作为秒数的参数进行调整。
     * 重载的版本会调整纳秒参数，确保保存的纳秒分片在0到999 999999间。
     * */
    @Test
    public void ofEpochSecond_(){

        assertEquals(ofEpochSecond(3), ofEpochSecond(3, 0));

        assertEquals(ofEpochSecond(2, 1_000_000_000), ofEpochSecond(4, -1_000_000_000));
        assertEquals(ofEpochSecond(3), ofEpochSecond(2, 1_000_000_000));
    }

    // 01. java.util.Date --> java.time.LocalDateTime
    public void UDateToLocalDateTime() {
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
    }

    // 02. java.util.Date --> java.time.LocalDate
    public void UDateToLocalDate() {
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalDate localDate = localDateTime.toLocalDate();
    }

    // 03. java.util.Date --> java.time.LocalTime
    public void UDateToLocalTime() {
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        LocalTime localTime = localDateTime.toLocalTime();
    }


    // 04. java.time.LocalDateTime --> java.util.Date
    public void LocalDateTimeToUdate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
    }


    // 05. java.time.LocalDate --> java.util.Date
    public void LocalDateToUdate() {
        LocalDate localDate = LocalDate.now();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
    }

    // 06. java.time.LocalTime --> java.util.Date
    public void LocalTimeToUdate() {
        LocalTime localTime = LocalTime.now();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        Date date = Date.from(instant);
    }



    final Date date = new Date();
    final Timestamp timestamp = new Timestamp(date.getTime());
    final Calendar calendar = Calendar.getInstance();
//
    final Instant instant = now();
    final LocalDateTime localDateTime = LocalDateTime.now();
    final ZonedDateTime zonedDateTime = ZonedDateTime.now();


    @Test
    public void instant() {

        //Date转Instant
        Instant dateInstant = date.toInstant();
        //Timestamp转Instant
        Instant timestampInstant = timestamp.toInstant();
        //Calendar转Instant
        Instant calendarInstant = calendar.toInstant();
        //LocalDateTime转Instant
        Instant localDateTimeInstant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        //ZonedDateTime转Instant
        Instant zonedDateTimeInstant = zonedDateTime.toInstant();


        //Date转LocalDateTime
        LocalDateTime dateLocalDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        //Timestamp转LocalDateTime
        LocalDateTime timestampLocalDateTime = timestamp.toLocalDateTime();
        //Calendar转LocalDateTime
        LocalDateTime calendarLocalDateTime = LocalDateTime.ofInstant(calendar.toInstant(), ZoneOffset.systemDefault());
        //Instant转LocalDateTime
        LocalDateTime instantLocalDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        //ZonedDateTime转LocalDateTime
        LocalDateTime zonedDateTimeLocalDateTime = zonedDateTime.toLocalDateTime();


        //Date转ZonedDateTime
        ZonedDateTime dateZonedDateTime = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        //Timestamp转ZonedDateTime
        ZonedDateTime timestampZonedDateTime = ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault());
        //Calendar转ZonedDateTime
        ZonedDateTime calendarZonedDateTime = ZonedDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
        //Instant转ZonedDateTime
        ZonedDateTime instantZonedDateTime = instant.atZone(ZoneId.systemDefault());
        //LocalDateTime转ZonedDateTime
        ZonedDateTime localDateTimeZonedDateTime = localDateTime.atZone(ZoneId.systemDefault());


        //Timestamp转Date
        Date timestampDate = new Date(timestamp.getTime());
        //Calendar转Date
        Date calendarDate = calendar.getTime();
        //Instant转Date
        Date instantDate = Date.from(instant);
        //LocalDateTime转Date
        Date localDateTimeDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        //ZonedDateTime转Date
        Date zonedDateTimeDate = Date.from(zonedDateTime.toInstant());


        //Date转Timestamp
        Timestamp dateTimestamp = new Timestamp(date.getTime());
        //Calendar转Timestamp
        Timestamp calendarTimestamp = new Timestamp(calendar.getTimeInMillis());
        //Instant转Timestamp
        Timestamp instantTimestamp = Timestamp.from(instant);
        //LocalDateTime转Timestamp
        Timestamp localDateTimeTimestamp = Timestamp.valueOf(localDateTime);
        //ZonedDateTime转Timestamp
        Timestamp zonedDateTimeTimestamp = Timestamp.from(zonedDateTime.toInstant());


        //Date转Calendar
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        //Timestamp转Calendar
        Calendar timestampCalendar = Calendar.getInstance();
        timestampCalendar.setTimeInMillis(timestamp.getTime());
        //Instant转Calendar
        Calendar instantCalendar = GregorianCalendar.from(ZonedDateTime.ofInstant(instant, ZoneId.systemDefault()));
        //LocalDateTime转Calendar
        Calendar localDateTimeCalendar = GregorianCalendar.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()));
        //ZonedDateTime转Calendar
        Calendar zonedDateTimeInstantCalendar = GregorianCalendar.from(zonedDateTime);
    }


    @Test
    public void test() {
        LocalTime classTime = LocalTime.of(16, 50);
        LocalDateTime classTimeBegin = LocalDateTime.of(LocalDate.now(), classTime);
        Instant beginTime = classTimeBegin.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println("beginTime = " + beginTime);


        Instant now = now();
        Instant maxTime = beginTime.minus(10, ChronoUnit.MINUTES);
        Instant minTime = beginTime.minus(-30, ChronoUnit.MINUTES);

        System.out.println("now = " + now);
        System.out.println("maxTime = " + maxTime);
        System.out.println("minTime = " + minTime);

        boolean before = now.isBefore(minTime);
        System.out.println("before = " + before);

        boolean after = now.isAfter(maxTime);
        System.out.println("after = " + after);

    }

}
