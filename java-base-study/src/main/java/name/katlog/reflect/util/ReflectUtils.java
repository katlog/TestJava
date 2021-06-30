package name.katlog.reflect.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @moudle: ReflectUtils 
 * @version:v1.0
 * @Description: 反射工具
 * @author: fw
 * @date: 2017年2月7日 上午11:55:14
 *
 */
public class ReflectUtils {
	
	/**
	 * 获取bean对象中的无参的get方法的全部值，存入map中
	 * <p>Title: getGetMethod</p>
	 * <p>author : fw</p>
	 * <p>date : 2017年2月7日 上午11:58:16</p>
	 * @param obj
	 * @return 方法名和get方法值组成的map
	 */ 
	public static <T> Map<String, Object>  getGetMethod(T obj){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Method[] methods = obj.getClass().getDeclaredMethods();
		if (methods.length>0) {
			try {
				for (Method method : methods) {
					method.setAccessible(true);
					if (method.getName().startsWith("get")) {
						Class<?>[] parameterTypes = method.getParameterTypes();
						if (parameterTypes.length < 1) {
							resultMap.put(method.getName(),method.invoke(obj, new Class[] {}));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultMap;
	}
	
	/**
	 * 获取对象中的 全部参数为空取得的值
	 * <p>Title: getGetMethod</p>
	 * <p>author : fw</p>
	 * <p>date : 2017年2月7日 上午11:58:16</p>
	 * @param obj
	 * @return 方法名和方法值组成的map
	 *
	 */ 
	public static Map<String, String>  getMethodNoneParam(Object obj){
		Map<String, String> resultMap = new HashMap<String, String>();
		Class<? extends Object> class1 = obj.getClass();
		Method[] methods = obj.getClass().getMethods();
//		ArraysUtils.formatPrintArray(Arrays.asList(methods));
		//FW 有待完善啊！！！
		if (methods.length>0) {
			try {
				for (Method method : methods) {
					Class<?>[] parameterTypes = method.getParameterTypes();
					method.setAccessible(true);
					IntBuffer intBuffer = (IntBuffer)obj;
					if (parameterTypes.length < 1&&Modifier.isPublic(method.getModifiers())) {
						resultMap.put(method.getName(),method.invoke(intBuffer, new Class[] {}).toString());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultMap;
	}
	
	
	public static<T> void print(T obj) {
		
		if (obj == null) {
			return;
		}
		Class<? extends Object> class1 = obj.getClass();
		//注解
		printAnnotions(class1);
		//类修饰符
		System.out.print(Modifier.toString(class1.getModifiers()));
		//类的参数类型
		TypeVariable<?>[] typeParameters = class1.getTypeParameters();
		//类名
		System.out.print(" "+class1.getSimpleName());
		
	}

	private static void printAnnotions(Class<? extends Object> class1) {

		//类注解
		Annotation[] annotations = class1.getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.println("@"+annotation.getClass().getSimpleName());
		}
	}
	
	public static void main(String[] args) {

		print(new ArrayList<>());
	}

}
