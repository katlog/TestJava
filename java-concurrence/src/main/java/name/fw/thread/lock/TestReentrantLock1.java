package name.fw.thread.lock;

import org.junit.Test;

import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by fw on 2019/5/17
 */
public class TestReentrantLock1 {

    @Test
    public void lock(){
        ReentrantLock lock = new ReentrantLock();

        System.out.println(Thread.currentThread() + ":第1次 lock ");
        lock.lock();
        System.out.println(Thread.currentThread() + ":第2次 lock ");
        lock.lock();

        new Thread(() -> {
            System.out.println(Thread.currentThread() + ":第1次 lock ");
            lock.lock();

            System.out.println(Thread.currentThread() + ":第1次 unlock ");
            lock.unlock();

        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread() + ":第1次 lock ");
            try {
                lock.tryLock(10,TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread() + ":第1次 unlock ");
            lock.unlock();
        });

        System.out.println(Thread.currentThread() + ":第1次 unlock ");
        lock.unlock();
        System.out.println(Thread.currentThread() + ":第2次 unlock ");
        lock.unlock();

    }
}
