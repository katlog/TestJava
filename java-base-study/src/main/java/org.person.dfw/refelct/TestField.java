package org.person.dfw.refelct;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
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
	
	@Test public void test(){
		
		Field[] fields = User.class.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Class<?> type = field.getType();
			
			TypeVariable<?>[] typeParameters = type.getTypeParameters();
			System.out.println(type);
			System.out.println(Arrays.toString(typeParameters));
			if (typeParameters.length>0) {
				System.out.println(typeParameters[0] );
				System.out.println(typeParameters[0] instanceof ParameterizedType);
				System.out.println(typeParameters[0] instanceof TypeVariable);
				System.out.println(typeParameters[0] instanceof WildcardType);
			}
		}
		
	}

	
	
}

class User<T>{
	
	public String userName;
	
	public String password;
	
	private int age;
	
	private T reserve;
	
	private User<T> friend;
	
	private User<? extends User<T>> enemy;
	
	private God<String,String> god;
}

class God<K,V>{
	
}