package name.katlog.reflect;

import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import javax.xml.ws.Action;
import java.lang.reflect.*;
import java.util.List;

import static org.junit.Assert.*;

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


    @Test
    public void _construct() throws NoSuchMethodException {
        assertNotNull(method1);
    }

    @Test
    public void getName() {
        assertEquals("method1", method1.getName());
    }

    @Test
    public void getDeclaringClass() {
        assertEquals(TestMethodReflect.class, method1.getDeclaringClass());
    }

    private void parameterMethod0(List aListParam){}
    @Test
    public void getParameters() throws NoSuchMethodException {
        Parameter[] parameters = clazz.getDeclaredMethod("parameterMethod0", List.class).getParameters();
        assertEquals(1, parameters.length);

        Parameter parameter = parameters[0];

        assertEquals("arg0", parameter.getName());
        assertEquals(List.class, parameter.getType());
        assertEquals(0, parameter.getAnnotations().length);
        // assertTrue(Modifier.isPrivate(parameter.getModifiers()));
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



    /** 方法的参数是不是可变数量参数 */
    public String varArgsMethod(String... strings){
        return strings[0];
    }
    @Test
    public void isVarArgs() throws NoSuchMethodException {
        Method varArgsMethod = TestMethodReflect.class.getMethod("varArgsMethod", String[].class);
        assertTrue(varArgsMethod.isVarArgs());
    }


    /** 反射调用 */
    public int run(){
        System.out.println("run..." );
        return 1;
    }
    public static int runStatic(){
        System.out.println("run static..." );
        return 1;
    }
    @Test
    public void invoke() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 调用实例方法
        Method runMethod = TestMethodReflect.class.getMethod("run", null);
        Object result = runMethod.invoke(this, null);
        assertEquals(1, result);

        // 调用静态方法[obj]
        Method runStatic = TestMethodReflect.class.getMethod("runStatic", null);
        result = runStatic.invoke(null, null);
        assertEquals(1, result);

        result = runStatic.invoke(this, null);
        assertEquals(1, result);
    }

}
