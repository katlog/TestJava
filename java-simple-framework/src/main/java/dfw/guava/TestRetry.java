package dfw.guava;

import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by fw on 2019/9/10
 */
public class TestRetry {


    class TaskCallable implements Callable<Boolean> {
        @Override
        public Boolean call() throws Exception {
            return false;
        }
    }

    class MyRetryListener implements RetryListener {

        @Override
        public <V> void onRetry(Attempt<V> attempt) {
            System.out.print("[retry]time=" + attempt.getAttemptNumber());
            // 距离第一次重试的延迟
            System.out.print(",delay=" + attempt.getDelaySinceFirstAttempt());

            // 重试结果: 是异常终止, 还是正常返回
            System.out.print(",hasException=" + attempt.hasException());
            System.out.print(",hasResult=" + attempt.hasResult());

            // 是什么原因导致异常
            if (attempt.hasException()) {
                System.out.print(",causeBy=" + attempt.getExceptionCause().toString());
            } else {
                // 正常返回时的结果
                System.out.print(",result=" + attempt.getResult());
            }
            System.out.println();
        }

    }


    @Test
    public void retry() {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder().
                //如果异常会重试
                        retryIfException().
                //如果结果为false会重试
                        retryIfResult(Predicates.equalTo(false)).
                //重调策略
                        withWaitStrategy(WaitStrategies.fixedWait(10, TimeUnit.SECONDS)).
                //尝试次数
                        withStopStrategy(StopStrategies.stopAfterAttempt(3)).
                //注册监听
                        withRetryListener(new MyRetryListener()).build();
        try {
            retryer.call(new TaskCallable());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}