package org.person.dfw.refelct;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;

import org.junit.Test;


/**
 * @moudle: TestField 
 * @version:v1.0
 * @author: 丰伟
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