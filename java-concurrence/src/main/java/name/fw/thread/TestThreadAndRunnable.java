/**
 * @Title: TestThreadAndRunnable.java
 * @Package: org.person.dfw.thread
 * @Description: TODO
 * @author: chensl
 * @date: 2017年3月30日 上午9:07:36
 * @version: V1.0
 */
package name.fw.thread;

import org.junit.Test;

/**
 * 比较thread和runnable运行的不同
 * 
 * 第一种方法中，new 3个 Thread对象，即三个线程分别执行三个对象中的代码，因此便是三个
 * 线程去独立地完成卖票的任务；第二种方法中，new 了 3 个 Thread 对象，但只有一个 Ru
 * nnable 对象，3 个 Thread 对象共享这个 Runnable 对象中的代码，因此，便会出现 3 个线程共同完成卖
 * 票任务的结果。如果我们 new 出 3 个 Runnable 对象，作为参数分别传入 3 个 Thread 对象中，那么 3 个
 * 线程便会独立执行各自 Runnable 对象中的代码，即 3 个线程各自卖 5 张票
 * 
 * 在第二种方法中，由于 3 个 Thread 对象共同执行一个 Runnable 对象中的代码，因此可能会造成线程的不
 * 安全，比如可能 ticket 会输出 -1（如果我们 System.out....语句前加上线程休眠操作，该情况将很有可能出
 * 现），这种情况的出现是由于，一个线程在判断 ticket 为 1>0 后，还没有来得及减 1，另一个线程已经将 tic
 * ket 减 1，变为了 0，那么接下来之前的线程再将 ticket 减 1，便得到了 -1。这就需要加入同步操作（即互
 * 斥锁），确保同一时刻只有一个线程在执行每次 for 循环中的操作。而在第一种方法中，并不需要加入同步
 * 操作，因为每个线程执行自己 Thread 对象中的代码，不存在多个线程共同执行同一个方法的情况
 * 
 * @moudle: TestThreadAndRunnable
 * @version:v1.0
 * @date: 2017年3月30日 上午9:07:36
 *
 */
public class TestThreadAndRunnable {

    /**
     * 每个线程单独卖了 5 张票，即独立地完成了买票的任务
     * <p>Title: ThreadDemo</p>
     * <p>date : 2017年3月30日 上午9:14:19</p>
     */
    @Test
    public void ThreadDemo() {
        System.out.println("thread demo 开始");
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread demo 结束");
    }

    /**
     * 多个线程去共同完成任务，即多个线程共同买 5 张票
     * <p>Title: RunnableDemo</p>
     * <p>date : 2017年3月30日 上午9:14:47</p>
     * ticket 输出的顺序并不是 54321，这是因为线程执行的时机难以预测，ticket--并不是原子操作
     */
    @Test
    public void RunnableDemo() {
        System.out.println("runnable demo 开始");
        MyRunnable my = new MyRunnable();
        new Thread(my).start();
        new Thread(my).start();
        new Thread(my).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("runnable demo 结束");
    }
}

class MyThread extends Thread {
    private int ticket = 5;

    public void run() {
        for (int i = 0; i < 10; i++) {
            if (ticket > 0) {
                System.out.println("ticket = " + ticket--);
            }
        }
    }
}

class MyRunnable implements Runnable {
    private int ticket = 5;

    public void run() {
        for (int i = 0; i < 10; i++) {
            if (ticket > 0) {
                System.out.println("ticket = " + ticket--);
            }
        }
    }
}