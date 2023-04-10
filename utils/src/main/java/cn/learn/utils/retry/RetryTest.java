package cn.learn.utils.retry;

import com.github.rholder.retry.*;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author shaoyijiong
 * @date 2023/4/10
 */
public class RetryTest {


    public static Boolean retryFunction() {
        System.out.println("retry");
        int i = RandomUtils.nextInt(0, 7);
        System.out.println(i);
        if (i > 5) {
            return true;
        }
        if (i < 2) {
            return false;
        }
        throw new RuntimeException("retry");
    }

    public static void main(String[] args) throws ExecutionException, RetryException {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                // 抛出异常后重试
                .retryIfExceptionOfType(RuntimeException.class)
                // 结果为false后重试
                .retryIfResult(aBoolean -> !aBoolean)
                // 重试5次
                .withStopStrategy(StopStrategies.stopAfterAttempt(5))
                // 每次重试间隔500毫秒
                .withWaitStrategy(WaitStrategies.fixedWait(500, TimeUnit.MILLISECONDS))
                .build();
        retryer.call(RetryTest::retryFunction);
    }
}