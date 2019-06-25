package org.person.dfw.refelct;

import org.junit.Before;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;

import javax.annotation.Resource;
import javax.xml.ws.Action;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by dell on 2018/4/13
 */
public class TestMethodReflect {


    private Class<TestMethodReflect> clazz;

    @Before
    public void before(){
        clazz = TestMethodReflect.class;
    }

    public TestMethodReflect() throws NoSuchMethodException {
    }
    /**  注意 异常会在构造方法中抛出 【有意思】 */
    Method method1 = TestMethodReflect.class.getMethod("method1", null);

    public void method1(){
    }

    @Action
    public String method2( String[] strings){
        return strings[0];
    }

    @Test
    public void _construct() throws NoSuchMethodException {

        Method test = TestMethodReflect.class.getMethod("method1", null);
        assertNotNull(test);

        Method method2 = TestMethodReflect.class.getMethod("method2", String[].class);
        method2.isVarArgs();
    }

    @Test
    public void getName() {
        assertEquals("method1", method1.getName());
    }

    @Test
    public void getDeclaringClass() {
        assertEquals(TestMethodReflect.class, method1.getDeclaringClass());
    }

    private void genericParameterMethod0(List list){}
    private void genericParameterMethod1(List<String> list){}
    private void genericParameterMethod2(List<?> list){}
    private <T> void genericParameterMethod3(List<T> list){}
    private <T> void genericParameterMethod4(T t){}
    /** 获取泛型化的参数类型 */
    @Test
    public void getGenericParameterTypes() throws NoSuchMethodException {

        Type[] genericParameterTypes0 = clazz.getDeclaredMethod("genericParameterMethod0", List.class).getGenericParameterTypes();
        System.out.println("genericParameterTypes = " + genericParameterTypes0);

        Type[] genericParameterTypes1 = clazz.getDeclaredMethod("genericParameterMethod1", List.class).getGenericParameterTypes();
        System.out.println("genericParameterTypes = " + genericParameterTypes1);

        Type[] genericParameterTypes2 = clazz.getDeclaredMethod("genericParameterMethod2", List.class).getGenericParameterTypes();
        System.out.println("genericParameterTypes = " + genericParameterTypes2);

        Type[] genericParameterTypes3 = clazz.getDeclaredMethod("genericParameterMethod3", List.class).getGenericParameterTypes();
        System.out.println("genericParameterTypes = " + genericParameterTypes3);

        Type[] genericParameterTypes4 = clazz.getDeclaredMethod("genericParameterMethod4", Object.class).getGenericParameterTypes();
        System.out.println("genericParameterTypes = " + genericParameterTypes4);

    }

    private <T extends String> void genericParameterMethod5(T t){}
    /** 获取参数类型:其实和getDeclaredMethod(String name,Class<?> parameterTypes)中parameterTypes一致</>) */
    @Test
    public void getParameterTypes() throws NoSuchMethodException {

        assertArrayEquals(new Class[]{List.class},
                clazz.getDeclaredMethod("genericParameterMethod0", List.class).getParameterTypes());
        assertArrayEquals(new Class[]{List.class},
                clazz.getDeclaredMethod("genericParameterMethod1", List.class).getParameterTypes());
        assertArrayEquals(new Class[]{List.class},
                clazz.getDeclaredMethod("genericParameterMethod2", List.class).getParameterTypes());
        assertArrayEquals(new Class[]{List.class},
                clazz.getDeclaredMethod("genericParameterMethod3", List.class).getParameterTypes());
        /** 编译器类型擦除到Object  */
        assertArrayEquals(new Class[]{Object.class},
                clazz.getDeclaredMethod("genericParameterMethod4", Object.class).getParameterTypes());
        /** 编译器类型擦除到String */
        assertArrayEquals(new Class[]{String.class},
                clazz.getDeclaredMethod("genericParameterMethod5", String.class).getParameterTypes());
    }

    /** 获取泛型方法的类型参数：即method 3 4 5中void前的<T>  */
    @Test
    public void getTypeParameters() throws NoSuchMethodException {

        TypeVariable<Method>[] parameterMethod0s = clazz.getDeclaredMethod("genericParameterMethod0", List.class).getTypeParameters();
        System.out.println("parameterMethod0s = " + parameterMethod0s);

        TypeVariable<Method>[] parameterMethod1s = clazz.getDeclaredMethod("genericParameterMethod1", List.class).getTypeParameters();
        System.out.println("parameterMethod1s = " + parameterMethod1s);

        TypeVariable<Method>[] genericParameterMethod2s = clazz.getDeclaredMethod("genericParameterMethod2", List.class).getTypeParameters();
        System.out.println("genericParameterMethod2s = " + genericParameterMethod2s);

        TypeVariable<Method>[] genericParameterMethod3s = clazz.getDeclaredMethod("genericParameterMethod3", List.class).getTypeParameters();
        System.out.println("genericParameterMethod3s = " + genericParameterMethod3s);

        TypeVariable<Method>[] genericParameterMethod4s = clazz.getDeclaredMethod("genericParameterMethod4", Object.class).getTypeParameters();
        System.out.println("genericParameterMethod4s = " + genericParameterMethod4s);

        TypeVariable<Method>[] genericParameterMethod5s = clazz.getDeclaredMethod("genericParameterMethod5", String.class).getTypeParameters();
        System.out.println("genericParameterMethod5s = " + genericParameterMethod5s);
    }


    @Resource
    class  A{}
    public A returnTypeMethod(){
        return null;
    }

    public String nonReturnTypeMethod() {
        return "";
    }

    /** 获取方法的返回值类型 */
    @Test
    public void getReturnType() throws NoSuchMethodException {

        assertEquals(A.class, clazz.getMethod("returnTypeMethod", null).getReturnType());

        assertEquals(void.class, clazz.getMethod("method1", null).getReturnType());

        assertEquals(String.class, clazz.getMethod("nonReturnTypeMethod", null).getAnnotatedReturnType().getType());

    }

    /**  不晓得和getReturnType啥区别 */
    @Test
    public void getAnnotatedReturnType() throws NoSuchMethodException {
        Method aa = clazz.getMethod("returnTypeMethod", null);
        // AnnotatedType接口 只有getType方法
        AnnotatedType annotatedReturnType = aa.getAnnotatedReturnType();

        assertEquals(A.class, annotatedReturnType.getType());

        assertEquals(void.class, clazz.getMethod("method1", null).getAnnotatedReturnType().getType());

        Method bb = clazz.getMethod("nonReturnTypeMethod", null);
        assertEquals(String.class, bb.getAnnotatedReturnType().getType());
    }

}
