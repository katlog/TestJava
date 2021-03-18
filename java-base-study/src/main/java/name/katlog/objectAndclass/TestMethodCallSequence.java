package name.katlog.objectAndclass;


/**
 * 方法调用顺序
 * 
 * @author: katlog
 * @date: 2017年5月13日 下午4:28:39
 */
public class TestMethodCallSequence {
	static class A {
		C c = new C();
		public A() {
			System.out.println("A's Constructor");
		}
		static {
			System.out.println("A's static ");
		}
	}
	static class B extends A {
		public B() {
			System.out.println("B's Constructor");
		}
		D d = new D();
		C c = new C();
		static {
			System.out.println("B's static ");
		}
	}
	static class C {
		public C() {
			System.out.println("C's Constructor");
		}
		static {
			System.out.println("C's static");
		}
	}
	static class D {
		public D() {
			System.out.println("D's Constructor");
		}
		static {
			System.out.println("D's static");
		}
	}
	public static void main( String args[] ) {
		B b = new B();
	}
}
