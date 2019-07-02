package org.person.dfw.refelct;

import org.junit.Test;
import sun.misc.Contended;
import sun.reflect.generics.factory.CoreReflectionFactory;
import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;
import sun.reflect.generics.scope.ClassScope;
import sun.reflect.generics.scope.Scope;
import sun.reflect.generics.tree.FieldTypeSignature;

import javax.annotation.Tainted;
import java.lang.annotation.Annotation;
import java.lang.reflect.TypeVariable;
import java.util.*;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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


    /** 泛型： */
    class Frob {}
    class Fnorkle {}
    class Quark<Q> {}
    class Particle<POSITION,MOMENTUM> {}
    @Test
    public void getTypeParameters(){
        List<Frob> list = new ArrayList<Frob>();
        Map<Frob,Fnorkle> map = new HashMap<Frob,Fnorkle>();
        Quark<Fnorkle> quark = new Quark<Fnorkle>();
        Particle<Long,Double> p = new Particle<Long,Double>();

        System.out.println(Arrays.toString(list.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        TypeVariable<? extends Class<? extends Quark>>[] typeParameters = quark.getClass().getTypeParameters();
        TypeVariable<? extends Class<? extends Quark>> typeParameter = typeParameters[0];
        System.out.println(Arrays.toString(typeParameters));
        System.out.println(Arrays.toString(p.getClass().getTypeParameters()));

        TypeVariableImpl<Class<Quark>> q = TypeVariableImpl.make(Quark.class, "Q", new FieldTypeSignature[]{}, CoreReflectionFactory.make(Quark.class, ClassScope.make(Quark.class)));
        System.out.println("q = " + q);
        boolean equals = typeParameter.equals(q);
        System.out.println("equals = " + equals);
    }


    @Test
    public void isInstance(){

        assertTrue(Boolean.class.isInstance(false));
        assertTrue(Boolean.class.isInstance(true));

        assertTrue(Boolean.class.isInstance(Boolean.TRUE));
        assertTrue(Boolean.class.isInstance(Boolean.FALSE));

        // 不知为为何这两种就不行
        // assertTrue(boolean.class.isInstance(true));
        // assertTrue(boolean.class.isInstance(false));

        assertTrue(Boolean.valueOf("true") instanceof  Boolean);
    }

}
