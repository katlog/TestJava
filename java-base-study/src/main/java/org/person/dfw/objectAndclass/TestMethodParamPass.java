package org.person.dfw.objectAndclass;

import org.junit.Test;

/**
 * @author: katlog
 * @date: 2017年4月27日 下午3:27:53
 */
public class TestMethodParamPass {
    
    
    /**
     * java的方法参数传递方式  
     *      java所有的参数传递都是传递的副本
     */
    @Test public void methodParamTransfer(){
    	String s = "aaa";
    	int i = 1;i = 2;
        StringBuffer sb = new StringBuffer("buff");
        Person p = new Person("aa",12);
        change(s,i,sb,p);
        System.out.println(s);
        System.out.println(i);
        System.out.println(sb.toString());
        System.out.println(p.toString());
    }

    private void change(String s, int i, StringBuffer sb, Person p){
        s="123";
        i=3;
        sb.append("woshi");
        p.setAge(100);
        //new的时候尤其注意这种影响
        sb = new StringBuffer("sbsb");  //参数副本的地址指向变了
        p = new Person("bb",44);        //参数副本的地址指向变了
    }
    
    private class Person{ 
       String name;int age;Person(String name,int age){this.name=name;this.age=age;}
       public void setAge(int age) {this.age = age;}
       @Override
       public String toString() {
           return "Person [name=" + name + ", age=" + age + "]";
       }
    }
    
}
