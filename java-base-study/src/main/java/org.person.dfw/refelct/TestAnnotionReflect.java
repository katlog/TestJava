/**
 * @Title: TestAnnotionReflect.java
 * @Package: org.person.dfw.refelct
 * @author: 丰伟
 * @date: 2017年4月13日 上午9:31:27
 * @version: V1.0
 */
package org.person.dfw.refelct;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * @moudle: TestAnnotionReflect
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年4月13日 上午9:31:27
 *
 */
public class TestAnnotionReflect {
    /**
     * 类注解
     * <p>date : 2017年4月13日 上午9:36:31</p>
     */
    @Test
    public void classAnnotion() {
        Class aClass = TheClass.class;
        // 1、获取所有注解
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof MyClassAnnotation) {
                MyClassAnnotation myAnnotation = (MyClassAnnotation) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }
        // 2、获取特定注解
        Annotation annotation = aClass.getAnnotation(MyClassAnnotation.class);
        if (annotation instanceof MyClassAnnotation) {
            MyClassAnnotation myAnnotation = (MyClassAnnotation) annotation;
            System.out.println("name: " + myAnnotation.name());
            System.out.println("value: " + myAnnotation.value());
        }
    }

    /**
     * 字段注解
     * <p>date : 2017年4月13日 上午9:36:31</p>
     * 
     * @throws SecurityException
     * @throws NoSuchFieldException
     */
    @Test
    public void fieldAnnotion()
        throws NoSuchFieldException, SecurityException {
        Class aClass = TheFieldClass.class;
        Field field = aClass.getField("myField"); // 获取方法对象</pre>
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof MyFieldAnnotion) {
                MyFieldAnnotion myAnnotation = (MyFieldAnnotion) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }
    }

    /**
     * 方法注解
     * <p>date : 2017年4月13日 上午9:36:31</p>
     */
    @Test
    public void methodAnnotion()
        throws NoSuchMethodException, SecurityException {
        Class aClass = TheMethodClass.class;
        Method method = aClass.getMethod("doSomething", Void.class); // 获取方法对象
        Annotation[] annotations = method.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof MyMethodAnnotion) {
                MyMethodAnnotion myAnnotation = (MyMethodAnnotion) annotation;
                System.out.println("name: " + myAnnotation.name());
                System.out.println("value: " + myAnnotation.value());
            }
        }
    }

    /**
     * 参数注解
     * <p>date : 2017年4月13日 上午10:17:09</p>
     * 
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    @Test
    public void paramAnnotion()
        throws NoSuchMethodException, SecurityException {
        Class aClass = TheParamClass.class;
        Method method = aClass.getMethod("doSomethingElse", Void.class);// 获取方法对象
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Class[] parameterTypes = method.getParameterTypes();

        int i = 0;
        for (Annotation[] annotations : parameterAnnotations) {
            Class parameterType = parameterTypes[i++];

            for (Annotation annotation : annotations) {
                if (annotation instanceof MyParamAnnotion) {
                    MyParamAnnotion myAnnotation = (MyParamAnnotion) annotation;
                    System.out.println("param: " + parameterType.getName());
                    System.out.println("name : " + myAnnotation.name());
                    System.out.println("value: " + myAnnotation.value());
                }
            }
        }
    }
}

/** --- 丰伟 2017年4月13日 start 类注解反射 start--- */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface MyClassAnnotation {
    public String name();

    public String value();
}

@MyClassAnnotation(name = "someName", value = "Hello World")
class TheClass {
}

/** --- 丰伟 2017年4月13日 end 类注解反射 end--- */

/** --- 丰伟 2017年4月13日 start 属性注解反射 start--- */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MyFieldAnnotion {
    public String name();

    public String value();
}

class TheFieldClass {
    @MyFieldAnnotion(name = "someName", value = "Hello World")
    public String myField = null;
}

/** --- 丰伟 2017年4月13日 end 属性注解反射 end--- */

/** --- 丰伟 2017年4月13日 start 方法注解反射 start--- */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface MyMethodAnnotion {
    public String name();

    public String value();
}

class TheMethodClass {
    @MyMethodAnnotion(name = "someName", value = "Hello World")
    public void doSomething() {
    }
}

/** --- 丰伟 2017年4月13日 end 方法注解反射 end--- */

/** --- 丰伟 2017年4月13日 start 参数注解反射 start--- */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@interface MyParamAnnotion {
    public String name();

    public String value();
}

class TheParamClass {
    public static void doSomethingElse(@MyParamAnnotion(name = "aName", value = "aValue") String parameter) {
    }
}
/** --- 丰伟 2017年4月13日 end 参数注解反射 end--- */

