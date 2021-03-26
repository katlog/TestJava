package name.katlog.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by fw on 2021/3/18
 */
public class TestHystrix {

    static class CommonHystrixCommand extends HystrixCommand<String> {
        private final String name;
        public CommonHystrixCommand(String name) {
            super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
            this.name = name;
        }
        @Override
        protected String run() {
            return this.name + ":" + Thread.currentThread().getName();
        }
    }

    /** 普通调用 */
    @Test
    public void commonRun(){
        String result = new CommonHystrixCommand("katlog").execute();
        System.out.println(result);
    }

    /** 普通的异步调用 */
    @Test
    public void asyncRun() throws ExecutionException, InterruptedException {
        Future<String> future = new CommonHystrixCommand("katlog").queue();
        System.out.println(future.get());
    }

    static class FallbackHystrixCommand extends HystrixCommand<String> {
        private final String name;
        public FallbackHystrixCommand(String name) {
            super(HystrixCommandGroupKey.Factory.asKey("MyGroup"));
            this.name = name;
        }
        @Override
        protected String run() {
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.name + ":" + Thread.currentThread().getName();
        }
        @Override
        protected String getFallback() {
            return " 失败了 ";
        }
    }

    /** 失败 */
    @Test
    public void fallback(){
        String result = new FallbackHystrixCommand("katlog").execute();
        System.out.println(result);
    }


    /** 信号量策略配置，在andCommandPropertiesDefaults（还有超时、并发量、断路器、metrics） */
    static class ConfigSemaphoreHystrixCommand extends HystrixCommand<String> {
        private final String name;
        public ConfigSemaphoreHystrixCommand(String name) {
            super(HystrixCommand.Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
                    .andCommandPropertiesDefaults( HystrixCommandProperties.Setter()
                            .withExecutionIsolationStrategy(
                                    HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE
                            )
                    )
            );
            this.name = name;
        }
        @Override
        protected String run() throws Exception {
            return null;
        }
    }


    /** 线程池策略配置 */
    static class ConfigHystrixCommand extends HystrixCommand<String> {
        private final String name;
        public ConfigHystrixCommand(String name) {
            super(HystrixCommand.Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("MyGroup"))
                    .andCommandPropertiesDefaults(
                            HystrixCommandProperties.Setter()
                                    .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                    ).andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                            .withCoreSize(10)
                            .withMaxQueueSize(100)
                            .withMaximumSize(100)
                    )
            );
            this.name = name;
        }
        @Override
        protected String run() throws Exception {
            return null;
        }
    }
}
