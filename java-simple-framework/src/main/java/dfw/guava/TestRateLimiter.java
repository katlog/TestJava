package dfw.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by fw on 2020/7/22
 */
public class TestRateLimiter {

    @Test
    public void _common() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(50.0);
        ExecutorService excutor = Executors.newFixedThreadPool(10);

        // 10个线程  每个线程1秒执行10次  10个线程每秒共执行100次
        // 限流每秒50次
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            excutor.submit(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (rateLimiter.tryAcquire()) {
                    System.out.println("获取到："+Thread.currentThread().getName() + "i = " + finalI);
                } else {
                    System.out.println("限流了："+Thread.currentThread().getName() + "i = " + finalI);
                }
            });
        }
        excutor.awaitTermination(10, TimeUnit.SECONDS);
    }
    
    @Test
    public void testSmoothBursty(){
        RateLimiter r = RateLimiter.create(5);
        while (true) {
            System.out.println("get 1 tokens: " + r.acquire() + "s");
        }
    }

    @Test
    public void testSmoothBursty2() {
        RateLimiter r = RateLimiter.create(2);
        while (true){
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {}
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("end");
        }
    }

    @Test
    public void testSmoothBursty3() {
        RateLimiter r = RateLimiter.create(5);
        while (true)        {
            System.out.println("get 5 tokens: " + r.acquire(5) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("end");
        }
    }


    @Test
    public void testSmoothwarmingUp() {
        RateLimiter r = RateLimiter.create(2, 3, TimeUnit.SECONDS);
        while (true){
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("end");
        }
    }


}
