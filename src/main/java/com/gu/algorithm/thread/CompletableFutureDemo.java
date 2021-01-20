package com.gu.algorithm.thread;

import java.util.concurrent.CompletableFuture;

/**
 * @author gu
 * @create 2021/1/12 下午2:13
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        CompletableFuture<Void> task1=CompletableFuture.supplyAsync(() -> {
            System.out.println("task1");
            return null;
        });
        CompletableFuture<Void> task2=CompletableFuture.supplyAsync(() -> {
            System.out.println("task2");
            return null;
        });

        CompletableFuture<Void> task3=CompletableFuture.supplyAsync(() -> {
            System.out.println("task3");
            return null;
        });
    }
}
