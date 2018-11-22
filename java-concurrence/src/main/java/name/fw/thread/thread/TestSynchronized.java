package name.fw.thread.thread;

import org.junit.Test;

/**
 * @moudle: TestSynchronized 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月29日 上午10:49:17
 *
 */
@SuppressWarnings("javadoc")
public class TestSynchronized {
	
	/**互斥的两个线程thread1、thread2 【同步】*/
	@Test public void _mutex() throws InterruptedException{
		SyncThread syncThread = new SyncThread();
		Thread thread1 = new Thread(syncThread, "SyncThread1");
		Thread thread2 = new Thread(syncThread, "SyncThread2");
		thread1.start();
		thread2.start();
		Thread.sleep(1000);
	}
	
	/**不互斥的两个线程thread1、thread2：因为不是同一个对象锁【不同步】*/
	@Test public void _not_mutex() throws InterruptedException{
		Thread thread1 = new Thread(new SyncThread(), "SyncThread1");
		Thread thread2 = new Thread(new SyncThread(), "SyncThread2");
		thread1.start();
		thread2.start();
		Thread.sleep(1000);
	}
	
	/**多个线程访问synchronized和非synchronized代码块 【不同步】*/
	@Test public void test2() throws InterruptedException{
		Counter counter = new Counter();
		Thread thread1 = new Thread(counter, "A");
		Thread thread2 = new Thread(counter, "B");
		thread1.start();
		thread2.start();
		Thread.sleep(1000);
	}
	
	/**多个线程访问synchronized代码块1和synchronized代码块2 【同步】*/
	@Test public void test() throws InterruptedException{
		Counter1 counter1 = new Counter1();
		Thread thread1 = new Thread(counter1, "A");
		Thread thread2 = new Thread(counter1, "B");
		thread1.start();
		thread2.start();
		Thread.sleep(1000);
	}
	
	/**指定要给某个对象加锁*/
	@Test public void test3() throws InterruptedException{
		Account account = new Account("zhang san", 10000.0f);
		AccountOperator accountOperator = new AccountOperator(account);

		final int THREAD_NUM = 5;
		Thread threads[] = new Thread[THREAD_NUM];
		for (int i = 0; i < THREAD_NUM; i ++) {
		   threads[i] = new Thread(accountOperator, "Thread" + i);
		   threads[i].start();
		}
		Thread.sleep(2000);
	}
	
	/**synchronized修饰静态方法*/
	@Test public void test4() throws InterruptedException{
		SyncThread1 syncThread1 = new SyncThread1();
		SyncThread1 syncThread2 = new SyncThread1();
		Thread thread1 = new Thread(syncThread1, "SyncThread1");
		Thread thread2 = new Thread(syncThread2, "SyncThread2");
		thread1.start();
		thread2.start();
		Thread.sleep(1000);
	}
	
	static Object o1 = new Object();
	static Object o2 = new Object();
	/**同步造成的死锁 */
	@Test public void deadlock() throws InterruptedException{

		class DeadLockclass implements Runnable {
		    public boolean falg;// 控制线程
		    DeadLockclass(boolean falg) {
		        this.falg = falg;
		    }

			public void run() {
				if (falg) {
					while (true) {
						synchronized (o1) {
							System.out.println("o1 " + Thread.currentThread().getName());
							synchronized (o2) {
								System.out.println("o2 " + Thread.currentThread().getName());
							}
						}
					}
				} else {
					while (true) {
						synchronized (o2) {
							System.out.println("o2 " + Thread.currentThread().getName());
							synchronized (o1) {
								System.out.println("o1 " + Thread.currentThread().getName());
							}
						}
					}
				}
			}
		}
        Thread t1 = new Thread(new DeadLockclass(true));//建立一个线程
        Thread t2 = new Thread(new DeadLockclass(false));//建立另一个线程
        t1.start();//启动一个线程
        t2.start();//启动另一个线程
        
        
        Thread.sleep(100000);

	}
	
	
}

class SyncThread implements Runnable {
	private static int count;
	
	public SyncThread() {
		count = 0;
	}
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public int getCount() {
		return count;
	}
}

class Counter implements Runnable {
	private int count;
	public Counter() {
		count = 0;
	}
	public void countAdd() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	// 非synchronized代码块，未对count进行读写操作，所以可以不用synchronized
	public void printCount() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + " count:" + count);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void run() {
		String threadName = Thread.currentThread().getName();
		if (threadName.equals("A")) {
			countAdd();
		} else if (threadName.equals("B")) {
			printCount();
		}
	}
}

class Counter1 implements Runnable {
	private int count;
	public Counter1() {
		count = 0;
	}
	public void countAdd() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":" + (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void printCount() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + " count:" + count);
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void run() {
		String threadName = Thread.currentThread().getName();
		if (threadName.equals("A")) {
			countAdd();
		} else if (threadName.equals("B")) {
			printCount();
		}
	}
}

/**
 * 银行账户类
 */
class Account {
	String name;
	float amount;

	public Account(String name, float amount) {
		this.name = name;
		this.amount = amount;
	}
	// 存钱
	public void deposit(float amt) {
		amount += amt;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	// 取钱
	public void withdraw(float amt) {
		amount -= amt;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public float getBalance() {
		return amount;
	}
}

/**
 * 账户操作类
 */
class AccountOperator implements Runnable {
	private Account account;

	public AccountOperator(Account account) {
		this.account = account;
	}
	public void run() {
		synchronized (account) {
			account.deposit(500);
			account.withdraw(500);
			System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
		}
	}
}

/**
 * 同步线程
 */
class SyncThread1 implements Runnable {
	private static int count;

	public SyncThread1() {
		count = 0;
	}
	public synchronized static void method() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + ":" + (count++));
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public synchronized void run() {
		method();
	}
}