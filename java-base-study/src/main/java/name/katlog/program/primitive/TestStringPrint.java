
/**  
 * @Title: TestStringPrint.java
 * @Package: org.person.dfw.program.primitive
 * @author: katlog
 * @date: 2017年6月23日 上午11:35:35
 * @version: V1.0  
 */ 
package name.katlog.program.primitive;

import java.util.Date;
import java.util.Locale;

import org.junit.Test;

/**
 * @moudle: TestStringPrint 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年6月23日 上午11:35:35
 *
 */
public class TestStringPrint {
	
	   @Test public void println(){//格式化打印
	    	
	        /*** 输出字符串 ***/      	          // %s表示输出字符串，也就是将后面的字符串替换模式中的%s  
	        System.out.printf("%s", new Integer(1212));  
	        // %n表示换行  
	        System.out.printf("%s%n", "end line");  
	        // 还可以支持多个参数  
	        System.out.printf("%s = %s%n", "Name", "Zhangsan");  
	        // %S将字符串以大写形式输出  
	        System.out.printf("%S = %s%n", "Name", "Zhangsan");  
	        // 支持多个参数时，可以在%s之间插入变量编号，1$表示第一个字符串，3$表示第3个字符串  
	        System.out.printf("%1$s = %3$s %2$s%n", "Name", "san", "Zhang");  
	          
	        /*** 输出boolean类型 ***/  
	        System.out.printf("true = %b; false = ", true);  
	        System.out.printf("%b%n", false);  


	        /*** 输出整数类型***/  
	        Integer iObj = 342;  
	        // %d表示将整数格式化为10进制整数  
	        System.out.printf("%d; %d; %d%n", -500, 2343L, iObj);  
	        // %o表示将整数格式化为8进制整数  
	        System.out.printf("%o; %o; %o%n", -500, 2343L, iObj);  
	        // %x表示将整数格式化为16进制整数  
	        System.out.printf("%x; %x; %x%n", -500, 2343L, iObj);  
	        // %X表示将整数格式化为16进制整数，并且字母变成大写形式  
	        System.out.printf("%X; %X; %X%n", -500, 2343L, iObj);  
	          
	        /*** 输出浮点类型***/  
	        Double dObj = 45.6d;  
	        // %e表示以科学技术法输出浮点数  
	        System.out.printf("%e; %e; %e%n", -756.403f, 7464.232641d, dObj);  
	        // %E表示以科学技术法输出浮点数，并且为大写形式               
	        System.out.printf("%E; %E; %E%n", -756.403f, 7464.232641d, dObj);  
	        // %f表示以十进制格式化输出浮点数  
	        System.out.printf("%f; %f; %f%n", -756.403f, 7464.232641d, dObj);  
	        // 还可以限制小数点后的位数  
	        System.out.printf("%.1f; %.3f; %f%n", -756.403f, 7464.232641d, dObj);  
	          
	        /*** 输出日期类型***/  
	        // %t表示格式化日期时间类型，%T是时间日期的大写形式，在%t之后用特定的字母表示不同的输出格式  
	        Date date = new Date();  
	        long dataL = date.getTime();  
	        // 格式化年月日  
	        // %t之后用y表示输出日期的年份（2位数的年，如99）  
	        // %t之后用m表示输出日期的月份，%t之后用d表示输出日期的日号  
	        System.out.printf("%1$ty-%1$tm-%1$td; %2$ty-%2$tm-%2$td%n", date, dataL);  
	        // %t之后用Y表示输出日期的年份（4位数的年），  
	        // %t之后用B表示输出日期的月份的完整名， %t之后用b表示输出日期的月份的简称  
	        System.out.printf("%1$tY-%1$tB-%1$td; %2$tY-%2$tb-%2$td%n", date, dataL);  
	          
	        // 以下是常见的日期组合  
	        // %t之后用D表示以 "%tm/%td/%ty"格式化日期   
	        System.out.printf("%1$tD%n", date);  
	        //%t之后用F表示以"%tY-%tm-%td"格式化日期   
	        System.out.printf("%1$tF%n", date);  
	          
	        /*** 输出时间类型***/  
	        // 输出时分秒  
	        // %t之后用H表示输出时间的时（24进制），%t之后用I表示输出时间的时（12进制），  
	        // %t之后用M表示输出时间的分，%t之后用S表示输出时间的秒  
	        System.out.printf("%1$tH:%1$tM:%1$tS; %2$tI:%2$tM:%2$tS%n", date, dataL);  
	        // %t之后用L表示输出时间的秒中的毫秒  
	        System.out.printf("%1$tH:%1$tM:%1$tS %1$tL%n", date);  
	        // %t之后p表示输出时间的上午或下午信息  
	        System.out.printf("%1$tH:%1$tM:%1$tS %1$tL %1$tp%n", date);  
	          
	        // 以下是常见的时间组合  
	        // %t之后用R表示以"%tH:%tM"格式化时间  
	        System.out.printf("%1$tR%n", date);  
	        // %t之后用T表示以"%tH:%tM:%tS"格式化时间   
	        System.out.printf("%1$tT%n", date);  
	        // %t之后用r表示以"%tI:%tM:%tS %Tp"格式化时间   
	        System.out.printf("%1$tr%n", date);  
	          
	        /*** 输出星期***/  
	        // %t之后用A表示得到星期几的全称  
	        System.out.printf("%1$tF %1$tA%n", date);  
	        // %t之后用a表示得到星期几的简称  
	        System.out.printf("%1$tF %1$ta%n", date);  
	          
	        // 输出时间日期的完整信息  
	        System.out.printf("%1$tc%n", date);  
	    }
	
