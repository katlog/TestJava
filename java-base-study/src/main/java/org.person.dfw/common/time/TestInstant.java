package org.person.dfw.common.time;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestInstant {

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
    final Instant instant = Instant.now();
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


        Instant now = Instant.now();
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
