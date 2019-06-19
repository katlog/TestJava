/**
 * @Title: TestBlocking.java
 * @Package: org.person.dfw.excutor
 * @author: katlog
 * @date: 2017年3月31日 上午11:02:24
 * @version: V1.0
 */
package name.fw.thread.excutor;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @moudle: TestBlocking
 * @version:v1.0
 * @author: katlog
 * @date: 2017年3月31日 上午11:02:24
 *
 */
@SuppressWarnings("javadoc")
public class TestBlocking {

	@Test public void blockingQueue() throws InterruptedException {
		BlockingQueue<String> bqueue = new ArrayBlockingQueue<String>(20);
		for (int i = 0; i < 30; i++) {
			// 将指定元素添加到此队列中
			bqueue.put("加入元素" + i);
			System.out.println("向阻塞队列中添加了元素:" + i);
		}
		System.out.println("程序到此运行结束，即将退出----");
	}

    @Test public void blockingQueue1() throws InterruptedException {
		BlockingQueue<String> bqueue = new ArrayBlockingQueue<String>(20);
		for (int i = 0; i < 30; i++) {
			// 将指定元素添加到此队列中
			bqueue.put("" + i);
			System.out.println("向阻塞队列中添加了元素:" + i);
			if (i > 18) {
				// 从队列中获取队头元素，并将其移出队列
				System.out.println("从阻塞队列中移除元素：" + bqueue.take());
			}
		}
		System.out.println("程序到此运行结束，即将退出----");
	}

    @Test public void blockingDeque() throws InterruptedException {
		BlockingDeque<String> bDeque = new LinkedBlockingDeque<String>(20);
		for (int i = 0; i < 30; i++) {
			// 将指定元素添加到此阻塞栈中
			bDeque.putFirst("" + i);
			System.out.println("向阻塞栈中添加了元素:" + i);
		}
		System.out.println("程序到此运行结束，即将退出----");
	}

    @Test public void blockingDeque1() throws InterruptedException {
		BlockingDeque<String> bDeque = new LinkedBlockingDeque<String>(20);
		for (int i = 0; i < 30; i++) {
			// 将指定元素添加到此阻塞栈中
			bDeque.putFirst("" + i);
			System.out.println("向阻塞栈中添加了元素:" + i);
			if (i > 18) {
				// 从阻塞栈中取出栈顶元素，并将其移出
				System.out.println("从阻塞栈中移出了元素：" + bDeque.pollFirst());
			}
		}
		System.out.println("程序到此运行结束，即将退出----");
	}
    
    
    private final ExecutorService exec = Executors.newCachedThreadPool();
    
    interface Task {    }
    @Test public void NoncancelableTask (){
    	BlockingQueue<Task> queue = new ArrayBlockingQueue<Task>(3);
    	
		Callable<Task> callable = new Callable<Task>() {
			public Task call() {
				boolean interrupted = false;
				try {
					while (true) {
						try {
							for (int i = 0; i < 1000; i++) {
								System.out.println("");
							}
							return queue.take();
						} catch (InterruptedException e) {
							interrupted = true; // fall through and retry
						}
					}
				} finally {
					if (interrupted)
						Thread.currentThread().interrupt();
				}
			}
		};
		Future future = exec.submit(callable);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 1000; j++) {
				System.out.println("");
			}
				queue.add(new Task() {});
		}

		try {
			Object object = future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
    }
}
