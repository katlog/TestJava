package name.katlog.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
import static name.katlog.util.DateUtil.timestampToStr;

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


    /** 当天、当月、当年 开始结束时间 */
    @Test
    public void get_dailyMonthYearStartEnd(){
        Long currentTime = System.currentTimeMillis();
        System.out.println("Current Time : " + currentTime + " = " + timestampToStr(currentTime, "GMT+8"));
        Date dailyStart = DateUtil.getDailyStartTime(currentTime, "GMT+8:00");
        Date dailyEnd = DateUtil.getDailyEndTime(currentTime, "GMT+8:00");
        Date monthStart = DateUtil.getMonthStartTime(currentTime, "GMT+8:00");
        Date monthEnd = DateUtil.getMonthEndTime(currentTime, "GMT+8:00");
        Date yearStart = DateUtil.getYearStartTime(currentTime, "GMT+8:00");
        Date yearEnd = DateUtil.getYearEndTime(currentTime, "GMT+8:00");

        System.out.println("Daily Start : " + dailyStart + " = " + timestampToStr(dailyStart, "GMT+8") + " Daily End : " + dailyEnd + " = " + timestampToStr(dailyEnd, "GMT+8"));
        System.out.println("Month Start : " + monthStart + " = " + timestampToStr(monthStart, "GMT+8") + " Month End : " + monthEnd + " = " + timestampToStr(monthEnd, "GMT+8"));
        System.out.println("Year Start : " + yearStart + " = " + timestampToStr(yearStart, "GMT+8") + " Year End : " + yearEnd + " = " + timestampToStr(yearEnd, "GMT+8"));
    }
}