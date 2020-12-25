package com.gu.algorithm.retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author gu
 * @create 2020/12/24 下午2:29
 */
@EnableRetry
@SpringBootApplication
public class RetryApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(RetryApplication.class, args);
        Retry service1 = run.getBean("retry", Retry.class);
        System.out.println(service1.service());
    }

    @Component("retry")
    public class Retry {

        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        @Retryable(value = IllegalAccessException.class, maxAttempts = 5,
                backoff = @Backoff(value = 1500, maxDelay = 100000, multiplier = 1.2))
        public String service() throws IllegalAccessException {
            System.out.println("service method...");
            if (!atomicBoolean.get()) {
                atomicBoolean.set(true);
                throw new IllegalAccessException("manual exception");
            } else {
                return "成功";
            }
        }

        @Recover
        public String recover(IllegalAccessException e) {
            System.out.println("service retry after Recover => " + e.getMessage());
            return "失败";
        }
    }

}
