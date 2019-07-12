package org.person.dfw.generic;


import org.junit.Test;

/**
 * @moudle: TestGeneric 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月9日 上午11:19:03
 *
 */
public class TestGeneric {
	
	class A {};
	class A1<T> extends A{};
	class A2<T extends A1> extends A1{};
	class A3<T extends Cloneable>{};
//	class A4<T super List>{}; 			//没有这种方式
	
	static class B{
		public static void _extends_1(A1<? extends A1> a) {			//?方式:通配符类型
			A1 b = a;
			A1<A2> b1 = (A1<A2>) a;
			A1<?> b2 = a;
			A1<? extends A1> b3 = a;
		}
		public static<T extends A1> void _extends_2(A1<T> a) {		//泛型参数方式
			
		}
		
		public static void _super_1(A1<? super A2> a) {				//?方式:通配符类型,超类限定 supertype bound
			A1 b = a;
			A1<A2> b1 = (A1<A2>) a;
			A1<?> b2 = a;
			A1<? super A2> b3 = a;
		}
//		public static <T super A2> void _super_2(A2<T> a) {			// 没有 泛型参数方式
//		}
		
		public static void test3(A1 a) {
		}
		public static<T> void test4(A1<T> a) {
		}
	}
	
	class Info<T>{
		void setVar(T t){
			
		}
	}
	
	@Test
	public void compileError(){
		// 指定String为泛型类型
		Info<?> i = new Info<String>();
		// 设置字符串 error
		// i.setVar("慕小然");
	}
}	
