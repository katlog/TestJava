package org.person.dfw.util;

import org.junit.Test;

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
}