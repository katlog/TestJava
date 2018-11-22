package org.person.dfw.international;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.text.ChoiceFormat;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

/**
 * @moudle: TestMessageFormat
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年4月1日 下午5:09:16
 */
public class TestFormat {
	
	private String currentPath = new File(TestFormat.class.getResource("").getFile()).getPath();
	private String classPath = new File(TestFormat.class.getResource("/").getFile()).getPath();

    @Test public void messageFormat1() {
        /**
         * ArgumentIndex必须是非负整数，它的个数不只限于0到9这10个，它可以用0到9的数字组成，因此可以有好多个
         */
        String msg = "{0}{1}{2}{3}{4}{5}{6}{7}{8}";
        Object[] array = new Object[] {"A", "B", "C", "D", "E", "F", "G", "H", "I",};
        String value = MessageFormat.format(msg, array);

        assertEquals(value, "ABCDEFGHI");
        System.out.println(value); // 输出：

    }

    @Test public void messageFormat2() {
        /**
         * 格式化字符串时，两个单引号才表示一个单引号，单个单引号会被省略，除非中文单引号不会被省略
         * 如果需要显示双引号要进行转移，比如：String msg = "oh, {0} is \"a\" pig";
         */
        String value = MessageFormat.format("oh, {0} is 'a' pig", "ZhangSan");
        System.out.println(value);

        String value1 = MessageFormat.format("oh, {0} is ''a'' pig", "ZhangSan");
        System.out.println(value1);
    }

    @Test public void messageFormat3() {
		Object[] params = {"John", new GregorianCalendar().getTime(),1.0E3};
		String pattern1 = "{0}，你好！你于{1}在工商银行存入{2} 元。";
		String pattern2 = "At {1,time,short} On{1,date,long}，{0} paid {2,number,currency}.";
		
		String msg1 = MessageFormat.format(pattern1,params);//本地化信息
		MessageFormat mf = new MessageFormat(pattern2,Locale.US);//美帝信息
		String msg2 = mf.format(params);
		System.out.println(msg1);
		System.out.println(msg2);
	}
	
    @Test  public void messageFormat4() {
        /**
         * 单引号会使其后面的占位符均失效，导致直接输出占位符
         */
        MessageFormat.format("{0}{1}", 1, 2); // 结果12
        MessageFormat.format("'{0}{1}", 1, 2); // 结果{0}{1}
        MessageFormat.format("'{0}'-{1}", 1, 2); // 结果{0}-2

        /**
         * 使用双引号和两个单引号没有关系
         */
        String value = MessageFormat.format("oh, ''{0}'' is a pig", "ZhangSan");
        System.out.println(value); // 输出：oh, 'ZhangSan' is a pig

        /**
         * 使用子格式模式，多了一个单引号
         */
        String value1 = MessageFormat.format("oh, {0,number,#.#} is good num", Double.valueOf("3.1415"));
        System.out.println(value1); // 输出：oh, 3.1 is good num

    }

    @Test public void messageFormat5() {
        /**
         * 无论是有引号字符串还是无引号字符串，左花括号都是不支持的
         */
        String value1 = MessageFormat.format("oh, } is good num", Double.valueOf("3.1415"));
        System.out.println(value1); // 输出：oh, } is good num

        /**
         * 如果使用左花括号会出现异常
         */
//        String value2 = MessageFormat.format("oh, { is good num", Double.valueOf("3.1415"));
//        System.out.println(value2); // java.lang.IllegalArgumentException: Unmatched

        /**
         * 因此要使用到左花括号需要使用单引号配合使用
         * MessageFormat.format("'{'{0}}", "X-rapido"); // {X-rapido}
         * 还有一个有趣的现象，如果出现两个或2个以上左花括号，就会出现分割字符串，但是右花括号就没问题，虽然没有任何意义，实际应用我们也用不到
         */
        // braces in the pattern.
        String value3 = MessageFormat.format("oh, {{ is good num", "d");
        System.out.println(value3); // oh,

        /**
         * 
         */
        String value4 = MessageFormat.format("oh, }} is good num", "d");
        System.out.println(value4); // oh, }} is good num

    }
    
