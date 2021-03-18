package name.katlog.objectAndclass.innerclass;

import org.junit.Test;


public class TestLocalInnerClass {
	
	private int s = 100;
	private int out_i = 1;

	public void f(final int k, int v) {
		final int s = 200;
		int i = 1;
		final int j = 10;
		// 定义在方法内部
		/* final */class Inner { // 不能有 public protected private static修饰 可以final修饰
			int s = 300;// 可以定义与外部类同名的变量

			// static int m = 20;//不可以定义静态变量
			Inner(int k) {
				inner_f(k);
			}

			int i_k = k;// 可以访问final修饰的方法参数

			int inner_i = 100;

			void inner_f(int k) {
				int i_v = v;// 可以访问方法的其他参数
				// 如果内部类没有与外部类同名的变量，在内部类中可以直接访问外部类的实例变量
				System.out.println(out_i);
				// 可以访问外部类的局部变量(即方法内的变量)，但是变量必须是final的
				System.out.println(j);
				// System.out.println(i);
				// 如果内部类中有与外部类同名的变量，直接用变量名访问的是内部类的变量
				System.out.println(s);
				// 用this.变量名访问的也是内部类变量
				System.out.println(this.s);
				// 用外部类名.this.内部类变量名访问的是外部类变量
				System.out.println(TestLocalInnerClass.this.s);
			}
		}
		new Inner(k);
	}
	public static void main(String[] args) {
		// 访问局部内部类必须先有外部类对象
		TestLocalInnerClass out = new TestLocalInnerClass();
		out.f(3, 4);
	}
	
	
	@Test public void test2(){
		abstract  class A{											//局部类可以是abstract的
			public abstract void a1() ;
			protected void a2() {
				System.out.println("a2");
			}
		}
		class B extends A{											//局部类可以是extends abstract类的类
			@Override
			public void a1() {
				System.out.println("B extends A excute a1");
			}
			
		}
		new B().a1();
		new B().a2();
	}
}
