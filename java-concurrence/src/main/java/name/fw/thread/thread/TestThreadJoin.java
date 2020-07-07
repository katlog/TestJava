package name.fw.thread.thread;

/**
 * Created by fw on 2020/7/2
 */
public class TestThreadJoin {

    /**
     * 结果中首先全部是是Thread-1，之后才会是Thread-2，这是因为在主线程中调用了thread1的join方法，
     * 就等于将主线程和thread1的执行方式由并行改为了串行，
     * 也就是必须当thread1全部执行结束之后，才会调用thread2的方法。
     * */
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new JoinThread());;
        Thread thread2 = new Thread(new JoinThread());

        thread1.start();
        Thread.sleep(20);
        System.out.println("thread1 started");

        // t1.join在main里面执行的，main线程被阻塞了，直到t1线程执行完毕，才执行main函数的线程
        thread1.join();

        thread2.start();
        Thread.sleep(20);
        System.out.println("thread2 started");



    }

    static class JoinThread implements Runnable {
        @Override
        public void run() {
            for(int i=0; i<100; i++) {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
