package name.fw.thread.lock;

import jdk.nashorn.internal.objects.NativeUint8Array;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by fw on 2020/1/8
 */
public class TestReadWriteLock {

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public String  read(){
        Lock lock = readWriteLock.readLock();
        try {
            lock.lock();

            // read

            return "";
        } finally {
            lock.unlock();
        }
    }


    public boolean write(){
        Lock lock = readWriteLock.writeLock();
        try {
            lock.lock();

            // read

            return true;
        } finally {
            lock.unlock();
        }
    }

   static class Singleton{

        private static Singleton ONE;
        private Singleton() {
        }

        public static Singleton getInstance(){
            if (ONE == null) {
                synchronized (Singleton.class) {
                    if (ONE == null) {
                        ONE = new Singleton();
                    }
                }
            }
            return ONE;
        }
    }
}
