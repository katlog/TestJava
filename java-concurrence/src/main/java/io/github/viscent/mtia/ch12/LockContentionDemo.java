package io.github.viscent.mtia.ch12;

import io.github.viscent.mtia.util.Tools;

public class LockContentionDemo {

    // 用于模拟锁的持有时间跨度
    static long lockDuration = 100;
    static SharedResource sr = new SharedResource();
    // 用于模拟锁申请频率
    static long lockAccessFrequency = 50;

    public static void main(String[] args) throws InterruptedException {
        int argc = args.length;
        if (argc > 0) {
            lockDuration = Long.valueOf(args[0]);
            if (argc > 1) {
                lockAccessFrequency = Long.valueOf(args[1]);
            }
        }
        int N = Runtime.getRuntime().availableProcessors();
        Thread[] threads = new Thread[N];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    for (; ; ) {
                        sr.access();
                        try {
                            Thread.sleep(lockAccessFrequency);
                        } catch (InterruptedException ignored) {
                        }
                    }
                }// run结束
            };

        }// for结束

        // 启动所有线程
        Tools.startThread(threads);
        Tools.delayedAction("The program will be terminated", new Runnable() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 120);
    }

    static class SharedResource {
        public synchronized void access() {
            // 模拟实际操作耗时
            try {
                Thread.sleep(lockDuration);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
