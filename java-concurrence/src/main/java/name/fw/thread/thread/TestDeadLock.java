package name.fw.thread.thread;



/**
 * @moudle: TestDeadLock 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年10月25日 下午8:58:20
 *
 */
public class TestDeadLock {
	static A a = new A();
	static B b = new B();
	static class A implements Runnable{
		@Override public void run() {
			synchronized (b) {
				for (int i = 0; i < 1000; i++) {
					System.out.println();
				}
				synchronized (a) {
					for (int i = 0; i < 100; i++) {
						System.out.println("aaaaa" + i);
					}
				}
			}
		}
	}
	static class B implements Runnable{
		@Override public void run() {
			synchronized (a) {
				for (int i = 0; i < 1000; i++) {
					System.out.println();
				}
				synchronized (b) {
					for (int i = 0; i < 100; i++) {
						System.out.println("bbbbb" + i);
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		
		new Thread(a).start();
		new Thread(b).start();
	}
	
}