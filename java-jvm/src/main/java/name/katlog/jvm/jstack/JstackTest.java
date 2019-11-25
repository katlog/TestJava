package name.katlog.jvm.jstack;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by fw on 2019/11/22
 *
 *  看一下这一篇文章<a href='https://blog.csdn.net/zxh87/article/details/52137335'>jstack日志深入理解</>
 */
public class JstackTest {

    @Test
    public void block() throws InterruptedException {

        Thread thread = new Thread("thread-1"){
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        System.out.println("thread 1 running ,,,");
                        TimeUnit.SECONDS.sleep(60);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread.start();

        synchronized (thread) {
            System.out.println("main running ,,,");
            TimeUnit.SECONDS.sleep(60);
        }

    }

    /** object.wait */
    @Test
    public void objecWait(){
        final Thread thread = new Thread("thread-1") {
            @Override
            public void run() {
                synchronized (this) {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (thread) {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.notify();
        }
    }

    private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1);

    @Test
    public void waitingOnCondition(){
        blockingQueue.add("1");
        try {
            //阻塞的添加
            blockingQueue.put("2");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
