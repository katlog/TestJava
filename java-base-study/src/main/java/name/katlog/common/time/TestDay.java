
/**  
 * @Title: TestDay.java
 * @Package: org.person.dfw.common.time
 * @author: katlog
 * @date: 2017年6月20日 下午5:30:28
 * @version: V1.0  
 */ 
package name.katlog.common.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

/**
 * @moudle: TestDay 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年6月20日 下午5:30:28
 *
 */
public class TestDay {
	@Test public void test(){
		Calendar calendar = Calendar.getInstance();
		Date today = new Date();
		calendar.setTime(today);

		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow  = calendar.getTime();

		calendar.add(Calendar.DAY_OF_YEAR, -2);
		Date yesterday = calendar.getTime();

		//
		System.out.println(today.after(yesterday));
		System.out.println(today.after(tomorrow));
		//
		System.out.println(today.before(yesterday));
		System.out.println(today.before(tomorrow));

		//比较日期
		// 日期相等为0
		System.out.println(today.compareTo(today));
		// 比比较日期小 -1
		System.out.println(today.compareTo(tomorrow));
		// 比比较日期大  1
		System.out.println(today.compareTo(yesterday));
		System.out.println(tomorrow.compareTo(yesterday));
		
	}
	
	@Test public void test1() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startRentDate = sdf.parse("2016-10-11");
		System.out.println(sdf.format(startRentDate));
		 //租期
		 Long zuqi = 12l;
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(startRentDate);
		 calendar.set(Calendar.MONTH,  (int) (calendar.get(Calendar.MONTH)+zuqi-1));
		 calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
		 //最后一期分界线
		 Date endDate = calendar.getTime();
		 System.out.println(sdf.format(endDate));
		 Date today = new Date();
		 System.out.println(sdf.format(today));
		 int compareTo = today.compareTo(endDate);
		 System.out.println(compareTo);
		 
		 
	}
	
	/**比较时间*/
	@Test public void compareTime(){
		
		Date before = new Date();
		
		try {
			Thread.sleep(1000*60);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Date now = new Date();
		
		System.out.println(before.getTime() - now.getTime());
	}

	@Test
	public void localDate() {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse("2018-02-24", df);
		LocalDate localDate1 = localDate.plusDays(11);

		System.out.println(localDate1);
	}

	@Test
	public void localDate1() {
		LocalDate now = LocalDate.now();
		long l = now.toEpochDay();
		System.out.println("toEpochDay返回1970-01-01 来的总天数 = " + l);
	}

	@Test
	public void localDateTime() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime midnight = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
		Instant instant = midnight.atZone(ZoneId.systemDefault()).toInstant();
		System.out.println("instant = " + instant);
		long l = instant.toEpochMilli();
		System.out.println("l = " + l);
		ZoneId zoneId = ZoneId.systemDefault();
		System.out.println("zoneId = " + zoneId);

		long aLong = now.getLong(ChronoField.MILLI_OF_SECOND);
		System.out.println("aLong = " + aLong);

	}
}
