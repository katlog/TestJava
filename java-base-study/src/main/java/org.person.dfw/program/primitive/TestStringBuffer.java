package org.person.dfw.program.primitive;

import org.junit.Test;

/**
 * @moudle: TestStringBuffer 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年7月26日 下午5:43:42
 *
 */
public class TestStringBuffer {
	
	@Test public void append(){
        StringBuffer buf = new StringBuffer() ; 
        buf.append("Hello ") ;                  // 向StringBuffer中添加内容  
        buf.append("World").append("!!!") ;     // 可以连续调用append()方法  
        buf.append("\n") ;                      // 添加一个转义字符  
        buf.append("数字 = ").append(1).append("\n") ;    // 添加数字  
        buf.append("字符 = ").append('C').append("\n");    // 添加字符  
        buf.append("布尔 = ").append(true) ;  // 添加布尔值  
        System.out.println(buf) ;           // 直接输出对象，调用toString() 
	}
	
	@Test public void insert(){//offset的左边
        StringBuffer buf = new StringBuffer("World!!") ; 
        buf.insert(0,"Hello ") ;//最前添加
        System.out.println(buf) ;  
        buf.insert(buf.length(),"MLDN~") ;  // 在最后添加内容  
        System.out.println(buf) ;  
	}
	
	@Test public void reverse(){
		StringBuffer buf = new StringBuffer("Hello World!!") ;  
        System.out.println(buf.reverse()) ; //!!dlroW olleH       
        System.out.println(buf.reverse()) ; //Hello World!!      
	}
	
	@Test public void replace(){
		// Hello World!!
		// 0123456789012
        StringBuffer buf = new StringBuffer("Hello World!!") ;  
        buf.replace(6,11,"LiXingHua") ; //[start,end) 
        System.out.println("内容替换之后的结果：" + buf) ;      
	}
	
	@Test public void subString(){
		// Hello World!!
		// 0123456789012
        StringBuffer buf = new StringBuffer("Hello World!!") ;  
        String str = buf.substring(6,12) ;  // [start,end) 
        System.out.println("内容截取之后的结果：" + str) ;      
	}
	
	@Test public void delete(){
		// Hello World!!
		// 0123456789012
        StringBuffer buf = new StringBuffer("Hello World !") ;  
        String str = buf.delete(6,10).toString() ;  // [start,end)
        System.out.println("删除之后的结果：" + buf) ; //Hello d!!
        buf = buf.delete(buf.length()-1, buf.length());
        System.out.println(buf);
	}
	
}
