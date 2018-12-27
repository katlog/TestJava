/**  
 * @Title: TestCalender.java
 * @Package: org.person.dfw.Time
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月20日 下午8:45:15
 * @version: V1.0  
 */
package org.person.dfw.common.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;


/**
 * @moudle: TestCalender
 * @version:v1.0
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月20日 下午8:45:15
 *
 */
public class TestCalendar {
	/**
	 * 获取存储时间
	 * Title: getStoreTime
	 * author : fw
	 * date : 2017年3月6日 下午1:28:50
	 * @return 值为从当前时间到本日凌晨之间的毫秒数
	 */
	private static long getStoreTime() {
		final Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR) + 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		final long diff = cal.getTimeInMillis() - System.currentTimeMillis();
		return diff;
	}

	public static void main(String[] args) {
		// Calendar的子类
		// GregorianCalendar

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		// 设置日期为2011-07-24 09：59:50
		calendar.set(2011, 06, 24, 9, 59, 50);

		// 12小时制
		int temp12Hour = Calendar.HOUR;

		// 24小时制
		int temp24Hour = calendar.HOUR_OF_DAY;

		// 显示年份
		int yearIndex = Calendar.YEAR;
		int year = calendar.get(yearIndex);
		System.out.println("yearIndex=" + yearIndex);
		System.out.println("year=" + year);

		// 显示月份 (从0开始, 实际显示要加一)
		int monthIndex = Calendar.MONTH;
		int month = calendar.get(monthIndex) + 1;
		System.out.println("monthIndex=" + monthIndex);
		System.out.println("month=" + month);

		// 今年的第几天
		int dayOfYearIndex = Calendar.DAY_OF_YEAR;
		int dayOfYear = calendar.get(dayOfYearIndex);
		System.out.println("dayOfYearIndex=" + dayOfYearIndex);
		System.out.println("dayOfYear=" + dayOfYear);

		// 本月的第N天
		int dayOfMonthIndex = Calendar.DAY_OF_MONTH;
		int dayOfMonth = calendar.get(dayOfMonthIndex);
		System.out.println("dayOfMonthIndex=" + dayOfMonthIndex);
		System.out.println("dayOfMonth=" + dayOfMonth);

		// 本周第N天，从周日开始
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0) {
			dayOfWeek = 7;
		}
		System.out.println("dayOfWeek=" + dayOfWeek);

		// 三小时以后
		int hourOfDayIndex = Calendar.HOUR_OF_DAY;
		calendar.add(hourOfDayIndex, 3);
		int afterThreeHour = calendar.get(hourOfDayIndex);
		System.out.println("afterThreeHour=" + afterThreeHour);

		// 当前分钟数
		int minuteIndex = Calendar.MINUTE;
		int minute = calendar.get(minuteIndex);
		System.out.println("minuteIndex=" + minuteIndex);
		System.out.println("minute=" + minute);

		// 15分钟以后
		calendar.add(minuteIndex, 15);
		minute = calendar.get(minuteIndex);
		System.out.println("minute+15=" + minute);

		// 30分钟以前
		calendar.add(minuteIndex, -30);
		minute = calendar.get(minuteIndex);
		System.out.println("minute-30=" + minute);

		// 格式化显示
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		String time = sdf.format(calendar.getTime());
		System.out.println(time);

		// 重置Calendar显示当前时间
		calendar.setTime(new Date());
		time = sdf.format(calendar.getTime());
		System.out.println(time);

		// 创建一个Calendar 用于比较时间
		Calendar calendarNew = Calendar.getInstance();

		// 设定为5小时以前，后者大，显示-1
		calendarNew.add(Calendar.HOUR, -5);
		System.out.println("时间比较：" + calendarNew.compareTo(calendar));

		// 设定7小时以后，前者大 ，显示1
		calendarNew.add(Calendar.HOUR, +7);
		System.out.println("时间比较：" + calendarNew.compareTo(calendar));

		// 退回2小时，时间相同，显示0
		calendarNew.add(Calendar.HOUR, -2);
		System.out.println("时间比较：" + calendarNew.compareTo(calendar));

		// 创建两个日历对象
		Calendar cal = Calendar.getInstance();
		Calendar future = Calendar.getInstance();

		// 打印当前日期
		System.out.println("Current date: " + cal.getTime());

		// 改变年份
		future.set(Calendar.YEAR, 2066);
		System.out.println("Year is " + future.get(Calendar.YEAR));

		// 检查日期是否在当前日期之后
		if (future.after(cal)) {
			System.out.println("Date " + future.getTime()
					+ " is after current date.");
		}

	}


	@Test public void test1() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startRentDate = sdf.parse("2018-3-31");
		 //租期
		 Long zuqi = 15l;
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(startRentDate);
		 calendar.set(Calendar.MONTH, (int) (calendar.get(Calendar.MONTH)+zuqi-1));
		 calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
		 
		 Date endDate = calendar.getTime();
		 System.out.println(sdf.format(endDate));
	}
}
