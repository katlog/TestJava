package org.person.dfw.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.TemporalField;
import java.util.Date;

import static org.junit.Assert.*;

public class DateUtilTest {

    @Test
    public void subtractDay() {
        assertEquals(2, DateUtil.subtractDay("2019-03-05 12:12:12", "2019-03-08 00:00:00"));
        assertEquals(3, DateUtil.subtractDay("2019-03-05 12:12:12", "2019-03-08 23:59:59"));
    }

    @Test
    public void addHour() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse("2018-12-12 23:30:30");

        assertEquals(sdf.parse("2018-12-13 02:30:30").getTime(),DateUtil.addHour(date1, 3).getTime());
        assertEquals(sdf.parse("2018-12-12 20:30:30").getTime(),DateUtil.addHour(date1, -3).getTime());
    }

    @Test
    public void addMonth() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2018-12-12");

        assertEquals(sdf.parse("2019-03-12").getTime(),DateUtil.addMonth(date1, 3).getTime());
        assertEquals(sdf.parse("2018-09-12").getTime(),DateUtil.addMonth(date1, -3).getTime());
    }
}