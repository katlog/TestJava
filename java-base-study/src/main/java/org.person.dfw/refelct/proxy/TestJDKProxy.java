package org.person.dfw.refelct.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 *使用动态代理实现，接口、接口实现类的方法，增加包裹打印
 */
public class TestJDKProxy {
    
    @Test public void proxy1(){
        MyInterface target = new MyInterfaceImpl();
        /**
         * 三个参数
         * 1. 类加载器(Class Loader)
         * 2. 需要实现的接口数组
         * 3. InvocationHandler接口。所有动态代理类的方法调用，都会交由InvocationHandler接口实现类里的invoke()方法去处理。
         */
        MyInterface proxyInstance = (MyInterface)Proxy.newProxyInstance(target.getClass().getClassLoader()
            ,target.getClass().getInterfaces(), new MyInvocation(target));
        proxyInstance.test1();
        proxyInstance.test2();
    }
    
    @Test public void proxy2(){
        new MyInterfacePorxy(new MyInterfaceImpl()).create().test1();
        new MyInterfacePorxy(new MyInterfaceImpl()).create().test2();
    }
}
interface MyInterface{
    public void test1();
    public void test2();
}
class MyInterfaceImpl implements MyInterface{
    @Override
    public void test1() {
        System.out.println("excute test1.... ");
    }
    @Override
    public void test2() {
        System.out.println("excute test2.... ");
    }
}

class MyInvocation implements InvocationHandler{
    Object target;
    
    public MyInvocation(Object target){
        this.target = target;
    }
    /**
     * 三个参数
     * @param proxy 动态代理类的引用，通常情况下不需要它。但可用getClass()方法，得到proxy的Class类从而取得实例的类信息，如方法列表，annotation等
     * @param method 方法对象的引用，代表被动态代理类调用的方法。从中可得到方法名，参数类型，返回类型等等
     * @param args 对象数组，代表被调用方法的参数。注意基本类型(int,long)会被装箱成对象类型(Interger, Long)
     */ 
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
        System.out.println("enter  proxy");
        Object obj = method.invoke(target, args);
        System.out.println("out  proxy");
        return obj;
    }
}
/**
 * 1、使用局部内部类将InvocationHandler的实现类嵌套起来，以优化代码结构
 * 2、局部内部类，外部访问不到，可以增强其安全性和定制性
 * 
 * */
class MyInterfacePorxy{
    MyInterface target;
    public MyInterfacePorxy(MyInterface target){
        this.target = target;
    }
    public MyInterface create(){
        class MyInvocation implements InvocationHandler{
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
                System.out.println("enter another  proxy");
                Object obj = method.invoke(target, args);
                System.out.println("out another proxy");
                return obj;
            }
        }
        MyInterface proxyInstance = (MyInterface)Proxy.newProxyInstance(target.getClass().getClassLoader()
            ,target.getClass().getInterfaces(), new MyInvocation());
        return proxyInstance;
    }
    
}