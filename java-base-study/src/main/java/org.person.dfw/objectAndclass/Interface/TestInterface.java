
/**  
 * @Title: TestInterface.java
 * @Package: org.person.dfw.objectAndclass.Interface
 * @author: 丰伟
 * @date: 2017年4月20日 上午9:23:49
 * @version: V1.0  
 */ 
package org.person.dfw.objectAndclass.Interface;

/**
 *成员：成员变量默认都是public static final类型的(都可省略),必须被显示初始化
 *方法：方法默认都是public,abstract类型的(都可省略),没有方法体,不能被实例化
 *构造方法：没有构造方法,不能被实例化
 *多态：接口不能实现(implements)另一个接口,但它可以继承多个其它的接口
 *实现：当类实现了某个Java接口时,它必须实现接口中的所有抽象方法,否则这个类必须声明为抽象类
 */
interface Person{  
    public String name = "li";
    void eat();  
    void sleep();  
}  
   
class Student implements Person{  
    public void eat(){  
       System.out.println("学生去食堂吃饭！");  
    }  
    public void sleep(){  
       System.out.println("学生回寝室睡觉！");  
    }  
}  
   
class Teacher implements Person{  
    public void eat(){  
       System.out.println("教师去教工餐厅吃饭！");  
    }  
    public void sleep(){  
       System.out.println("教师回学校公寓睡觉！");  
    }  
}  

public class TestInterface {
    public static void main(String[] args){  
        Person p=new Student();  
        p.eat();  
        p.sleep();  
        p=new Teacher();  
        p.eat();  
        p.sleep();  
        //接口形式的匿名内部类
        new Person() {
            @Override
            public void sleep() {
                System.out.println("anonymous class person sleep");
            }
            @Override
            public void eat() {
                System.out.println("anonymous class person eat");
            }
        }.eat();
    }


    interface A{
        void i();
    }
    interface B{
        void i();
    }
    class AB implements A,B{
        @Override
        public void i() {
            // 可以实现具有相同方法签名的多个接口
        }
    }

    interface AA{
        default void i(){
            System.out.println("AA = " + true);
        }
    }
    interface BB{
        default void i(){
            System.out.println("BB = " + true);
        }
    }
    class AAI implements AA{}
    class BBI implements BB{}
    class AABB implements AA,BB{
        @Override
        public void i() {
            // 此时必须重写 i 方法
        }
    }
}