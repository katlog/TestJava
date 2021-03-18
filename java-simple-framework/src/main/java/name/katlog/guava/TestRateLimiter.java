package name.katlog.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 令牌桶
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


    /**
     *  平滑突发：创建一个稳定输出令牌的 RateLimiter，保证平均每秒不超过permitsPerSecond个请求
     *  当请求到来的速度超过了permitsPerSecond，保证每秒只处理permitsPerSecond个请求
     *  当这个RateLimiter使用不足(即请求到来速度小于permitsPerSecond)，会囤积最多permitsPerSecond个请求
     *
     *
     * */
    @Test
    public void testSmoothBursty(){

        // 基本上都是0.2s执行一次，符合一秒发放5个令牌的设定。
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


    /**
     * 平滑预热： 创建一个稳定输出令牌的RateLimiter，保证了平均每秒不超过permitsPerSecond个请求
     *  还包含一个热身期(warmup period),热身期内，RateLimiter会平滑的将其释放令牌的速率加大，直到起达到最大速率
     *  同样，如果RateLimiter在热身期没有足够的请求(unused),则起速率会逐渐降低到冷却状态
     *
     *  参数warmupPeriod和unit决定了其从冷却状态到达最大速率的时间
     * */
    @Test
    public void testSmoothwarmingUp() {

        // 预热5秒，才会到最大速率（即2个/s）
        RateLimiter r = RateLimiter.create(3, 5, TimeUnit.SECONDS);
        while (true){
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("get 1 tokens: " + r.acquire(1) + "s");
            System.out.println("end");
        }
    }


}
