package name.fw.thread.synchronizor;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 
 * JAVA同步器之Barrier(能够循环使用，当计数器增加到Barrier的初始化计数器之后马上会被置为0为下一次循环使用做准备)
 * 		Barrier能够为指定的一个或多个（一般为多个）线程设置一道屏障，只有当所有的线程都到达该屏障后才能一起冲过该屏障继续其他任务 
 * 			一般可以new CyclicBarrier(ThreadCount)来进行初始化
 * 			也可以new CyclicBarrier(ThreadCount,RunableAction)当初始化数量的线程都调用了await()方法后触发RunableAction线程，
 * 			也可以通过初始化一个new CyclicBarrier(ThreadCount+1)的Barrier在前置线程未执行完成时一直阻塞一个或多个后续线程，这一点类似于CountDownLatch
 */
public class TestCyclicBarrier {

	private static final int THREAD_COUNT = 10;
	private static final CyclicBarrier barrier = new CyclicBarrier(THREAD_COUNT + 1, new Runnable() {
		@Override public void run() {
			System.out.println("All task are prepared or finished!!");
		}
	});

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		// --------------第一次使用--------------
		for (int i = 0; i < THREAD_COUNT; i++) {
			new Thread("Task " + i) {
				public void run() {
					try {
						System.out.println(Thread.currentThread().getName() + " prepared!!");
						barrier.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					// do something
					System.out.println(Thread.currentThread().getName() + " finished!!");
				};
			}.start();
		}
		barrier.await();
		System.out.println("barrier await for all first ! ");
		// --------------第二次：开始准备循环使用--------------
		for (int i = 0; i < THREAD_COUNT; i++) {
			new Thread("Task " + i) {
				public void run() {
					// do something
					System.out.println(Thread.currentThread().getName() + " prepared again !!");
					try {
						barrier.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + " finished again !!");
				};
			}.start();
		}
		barrier.await();
		System.out.println("barrier await for all last ! ");
	}
}
