package org.person.dfw.refelct;

import org.junit.Assert;
import org.junit.Test;
import sun.misc.Contended;

import javax.annotation.Tainted;
import java.lang.annotation.Annotation;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by fw on 2019/6/14
 */
public class TestClass {

    @Contended
    @Tainted
    static class DemoClass{

        public class PublicInnerClass{}
        public static class PublicStaticInnerClass{}
        private class PrivateInnerClass{}
        protected class ProtectedInnerClass{}
        // 内部类无法再嵌套接口：inner class can not have static declarations
    }

    private static final Class clazz = DemoClass.class;


    /** 获取内部类:public的 */
    @Test
    public void getClasses(){
        Class[] classes = clazz.getClasses();
        assertArrayEquals(new Class[]{DemoClass.PublicStaticInnerClass.class,DemoClass.PublicInnerClass.class},
                classes);
    }

    /** 获取全部的内部类 */
    @Test
    public void getDeclaredClasses(){
        Class[] classes = clazz.getDeclaredClasses();
        assertArrayEquals(new Class[]{
                        DemoClass.ProtectedInnerClass.class,
                        DemoClass.PrivateInnerClass.class,
                        DemoClass.PublicStaticInnerClass.class,
                        DemoClass.PublicInnerClass.class
                        }
                    ,classes);
    }


    /** 类上的注解（多个，重复注解） */
    @Test
    public void getAnnotationsByType(){
        Annotation[] annotations = clazz.getDeclaredAnnotationsByType(Contended.class);

        assertEquals(clazz.getDeclaredAnnotation(Contended.class),annotations[0]);
    }

    /** 类上的注解（单个） */
    @Test
    public void getDeclaredAnnotation(){
        Annotation annotation = clazz.getDeclaredAnnotation(Contended.class);

        assertEquals(clazz.getDeclaredAnnotationsByType(Contended.class)[0],annotation);
    }



}
