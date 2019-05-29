package name.fw.thread.excutor;

import org.junit.Test;

import javax.swing.*;
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


        System.out.println(Thread.currentThread() + ":第1次 unlock ");
        lock.unlock();
        System.out.println(Thread.currentThread() + ":第2次 unlock ");
        lock.unlock();

    }
}
