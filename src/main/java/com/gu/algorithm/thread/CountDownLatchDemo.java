package com.gu.algorithm.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gu
 * @create 2021/1/12 下午2:06
 */
public class CountDownLatchDemo {
    private static final int SIZE = 6;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(SIZE);
        final CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        for (int i = 0; i < SIZE; i++) {
            executorService.submit(() -> {
                try {
                    System.out.println("处理⽂件的业务操作");
                }catch (Exception e){

                }finally {
                    countDownLatch.countDown();
                }

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("finish");
    }
}
