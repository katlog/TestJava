package name.fw.thread.synchronizor;

/**
 * @moudle: TestSemaphroe
 * @version:v1.0
 * @author: katlog
 * @date: 2017年3月31日 上午11:29:02
 *
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@SuppressWarnings("javadoc")
/**
 * @moudle: TestSemaphroe 
 * @author: katlog
 * @date: 2017年9月5日 上午10:19:17
 *	通过计数器控制对共享资源的访问。
 *	Semaphore 信号量，就是一个允许实现设置好的令牌。也许有1个，也许有10个或更多。 
　　		谁拿到令牌(acquire)就可以去执行了，如果没有令牌则需要等待。 
　　		执行完毕，一定要归还(release)令牌，否则令牌会被很快用光，别的线程就无法获得令牌而执行下去了
 */
public class TestSemaphroe {
    public static void main(String[] args) {
        // 采用新特性来启动和管理线程——内部使用线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只允许5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        // 模拟10个客户端访问
        for (int index = 0; index < 10; index++) {
            final int num = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        System.out.println("线程" + Thread.currentThread().getName() + "获得许可：" + num);
                        // 模拟耗时的任务
                        for (int i = 0; i < 999999; i++)
                            ;
                        // 释放许可
                        semp.release();
                        System.out.println("线程" + Thread.currentThread().getName() + "释放许可：" + num);
                        System.out.println("当前允许进入的任务个数：" + semp.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        // 关闭线程池
        exec.shutdown();
    }
}