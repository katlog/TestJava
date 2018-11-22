package name.dfw.jvm.tools;

/**
 * @moudle: TestJVM_jstack
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年9月18日 下午4:58:42
 *
 */
public class TestJVM_jstack {

	public static void main(String[] args) {
		Thread t1 = new Thread(new DeadLockclass(true));// 建立一个线程
		Thread t2 = new Thread(new DeadLockclass(false));// 建立另一个线程
		t1.start();// 启动一个线程
		t2.start();// 启动另一个线程
	}
}

class DeadLockclass implements Runnable {

	public boolean falg;// 控制线程

	DeadLockclass(boolean falg) {
		this.falg = falg;
	}

	public void run() {
		/**
		 * 如果falg的值为true则调用t1线程
		 */
		if (falg) {
			while (true) {
				synchronized (Suo.o1) {
					System.out.println("o1 " + Thread.currentThread().getName());
					synchronized (Suo.o2) {
						System.out.println("o2 " + Thread.currentThread().getName());
					}
				}
			}
		}
		/**
		 * 如果falg的值为false则调用t2线程
		 */
		else {
			while (true) {
				synchronized (Suo.o2) {
					System.out.println("o2 " + Thread.currentThread().getName());
					synchronized (Suo.o1) {
						System.out.println("o1 " + Thread.currentThread().getName());
					}
				}
			}
		}
	}
}

class Suo {

	static Object o1 = new Object();
	static Object o2 = new Object();
}
