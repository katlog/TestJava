package org.person.dfw.objectAndclass;

import org.junit.Test;



/**
 * 方法调用测试
 * @moudle: TestMethodCall 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月8日 上午8:57:23
 *
 */
public class TestMethodCall {
	
	static abstract class Human {
		protected abstract void sayHello();
	}

	static class Man extends Human {
		@Override
		protected void sayHello() {
			System.out.println("man say hello");
		}
		protected void manSay() {
			System.out.println("only man say!");
		}
	}
	static class Woman extends Human {
		@Override
		protected void sayHello() {

			System.out.println("woman say hello");
		}
		protected void womanSay() {
			System.out.println("only woman say!");
		}
	}
	
	@Test public void test1() {
		
		/** 方法重写：调用版本由实际类型来确定，和外观类型Human无关 */
		Human man = new Man();
		Human woman = new Woman();
		man.sayHello(); //调用Man的sayHello
		woman.sayHello();//调用Woman的sayHello
		man = new Woman();
		man.sayHello();//调用Woman的sayHello
		
//		man.manSay();//产生编译错误，因manSay方法在Human中没有定义
	}
	
}
