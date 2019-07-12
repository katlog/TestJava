package name.fw.thread.excutor;

/**
 * @moudle: TestThreadPool
 * @version:v1.0
 * @author: katlog
 * @date: 2017年3月31日 上午9:21:15
 *
 */

import org.junit.Test;

import java.util.concurrent.*;

@SuppressWarnings("javadoc")
public class TestThreadPool {
	
    @Test public void test1() throws InterruptedException {
        // 创建等待队列
//        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(20);
//        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>(20);
        BlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(20);
        /**
            • corePoolSize：线程池中所保存的核心线程数，包括空闲线程。
            • maximumPoolSize：池中允许的最大线程数。
            • keepAliveTime：线程池中的空闲线程所能持续的最长时间。
            • unit：持续时间的单位。
            • workQueue：任务执行前保存任务的队列，仅保存由 execute 方法提交的 Runnable 任务
         */
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 50, TimeUnit.MILLISECONDS, queue);
        // 创建七个任务
        Runnable t1 = new MyThread();
        Runnable t2 = new MyThread();
        Runnable t3 = new MyThread();
        Runnable t4 = new MyThread();
        Runnable t5 = new MyThread();
        Runnable t6 = new MyThread();
        Runnable t7 = new MyThread();
        // 每个任务会在一个线程上执行
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        pool.execute(t7);
        // 关闭线程池
        pool.shutdown();
        
        Thread.sleep(5000);
    }
    
    
    @Test public void test2() throws InterruptedException{
    	
        // 产生线程池，有3个线程，使用固定线程池创建
        //ExecutorService threadPool = Executors.newFixedThreadPool(3);
        //产生线程池，动态创建线程池的大小
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //向线程池添加10个任务
		for (int i = 1; i < 10; i++) {
			final int task = i;
			threadPool.execute(new Runnable() {
				@Override public void run() {
					for (int j = 0; j < 10; j++) {
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println(Thread.currentThread().getName() + "is looping of " + j + " for task "
								+ task);
					}
				}
			});
		}
		// 线程调度的使用
		// 该功能为定时运行6秒以后运行，然后每隔2秒运行一次
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(() -> System.out.println("bomb...."), 6, 2,
				TimeUnit.SECONDS);
		
		Thread.sleep(5000);
    }
}

class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行。。。");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}