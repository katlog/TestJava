/**
 * @Title: TestThreadInterrupt.java
 * @Package: org.person.dfw.thread
 * @author: chensl
 * @date: 2017年3月30日 上午9:29:21
 * @version: V1.0
 */
package name.fw.thread.thread;

import org.checkerframework.checker.units.qual.Time;
import org.junit.Test;

/**
 * 测试线程中断 {@link Thread#interrupt} {@link Thread#isInterrupted()}
 * {@link Thread#sleep(long)} {@link Thread#interrupt}
 * 
 * @moudle: TestThreadInterrupt
 * @version:v1.0
 * @date: 2017年3月30日 上午9:29:21
 *
 */
public class TestThreadInterrupt extends Object implements Runnable {
    public void run() {
        try {
            System.out.println("in run() - about to sleep for 20 seconds");
//            Thread.currentThread().interrupt();
            // sleep()方法的实现检查到休眠线程被中断，它会相当友好地终止线程，并抛出
            // InterruptedException 异常
            System.out.println("in run() - state:"+Thread.currentThread().isInterrupted());
            Thread.sleep(20000);
            System.out.println("in run() - woke up");
        } catch (InterruptedException e) {
            System.out.println("in run() - interrupted while sleeping");
            System.out.println("in run() - state:"+Thread.currentThread().isInterrupted());
            // 处理完中断异常后，返回到run（）方法人口，
            // 如果没有return，线程不会实际被中断，它会继续打印下面的信息
            Thread.currentThread().interrupt();
            System.out.println("in run() - state:"+Thread.currentThread().isInterrupted());
            return;
        }
        System.out.println("in run() - leaving normally");
    }

    /**
     * 测试线程中断 interrupt 方法
     * <p>Title: testInterrupt</p>
     * <p>date : 2017年3月30日 上午9:38:12</p>
     */
    @Test
    public void testInterrupt() {
        TestThreadInterrupt si = new TestThreadInterrupt();
        Thread t = new Thread(si);
        t.start();
        // 主线程休眠2秒，从而确保刚才启动的线程有机会执行一段时间
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("in main() - interrupting other thread");
        // 中断线程t
        t.interrupt();
        System.out.println("in main() - leaving");
    }

    /**
     * 测试待决中断
     * 
     * 如果线程在调用 sleep()方法前被中断，那么该中断称为待决中断，它会在刚调用sleep()方法时，立即抛出 InterruptedException 异常
     * <p>Title: testPengdingInterrupt</p>
     * <p>date : 2017年3月30日 上午10:43:38</p>
     */
    @Test
    public void testPengdingInterrupt() {
        boolean flag = false;
        // 如果输入了参数，则在main线程中中断当前线程（亦即main线程）
        if (flag) {
            Thread.currentThread().interrupt();
        }
        // 获取当前时间
        long startTime = System.currentTimeMillis();
        try {
            Thread.sleep(2000);
            System.out.println("was NOT interrupted");
        } catch (InterruptedException x) {
            System.out.println("was interrupted");
        }
        // 计算中间代码执行的时间
        System.out.println("elapsedTime=" + (System.currentTimeMillis() - startTime));
    }

    /**
     * 测试isinterrupted方法判断在中断状态
     * 
     * 在 Thread 对象上调用 isInterrupted()方法来检查任何线程的中断状态。注意：线程一旦被中断，isInterrupted()方法
     * 便会返回 true，而一旦 sleep()方法抛出异常，它将清空中断标志，此时isInterrupted()方法将返回 false
     * <p>Title: testIsinterrupted</p>
     * <p>date : 2017年3月30日 上午10:45:31</p>
     */
    @Test
    public void testIsinterrupted() {
        Thread t = Thread.currentThread();
        System.out.println("Point A: t.isInterrupted()=" + t.isInterrupted());
        // 待决中断，中断自身
        t.interrupt();
        System.out.println("Point B: t.isInterrupted()=" + t.isInterrupted());
        System.out.println("Point C: t.isInterrupted()=" + t.isInterrupted());
        try {
            Thread.sleep(2000);
            System.out.println("was NOT interrupted");
        } catch (InterruptedException x) {
            System.out.println("was interrupted");
        }
        // 抛出异常后，会清除中断标志，这里会返回false
        System.out.println("Point D: t.isInterrupted()=" + t.isInterrupted());

    }

    /**
     * 测试 interrupted方法
     * 
     * Thread.interrupted()方法来检查当前线程的中断状态（并隐式重置为 false）。因为静态方
        法，因此不能在特定的线程上使用，而只能报告调用它的线程的中断状态，如果线程被中断，而且中断状态尚不
        清楚，那么，这个方法返回 true。与 isInterrupted()不同，它将自动重置中断状态为 false，
        第二次调用 Thread.interrupted()方法，总是返回 false，除非中断了线程
     * <p>Title: testInterrupted</p>
     * <p>date : 2017年3月30日 上午10:51:06</p>
     */ 
    @Test
    public void testInterrupted() {

        System.out.println("Point X: Thread.interrupted()=" + Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println("Point Y: Thread.interrupted()=" + Thread.interrupted());
        System.out.println("Point Z: Thread.interrupted()=" + Thread.interrupted());
    }


    Object lock1 = new Object();
    Object lock2 = new Object();

    @Test
    public void test11() throws InterruptedException {
       new Thread(() -> {
           try {
               a();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }).start();
        b();


    }
    public void  a() throws InterruptedException {
        synchronized (lock1){
            Thread.sleep(500);
            synchronized (lock2){

            }
        }
    }

    public void  b() throws InterruptedException {
        synchronized (lock2){
            Thread.sleep(500);
            synchronized (lock1){

            }
        }
    }
}