    @Test public void numberFormat() throws ParseException{
    	//格式化
		Locale locale = new Locale("zh", "CN");
		NumberFormat currFmt = NumberFormat.getCurrencyInstance(locale);
		System.out.println(currFmt.format(123456.78));
		
		//解析
		Number parse = currFmt.parse("￥123,456.78");
		System.out.println((double) parse);
    }

    @Test public void  dateFormat() {
		Locale locale = new Locale("en", "US");
		Date date = new Date();
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
		System.out.println(df.format(date));
	}
    
    @Test public void choicFormat() {
        double[] limit = {0, 1,2, 4};
        String[] format = {"hello0", "hello1","hello2" ,"hello3"};
        ChoiceFormat cf = new ChoiceFormat(limit, format);
        for (int i = 0; i < 8; ++i) {
            System.out.println(cf.format(i));
        }

        String pattern = "0#[0,3)|3#[3,6)|6#[6,9)|9#[9]";  
        ChoiceFormat format1 = new ChoiceFormat(pattern);  
        //输出的结果是  与下面相同
        // 0=[0,3)|1=[0,3)|2=[0,3)|3=[3,6)|4=[3,6)|5=[3,6)|6=[6,9)|7=[6,9)|8=[6,9)|9=[9]|  
        for(int i=0;i<10;i++){  
            System.out.print(i+"="+format1.format(i)+"|");  
        }  
        System.out.println();
        
        double limit2[] = {0,3,6,9};  
        String [] mes = {"[0,3)","[3,6)","[6,9)","[9]"};  
        ChoiceFormat format2 = new ChoiceFormat(limit2,mes);  
        //输出的结果是  与上面相同
        // 0=[0,3)|1=[0,3)|2=[0,3)|3=[3,6)|4=[3,6)|5=[3,6)|6=[6,9)|7=[6,9)|8=[6,9)|9=[9]|  
        for(int i=0;i<10;i++){  
            System.out.print(i+"="+format2.format(i)+"|");  
        }  
    }
    
    @Test  public void comprehensive(){
        double[] limit = {0, 1};
        String[] format = {"Hello0", "Hello1{1}"};
        ChoiceFormat cf = new ChoiceFormat(limit, format);
        MessageFormat mf = new MessageFormat("{0}");
        mf.setFormatByArgumentIndex(0, cf);
        for (int i = 0; i < 2; ++i){
            System.out.println(mf.format(new Object[]{new Integer(i), new Integer(i+1)}));
        }
    }
    
	
    @Test  public void resourceBoundle(){
    	String packagePath = currentPath.replace(classPath, "");
		ResourceBundle rb1 = ResourceBundle.getBundle(packagePath+"\\resource",Locale.US);
		ResourceBundle rb2 = ResourceBundle.getBundle(packagePath+"\\resource",Locale.CANADA);
		System.out.println("us:"+rb1.getString("greeting.common"));
		System.out.println("cn:"+rb2.getString("greeting.common"));
	}
	
    @Test  public void resourceBoundleFmt(){
    	String packagePath = currentPath.replace(classPath, "");
		ResourceBundle rb1 = ResourceBundle.getBundle(packagePath+"/fmt_resource",Locale.US);
		ResourceBundle rb2 = ResourceBundle.getBundle(packagePath+"/fmt_resource",Locale.CHINA);
		Object[] params = {"John", new GregorianCalendar().getTime()};
		
		String str1 = new MessageFormat(rb1.getString("greeting.common"),Locale.US).format(params);
		String str2 =new MessageFormat(rb2.getString("greeting.morning"),Locale.CHINA).format(params);
		String str3 =new MessageFormat(rb2.getString("greeting.afternoon"),Locale.CHINA).format(params);
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
	}
}
