package org.person.dfw.refelct;

import org.junit.Test;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.xml.ws.Action;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by dell on 2018/4/13
 */
public class TestMethodReflect {


    public TestMethodReflect() throws NoSuchMethodException {
    }

    public void method1(){
    }

    /**  注意 异常会在构造方法中抛出 【有意思】 */
    Method method1 = TestMethodReflect.class.getMethod("method1", null);

    @Action
    public String method2( String[] strings){
        return strings[0];
    }

    @Test
    public void construct() throws NoSuchMethodException {

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

    @Resource
    class  A{}
    public A returnTypeMethod(){
        return null;
    }

    public String nonReturnTypeMethod() {
        return "";
    }

    /**  不晓得和getReturnType啥区别 */
    @Test
    public void getAnnotatedReturnType() throws NoSuchMethodException {
        Method aa = TestMethodReflect.class.getMethod("returnTypeMethod", null);
        // AnnotatedType接口 只有getType方法
        AnnotatedType annotatedReturnType = aa.getAnnotatedReturnType();

        assertEquals(A.class, annotatedReturnType.getType());

        assertEquals(void.class, method1.getAnnotatedReturnType().getType());


        Method bb = TestMethodReflect.class.getMethod("nonReturnTypeMethod", null);
        assertEquals(String.class, bb.getAnnotatedReturnType().getType());
    }

}
