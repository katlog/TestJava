package org.person.dfw.objectAndclass.innerclass;


public class TestAnonymousInnerClass {
	/**
	 * 匿名内部类是没有访问修饰符的。	
	 * 第一个参final修饰，而第二个却没有(在匿名内部类中没有使用)，当所在方法的形参需被匿名内部类使用，这个形参须为final
	 * 匿名内部类是没有构造方法的。因为它连名字都没有何来构造方法
	 * 匿名内部类中不能存在任何的静态成员变量和静态方法
	 * 匿名内部类不能是抽象的，它必须要实现继承的类或者实现的接口的所有抽象方法
	 */
	public InnerClass getInnerClass( final int num, String str2 ) {
		return new InnerClass() {
			int number = num + 3;
			public int getNumber() {
				return number;
			}
		}; /* 注意：分号不能省 */
	}
	public static void main( String[] args ) {
		TestAnonymousInnerClass out = new TestAnonymousInnerClass();
		InnerClass inner = out.getInnerClass(2, "chenssy");
		System.out.println(inner.getNumber());
	}
}
//interface 可以是接口
//abstract  也可以是抽象类
class InnerClass {
	int getNumber() {
		return 0;
	}
}
