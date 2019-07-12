package name.katlog.jvm.item08;

import java.io.Serializable;

/**
 * @moudle: TestOverload
 * @version:v1.0
 * @author: katlog
 * @date: 2017年7月31日 上午9:39:42
 *
 */
public class TestOverload {

	public static void sayHello(Object arg) {
		System.out.println("hello Object");
	}

	public static void sayHello(int arg) {
		System.out.println("hello int");
	}

	public static void sayHello(long arg) {
		System.out.println("hello long");
	}

	public static void sayHello(Character arg) {
		System.out.println("hello Character");
	}

	public static void sayHello(char arg) {
		System.out.println("hello char");
	}

	public static void sayHello(char... arg) {
		System.out.println("hello char……");
	}

	public static void sayHello(Serializable arg) {
		System.out.println("hello Serializable");
	}

	public static void main(String[] args) {
		sayHello('a');
	}
}
