package org.person.dfw.program.primitive;

import java.util.Map;

import org.junit.Test;
import org.person.dfw.refelct.util.BeanUtil;
import org.person.dfw.util.Print;
import org.person.dfw.util.collections.MapUtil;


/**
 * 数据类型测试
 * @moudle: TestDataType 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月10日 下午4:34:57
 *
 */
public class TestDataType {
	
	
	@Test public void _byte(){
		//最大值  最小值
		System.out.println(Byte.MAX_VALUE);		//127
		System.out.println(Byte.MIN_VALUE);		//-128
		
		//溢出时    
		Print.bin(2111);						//0000 0000 0000 0000 0000 1000 0011 1111
		Print.bin((byte)63);					//								0011 1111 
		Print.bin((byte)2111);					//								0011 1111 
		System.out.println((byte)2111);			// 63
	}
	
	@Test public void _short(){
		int i = -111111;
		while (i!=0) {
			System.out.print(i&1);
			i = i>>1;
		}
	}
	
	@Test public void _int(){
		
	}
	
	@Test public void _long(){
		
	}
	
	@Test public void _float(){
		
	}
	
	@Test public void _double(){
		
        System.out.println("double最大值："+Double.MAX_VALUE);
        System.out.println("double最小值："+Double.MIN_VALUE);
        
        System.out.println("正无穷大："+Double.POSITIVE_INFINITY);
        System.out.println("1.0/0==POSITIVE_INFINITY："+(1.0/0==Double.POSITIVE_INFINITY));            //true   
        System.out.println("-1.0/-0==POSITIVE_INFINITY："+(-1.0/-0==Double.POSITIVE_INFINITY));          //false
        
        System.out.println("负无穷大："+Double.NEGATIVE_INFINITY);
        System.out.println("-1.0/0==NEGATIVE_INFINITY："+(-1.0/0==Double.NEGATIVE_INFINITY));           //true
        System.out.println("-1.0/-0==NEGATIVE_INFINITY：)"+(-1.0/-0==Double.NEGATIVE_INFINITY));          //true
        
        System.out.println("不是数字："+Double.NaN);
        System.out.println("Double.NaN==Double.NaN："+(Double.NaN==Double.NaN));                     // ==Double.NaN  永远不会true的
//      System.out.println(0/0==Double.NaN);                            //报错
        System.out.println("√-1："+(Math.sqrt(-1)==Double.NaN));                  //false
        
        System.out.println("浮点值的误差："+ (2.0-1.1));
	}

	
	@Test public void _char() throws InstantiationException, IllegalAccessException{
		
		//ASCII码范围 0~255  256个
		for (int i = 0; i < 256; i++) {
			System.out.printf("\t|%s|",(char)i);
			if( i %15==0)
				System.out.println();
		}
		
		System.out.println( (char)257);
		
		//character中的常量
		Map<String, Object> map = BeanUtil.getPublicFinalStatictPro(Character.class);
		MapUtil.formatPrint(map);
		
		//unicode中 2048个非法字符  全部为?
		for (int i = '\ud800'; i <= '\udfff'; i++) {
//			System.out.printf("\t|%s|",(char)i);
//			if( i %15==0)
//				System.out.println();
		}
	}
	
	/**数组*/
	@Test public void _array(){
		
		//创建数组对象    并初始化数组对象
		int[] a = {1,2,4,5,6,7,8,9};
		//初始化匿名数组 并赋值给a
		a = new int[]{1,3,4,5,6};
		
	}
}
