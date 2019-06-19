package org.person.dfw.refelct;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


/**
 * @moudle: TestField 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年11月16日 下午5:18:11
 *
 */
public class TestField {

	/** 类型 */
	@Test
	public void getType(){
		Field[] fields = User.class.getDeclaredFields();
		for (Field field : fields) {
			Class<?> type = field.getType();
			System.out.println(type);
		}
	}

	/** 泛型类型 */
	@Test
	public void getGenericType(){
		Field[] fields = User.class.getDeclaredFields();
		for (Field field : fields) {
			Type genericType = field.getGenericType();
			System.out.println("genericType = " + genericType);
		}

	}

	/** 不太清楚 这个方法的用意 */
	@Test
	public void getAnnotatedType(){
		Field[] fields = User.class.getDeclaredFields();
		for (Field field : fields) {
			AnnotatedType annotatedType = field.getAnnotatedType();
			System.out.println("annotatedType = " + annotatedType);
		}
	}

	/** 获取字段上的注解：忽略继承注解 */
	@Test
	public void getDeclaredAnnotations(){
		Field[] fields = User.class.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			System.out.println(Arrays.toString(annotations));
		}
	}

	/** 获取字段上的注解 */
	@Test
	public void getAnnotations(){
		Field[] fields = User.class.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			System.out.println(Arrays.toString(annotations));
		}
	}

	static class Data{
		int a = 0;
		static int b = 1;
	}
	static abstract class AbstractData{
		static int c = 2;
		public AbstractData() {
		}
		public AbstractData(int b) {
			c = b;
		}
	}
	interface InterfaceData{
		int d = 3;
	}
	/** 获取属性值 */
	@Test
	public void get() throws NoSuchFieldException, IllegalAccessException {

		/** 实例属性 用实例 */
		Field aField = Data.class.getDeclaredField("a");
		assertEquals(0, aField.get(new Data()));

		/** 静态属性 用null即可(一般类) */
		Field bField = Data.class.getDeclaredField("b");
		assertEquals(1, bField.get(null));

		/** 静态属性 用null即可（抽象类） */
		Field cField = AbstractData.class.getDeclaredField("c");
		assertEquals(2,cField.get(null));

		/** 静态属性 用null即可（接口） */
		Field dField = InterfaceData.class.getDeclaredField("d");
		assertEquals(3,dField.get(null));
	}
}

class User<T>{
	
	public String userName;

	@Deprecated
	public String password;
	
	private int age;
	
	private T reserve;
	
	private User<T> friend;
	
	private User<? extends User<T>> enemy;
	
	private God<String,String> god;

	private God god1;

	private God<T, T> god2;
}

class God<K,V>{
	
}