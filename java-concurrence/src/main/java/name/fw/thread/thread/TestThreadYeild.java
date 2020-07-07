package name.fw.thread.thread;

import org.junit.Test;

/**
 * Created by fw on 2020/7/6
 */
public class TestThreadYeild {

    /**
     * 暂停当前正在执行的线程对象，并执行其他线程。
     *
     * 与join的区别 yield()让当前线程状态改为可运行状态，和其它线程一起竞争资源
     *
     * Thread.yield()方法作用是：
     *          暂停当前正在执行的线程对象，并执行其他线程。
     *          yield()应该做的是让当前运行线程回到可运行状态，以允许具有相同优先级的其他线程获得运行机会。
     *          因此，使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。
     *          但，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中。
     *          它仅能使一个线程从运行状态转到可运行状态，而不是等待或阻塞状态
     *
     * 结论：yield()从未导致线程转到等待/睡眠/阻塞状态。在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果。
     *
     *  */
    @Test
    public void yeild1() throws InterruptedException {
        Thread producer = new Producer();
        Thread consumer = new Consumer();

        producer.setPriority(Thread.NORM_PRIORITY); //Min Priority
        consumer.setPriority(Thread.NORM_PRIORITY); //Max Priority

        producer.start();
        consumer.start();
    }
    class Consumer extends Thread {
        @Override
        public void run()
        {
            for (int i = 0; i < 15; i++)
            {
                System.out.println("I am Consumer : Consumed Item " + i);
                Thread.yield();
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run()
        {
            for (int i = 0; i < 5; i++)
            {
                System.out.println("I am Producer : Produced Item " + i);
                Thread.yield();
            }
        }
    }
}
