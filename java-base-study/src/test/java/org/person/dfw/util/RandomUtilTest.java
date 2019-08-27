package org.person.dfw.util;

import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

public class RandomUtilTest {

    @Test
    public void integer() {
    }

    @Test
    public void number() {
    }

    @Test
    public void string() {
    }

    @Test
    public void mixString() {
    }

    @Test
    public void lowerString() {
    }

    @Test
    public void upperString() {
    }

    @Test
    public void zeroString() {
    }

    @Test
    public void toFixdLengthString() {
    }

    @Test
    public void toFixdLengthString1() {
    }

    @Test
    public void randomItem() {
    }

    @Test
    public void randomItem1() {
    }

    @Test
    public void uuid() {
    }

    @Test
    public void UUID() {
    }

    @Test
    public void squid() {
    }

    enum N{A,B,C,D}
    @Test
    public void randomEum(){
        N n = RandomUtil.randomEnum(N.class);
        assertNotNull(n);
        System.out.println("n = " + n);
    }

    @Test
    public void randomDate() {
        Date date = RandomUtil.randomDate();

        assertNotNull(date);
        System.out.println(DateUtil.date(date));
    }



    @Test
    public void randomDate_dateBetween() throws ParseException {
        Date begin = DateUtil.date("2019-12-11");
        Date end = DateUtil.date("2019-12-12");
        Date date = RandomUtil.randomDate(begin, end);

        assertNotNull(date);

        assertTrue(date.after(begin));
        assertTrue(date.before(end));

        System.out.println(DateUtil.date(date));
    }
}