	@Test public void test1(){
	    String str=null;  
	    str=String.format("Hi,%s", "王力");  
	    System.out.println(str);  
	    str=String.format("Hi,%s:%s.%s", "王南","王力","王张");            
	    System.out.println(str);                           
	    System.out.printf("字母a的大写是：%c %n", 'A');  
	    System.out.printf("3>7的结果是：%b %n", 3>7);  
	    System.out.printf("100的一半是：%d %n", 100/2);  
	    System.out.printf("100的16进制数是：%x %n", 100);  
	    System.out.printf("100的8进制数是：%o %n", 100);  
	    System.out.printf("50元的书打8.5折扣是：%f 元%n", 50*0.85);  
	    System.out.printf("上面价格的16进制数是：%a %n", 50*0.85);  
	    System.out.printf("上面价格的指数表示：%e %n", 50*0.85);  
	    System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50*0.85);  
	    System.out.printf("上面的折扣是%d%% %n", 85);  
	    System.out.printf("字母A的散列码是：%h %n", 'A');
	}
	
	@Test public void test2() {
	    String str=null;  
	    //$使用  
	    str=String.format("格式参数$的使用：%1$d,%2$s", 99,"abc");             
	    System.out.println(str);                       
	    //+使用  
	    System.out.printf("显示正负数的符号：%+d与%d%n", 99,-99);  
	    //补O使用  
	    System.out.printf("最牛的编号是：%03d%n", 7);  
	    //空格使用  
	    System.out.printf("Tab键的效果是：% 8d%n", 7);  
	    //.使用  
	    System.out.printf("整数分组的效果是：%,d%n", 9989997);  
	    //空格和小数点后面个数  
	    System.out.printf("一本书的价格是：% 50.5f元%n", 49.8);  
	}
	
	@Test public void test3() {
	    Date date=new Date();                                  
	    //c的使用  
	    System.out.printf("全部日期和时间信息：%tc%n",date);          
	    //f的使用  
	    System.out.printf("年-月-日格式：%tF%n",date);  
	    //d的使用  
	    System.out.printf("月/日/年格式：%tD%n",date);  
	    //r的使用  
	    System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n",date);  
	    //t的使用  
	    System.out.printf("HH:MM:SS格式（24时制）：%tT%n",date);  
	    //R的使用  
	    System.out.printf("HH:MM格式（24时制）：%tR",date);  
	}
	
	@Test public void test4() {
	    Date date=new Date();                                      
	    //b的使用，月份简称  
	    String str=String.format(Locale.US,"英文月份简称：%tb",date);       
	    System.out.println(str);                                                                              
	    System.out.printf("本地月份简称：%tb%n",date);  
	    //B的使用，月份全称  
	    str=String.format(Locale.US,"英文月份全称：%tB",date);  
	    System.out.println(str);  
	    System.out.printf("本地月份全称：%tB%n",date);  
	    //a的使用，星期简称  
	    str=String.format(Locale.US,"英文星期的简称：%ta",date);  
	    System.out.println(str);  
	    //A的使用，星期全称  
	    System.out.printf("本地星期的简称：%tA%n",date);  
	    //C的使用，年前两位  
	    System.out.printf("年的前两位数字（不足两位前面补0）：%tC%n",date);  
	    //y的使用，年后两位  
	    System.out.printf("年的后两位数字（不足两位前面补0）：%ty%n",date);  
	    //j的使用，一年的天数  
	    System.out.printf("一年中的天数（即年的第几天）：%tj%n",date);  
	    //m的使用，月份  
	    System.out.printf("两位数字的月份（不足两位前面补0）：%tm%n",date);  
	    //d的使用，日（二位，不够补零）  
	    System.out.printf("两位数字的日（不足两位前面补0）：%td%n",date);  
	    //e的使用，日（一位不补零）  
	    System.out.printf("月份的日（前面不补0）：%te",date);  
	}
	
	@Test public void test5() {
	    Date date = new Date();  
	    //H的使用  
	    System.out.printf("2位数字24时制的小时（不足2位前面补0）:%tH%n", date);  
	    //I的使用  
	    System.out.printf("2位数字12时制的小时（不足2位前面补0）:%tI%n", date);  
	    //k的使用  
	    System.out.printf("2位数字24时制的小时（前面不补0）:%tk%n", date);  
	    //l的使用  
	    System.out.printf("2位数字12时制的小时（前面不补0）:%tl%n", date);  
	    //M的使用  
	    System.out.printf("2位数字的分钟（不足2位前面补0）:%tM%n", date);  
	    //S的使用  
	    System.out.printf("2位数字的秒（不足2位前面补0）:%tS%n", date);  
	    //L的使用  
	    System.out.printf("3位数字的毫秒（不足3位前面补0）:%tL%n", date);  
	    //N的使用  
	    System.out.printf("9位数字的毫秒数（不足9位前面补0）:%tN%n", date);  
	    //p的使用  
	    String str = String.format(Locale.US, "小写字母的上午或下午标记(英)：%tp", date);  
	    System.out.println(str);   
	    System.out.printf("小写字母的上午或下午标记（中）：%tp%n", date);  
	    //z的使用  
	    System.out.printf("相对于GMT的RFC822时区的偏移量:%tz%n", date);  
	    //Z的使用  
	    System.out.printf("时区缩写字符串:%tZ%n", date);  
	    //s的使用  
	    System.out.printf("1970-1-1 00:00:00 到现在所经过的秒数：%ts%n", date);  
	    //Q的使用  
	    System.out.printf("1970-1-1 00:00:00 到现在所经过的毫秒数：%tQ%n", date);  
	}
	
}
