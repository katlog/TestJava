package name.fw.thread.object;

import org.junit.Test;

/**
 * Created by fw on 2019/7/31
 */
public class TestConsumerSupllier {

    private static Object resource ;

    static class Consumer implements Runnable{
        private Object lock;

        public Consumer(Object lock) {
            this.lock = lock;
        }
        @Override
        public void run() {
            synchronized (lock) {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (resource != null) {
                        System.out.println("resource = " + resource);
                        resource = null;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

   static class Supplier implements Runnable{
        private static Integer emitter = 0;

        private Object lock;
        public Supplier(Object lock) {
            this.lock = lock;
        }
        @Override
        public void run() {
            synchronized (lock) {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (resource == null) {
                        resource = emitter++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Test
    public void synchronizedWay() {
        Object lock = new Object();
        new Thread(new Consumer(lock)).start();
        new Thread(new Consumer(lock)).start();
        new Thread(new Consumer(lock)).start();
        new Thread(new Consumer(lock)).start();
        new Thread(new Supplier(lock)).start();
        new Thread(new Supplier(lock)).start();
    }

    @Test
    public void blockingWay(){

    }

}
