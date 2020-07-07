package name.fw.thread.synchronizor;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @moudle: TestCountDownLatch
 * @author: katlog
 * @date: 2017年8月30日 下午5:42:04
 *	倒计数器 锁存器是一次性障碍，允许一个或者多个线程等待一个或者多个其它线程来做某些事情。
 *	CountDownLatch的唯一构造器带一个int类型的参数，这个int参数是指允许所有在等待线程被处理之前，
 *	必须在锁存器上调用countDown方法的次数
 *
 *	CountDownLatch最重要的方法是countDown()和await()，
 *		前者主要是倒数一次，后者是等待倒数到0，如果没有到达0 ，就只有阻塞等待了。 
 *  CountDownLatch（不能循环使用，如果需要循环使用可以考虑使用CyclicBarrier） 两种比较常规用法：
 *  	 1：new  CountDownLatch(1);所有的线程在开始工作前需要做一些准备工作，当所有的线程都准备到位后再统一执行时有用
 *  	 2：new  CountDownLatch(THREAD_COUNT);当所有的线程都执行完毕后，等待这些线程的其他线程才开始继续执行时有用 
 */
public class TestCountDownLatch {

	private static final int THREAD_COUNT = 100;
	// 在调用startSingal.countDown()之前调用了startSingal.await()的线程一律等待，直到startSingal.countDown()的调用
	private static final CountDownLatch startSingal = new CountDownLatch(1);
	// 在finishedSingal的初始化记数量通过调用finishedSingal.countDown()减少为0时调用了finishedSingal.await()的线程一直阻塞
	private static final CountDownLatch finishedSingal = new CountDownLatch(THREAD_COUNT);

	/** 互为等待线程 */
	@Test
	public void countDown() throws InterruptedException {
		
		for (int i = 0; i < THREAD_COUNT; i++) {
			new Thread("Task " + i) {
				public void run() {
					System.out.println(Thread.currentThread().getName() + " prepared!!");
					try {
						startSingal.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " finished!!");
					finishedSingal.countDown();
				};
			}.start();
		}
		Thread.sleep(1000);
		startSingal.countDown();// 所有的线程被唤醒，同时开始工作.countDown 方法的线程等到计数到达零时才继续
		finishedSingal.await();// 等待所有的线程完成!!
		System.out.println("All task are finished!!");
	}
	
	/** 一个等待线程 boss */
	@Test
	public void workBoss() throws InterruptedException {
		ExecutorService executor = new ThreadPoolExecutor(
				10, 15, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r,"myThread");
			}
		});

		CountDownLatch latch = new CountDownLatch(3);

		executor.execute(new Worker(latch,"王二"));
		executor.execute(new Worker(latch,"李四"));
		executor.execute(new Worker(latch,"张三"));
		executor.execute(new Boss(latch));

		executor.shutdown();

		Thread.sleep(10000);
	}


	class Worker implements Runnable{
		private CountDownLatch downLatch;
		private String name;
		Worker(CountDownLatch downLatch, String name){
			this.downLatch = downLatch;
			this.name = name;
		}
		@Override
		public void run() {
			this.doWork();
			try{
				TimeUnit.SECONDS.sleep(new Random().nextInt(10));
			}catch(InterruptedException ie){
			}
			System.out.println(this.name + "活干完了！");
			this.downLatch.countDown();

		}
		private void doWork(){
			System.out.println(this.name + "正在干活!");
		}
	}
	class Boss implements Runnable {
		private CountDownLatch downLatch;
		Boss(CountDownLatch downLatch) {
			this.downLatch = downLatch;
		}
		@Override
		public void run() {
			System.out.println("老板正在等所有的工人干完活......");
			try {
				this.downLatch.await();
			} catch (InterruptedException e) {
			}
			System.out.println("工人活都干完了，老板开始检查了！");
		}
	}

}
