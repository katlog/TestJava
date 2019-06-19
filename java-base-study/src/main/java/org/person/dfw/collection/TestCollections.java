
/**  
 * @Title: TestCollections.java
 * @Package: org.person.dfw.collection
 * @author: katlog
 * @date: 2017年6月23日 下午5:35:08
 * @version: V1.0  
 */ 
package org.person.dfw.collection;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/**
 * @moudle: TestCollections 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年6月23日 下午5:35:08
 *
 */
public class TestCollections {

	// region  ----------------------------------sort 方法测试
	public class Book implements Comparable { // 定义名为Book的类，默认继承自Object类  
	    public int id;// 编号  
	    public String name;// 名称  
	    public double price; // 价格  
	    private String author;// 作者  
	    public GregorianCalendar calendar;// 出版日期  
	    public Book() {  this(0, "X", 0.0, new GregorianCalendar(), ""); }  
	    public Book(int id, String name, double price, GregorianCalendar calender, String author) {  
	        this.id = id;  
	        this.name = name;  
	        this.price = price;  
	        this.calendar = calender;  
	        this.author = author;  
	    }  
	  
	    public int compareTo(Object obj) {// Comparable接口中的方法  
	        Book b = (Book) obj;  
	        return this.id - b.id; // 按书的id比较大小，用于默认排序  
	    }  
	    
	    public String toString() {  
	        String showStr = id + "\t" + name; // 定义显示类信息的字符串  
	        DecimalFormat formatPrice = new DecimalFormat("0.00");// 格式化价格到小数点后两位  
	        showStr += "\t" + formatPrice.format(price);// 格式化价格  
	        showStr += "\t" + author;  
	        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy年MM月dd日");  
	        showStr += "\t" + formatDate.format(calendar.getTime()); // 格式化时间  
	        return showStr; // 返回类信息字符串  
	    }  
	}  
	
    // 自定义方法：分行打印输出list中的元素  
    public static void myprint(List<Book> list) {  
        Iterator it = list.iterator(); // 得到迭代器，用于遍历list中的所有元素  
        while (it.hasNext()) {// 如果迭代器中有元素，则返回true  
            System.out.println("\t" + it.next());// 显示该元素  
        }  
    }  
  
    // 自定义比较器：按书的价格排序  
    static class PriceComparator implements Comparator {  
        public int compare(Object object1, Object object2) {// 实现接口中的方法  
            Book p1 = (Book) object1; // 强制转换  
            Book p2 = (Book) object2;  
            return new Double(p1.price).compareTo(new Double(p2.price));  
        }  
    }  
  
    // 自定义比较器：按书出版时间来排序  
    static class CalendarComparator implements Comparator {  
        public int compare(Object object1, Object object2) {// 实现接口中的方法  
            Book p1 = (Book) object1; // 强制转换  
            Book p2 = (Book) object2;  
            return p2.calendar.compareTo(p1.calendar);  
        }  
    }  
    
	public  void sort() {  
        List<Book> list = new ArrayList<Book>(); // 数组序列  
        Book b1 = new TestCollections().new  Book(10000, "红楼梦", 150.86, new GregorianCalendar(2009, 01, 25), "曹雪芹、高鄂");  
        Book b2 = new TestCollections().new Book(10001, "三国演义", 99.68, new GregorianCalendar(2008, 7, 8), "罗贯中 ");  
        Book b3 = new TestCollections().new Book(10002, "水浒传", 100.8, new GregorianCalendar(2009, 6, 28), "施耐庵 ");  
        Book b4 = new TestCollections().new Book(10003, "西游记", 120.8, new GregorianCalendar(2011, 6,  8), "吴承恩");  
        list.add(b1);  list.add(b2);  list.add(b3);  list.add(b4);   //添加上
        // Collections.sort(list); //没有默认比较器，不能排序  
        System.out.println("数组序列中的元素:");  
        myprint(list);  
        Collections.sort(list, new PriceComparator()); // 根据价格排序  
        System.out.println("按书的价格排序:");  
        myprint(list);  
        Collections.sort(list, new CalendarComparator()); // 根据时间排序  
        System.out.println("按书的出版时间排序:");  
        myprint(list);  
    }  
  
	// endregion ---------------------------sort测试---------------------

}
