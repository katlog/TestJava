package org.person.dfw.collection;

import java.util.Enumeration;
import java.util.Vector;

import org.junit.Test;


/**
 * @moudle: TestVector 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月7日 上午11:55:20
 *	Vector与ArrayList一样，也是通过数组实现的，不同的是它支持线程的同步，即某一时刻只有一个线程能够写Vector，
 *	避免多线程同时写而引起的不一致性，但实现同步需要很高的花费，因此，访问它比访问ArrayList慢。
 */
public class TestVector {

	@Test public void _common(){
		
		Vector<Object> v1 = new Vector<Object>(); 
		Integer integer1= new Integer(1); 
		//加入为字符串对象 
		v1.addElement("one"); 
		//加入的为integer的对象 
		v1.addElement(integer1); 
		v1.addElement(1); 
		v1.addElement("two"); 
		v1.addElement(new Integer(2)); 
		v1.addElement(integer1); 
		v1.addElement(1); 
		
		System.out.println("The Vector v1 is:\n\t"+v1); 
		
		//向指定位置插入新对象 
		v1.insertElementAt("three",2); 
		v1.insertElementAt(new Float(3.9),3); 
		System.out.println("The Vector v1(used _03method insertElementAt()is:\n\t)"+v1);
		
		//将指定位置的对象设置为新的对象 
		//指定位置后的对象依次往后顺延 
		v1.setElementAt("four",2); 
		System.out.println("The vector v1 cused _03method setElmentAt()is:\n\t"+v1);
		
		//从向量对象v1中删除对象integer1 
		//由于存在多个integer1,所以从头开始。 
		//找删除找到的第一个integer1. 
		v1.removeElement(integer1); 
		
		//使用枚举类(Enumeration)的方法取得向量对象的每个元素。 
		Enumeration enums = v1.elements(); 
		System.out.println("The vector v1 (used _03method removeElememt()is");
		while(enums.hasMoreElements()) 
		System.out.println(enums.nextElement()+""); 
		
		//按不同的方向查找对象integer1所处的位置 
		System.out.println("The position of Object1(top-to-botton):"+v1.indexOf(integer1)); 
		System.out.println("The position of Object1(tottom-to-top):"+v1.lastIndexOf(integer1)); 
		
		//重新设置v1的大小，多余的元素被抛弃 
		v1.setSize(4); 
		System.out.println("The new Vector(resized the vector)is:"+v1); 
	}
}
