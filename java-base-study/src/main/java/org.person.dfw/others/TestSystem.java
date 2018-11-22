
/**  
 * @Title: TestSystem.java
 * @Package: org.person.dfw.others
 * @author: 丰伟
 * @date: 2017年6月23日 上午11:02:21
 * @version: V1.0  
 */ 
package org.person.dfw.others;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;

/**
 * @moudle: TestSystem 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年6月23日 上午11:02:21
 *
 */
public class TestSystem {
	
	
	@Test public void getProperty(){
		
		//环境变量
		//指定的环境变量
		System.out.println("环境变量-----------------------------------");
		System.out.println(System.getenv("COMPUTERNAME"));
		//全部环境变量
		Map<String, String> envmMap = System.getenv();
		for (Entry<String, String> entry : envmMap.entrySet()) {
			System.out.println(entry.getKey()+"\t\t\t"+entry.getValue());
		}
		
		//获取系统的相关属性，包括文件编码、操作系统名称、区域、用户名等，此属性一般由jvm自动获取，不能设置，若无则获取默认值
		//指定的系统属性，指定默认值
		System.out.println("系统属性-----------------------------------");
		System.out.println(System.getProperty("AAAA","AAAA"));
		//获取全部系统的相关属性
		Properties properties = System.getProperties();
		for (Object key : properties.keySet()) {
			System.out.println(key+"\t\t\t"+properties.getProperty(key.toString()));
		}
		
	}
	
	@Test public void arraycopy(){
	    int[] ids = { 1, 2, 3, 4, 5 };  
	    System.out.println(Arrays.toString(ids)); // [1, 2, 3, 4, 5]  
	    // 把从索引0开始的2个数字复制到索引为3的位置上  
	    System.arraycopy(ids, 0, ids, 3, 2);  
	    System.out.println(Arrays.toString(ids)); // [1, 2, 3, 1, 2]  
	    
	    // 测试复制到别的数组上  
	    // 将数据的索引1开始的3个数据复制到目标的索引为0的位置上  
	    int[] ids2 = new int[6];  
	    System.arraycopy(ids, 1, ids2, 0, 3);  
	    System.out.println(Arrays.toString(ids2)); // [2, 3, 1, 0, 0, 0]  
	    // 此功能要求  
	    // 源的起始位置+长度不能超过末尾  
	    // 目标起始位置+长度不能超过末尾  
	    // 且所有的参数不能为负数  
	    try {  
	      System.arraycopy(ids, 0, ids2, 0, ids.length + 1);  
	    } catch (IndexOutOfBoundsException ex) {  
	      // 发生越界异常，数据不会改变  
	      System.out.println("拷贝发生异常：数据越界。");  
	    }  
	    System.out.println(Arrays.toString(ids2)); // [2, 3, 1, 0, 0, 0]  
	    // 如果是类型转换问题  
	    Object[] o1 = { 1, 2, 3, 4.5, 6.7 };  
	    Integer[] o2 = new Integer[5];  
	    System.out.println(Arrays.toString(o2)); // [null, null, null, null, null]  
	    try {  
	      System.arraycopy(o1, 0, o2, 0, o1.length);  
	    } catch (ArrayStoreException ex) {  
	      // 发生存储转换，部分成功的数据会被复制过去  
	      System.out.println("拷贝发生异常：数据转换错误，无法存储。");  
	    }  
	    // 从结果看，前面3个可以复制的数据已经被存储了。剩下的则没有  
	    System.out.println(Arrays.toString(o2)); // [1, 2, 3, null, null]  
	}
	
	@Test public void getTime(){
		  //获取系统当前的时间毫秒currentTimeMillis()（返回当前时刻距离UTC 1970.1.1 00:00的时间差）
		  Long time = System.currentTimeMillis();
		  System.out.println(time);

		  Long time1 = System.nanoTime();//主要用于计算时间差单位纳秒
		  Long time3 = System.currentTimeMillis();
		  for(Long i =0l ;i <999l; i++){}
		  Long time2 = System.nanoTime();
		  Long time4 = System.currentTimeMillis();
		  System.out.println(time2 - time1+ " : " +(time4 - time3));
	}
	
	@Test public void identityHashCode(){
		  //str1 str2为两个不同的String对象 
		  String str1 = new String("helloWorld");
		  String str2 = new String("helloWorld");
		  //由于String类重写了hashCode()方法 所以他们的HashCode是一样的
		  System.out.println(str1.hashCode()+" : "+str2.hashCode());
		  //由于他们不是同一个对象所以他们的计算出来的HashCode时不同的。
		  //实际上该方法使用的时最原始的HashCode计算方法即Object的HashCode计算方法
		  System.out.println(System.identityHashCode(str1) + " : "+ System.identityHashCode(str2));
		  String str3 = "hello";
		  String str4 = "hello";
		  //由于他们引用的是常量池中的同一个对象 所以他们的HashCode是一样的
		  System.out.println(System.identityHashCode(str3) + " : "+ System.identityHashCode(str4));

	}
}
