package org.person.dfw.generic;

import java.util.Date;

import org.junit.Test;



/**
 * @moudle: TestGeneric_局限于约束 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年9月8日 上午9:34:26
 *
 */
public  class  TestGeneric_局限与约束<T> {
	
	
	@Test public void test1(){
		
		//1、不能用基本类型实例化类型参数
		//	Pair<double> p1 = new Pair<double>();					//产生编译错误
	}
	
	@Test  public void test2(){
		//2、运行时类型查询instanceof只适用于原始类型
		Pair<Date> p2 = new Pair<Date>();
		System.out.println(p2 instanceof Pair);
		System.out.println(p2 instanceof Pair<?>);
		
		//强制类型转换时
//		Pair<String> p = (Pair<String>) p2;							//编译错误：Cannot cast from Pair<Date> to Pair<String>
	}
	
	@Test public void test3(){
//		class Problem<T> extends Exception { /* . . . */ }			//编译错误：The generic class Problem<T> may not subclass java.lang.Throwable
		
		
	}
	
	/**4、参数化类型的数组不合法*/
	@Test public void test4(){
		//不能声明参数化类型的数组
//		Pair<String>[] table = new Pair<String>[10]; 				// 编译错误：Cannot create a generic array of Pair<String> 
		
		

	}
	
	/**5、不能实例化类型变量*/
	@Test public void test5(){
//		new T();													//编译错误：
		
	}
}

class Pair<T> {
	private T first;
	private T second;

	public Pair() { first = null; second = null; }
	public Pair(T first, T second) { this.first = first;  this.second = second; }

	public T getFirst() { return first; }
	public T getSecond() { return second; }

	public void setFirst(T newValue) { first = newValue; }
	public void setSecond(T newValue) { second = newValue; }
}

