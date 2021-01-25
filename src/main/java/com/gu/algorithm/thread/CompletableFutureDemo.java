package com.gu.algorithm.thread;

import org.junit.Assert;

import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * @author gu
 * @create 2021/1/12 下午2:13
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        System.out.println("创建一个完成的completableFuture");
        completableFuture();
        System.out.println("运行一个简单的异步阶段");
        runAsyncExample();
        System.out.println("在前一个阶段上应用函数");
        thenApplyExample();
        System.out.println("在前一个阶段应用异步函数");
        thrnApplyAsyncExample();
        System.out.println("使用定制的Executor在前一个阶段上异步应用函数");
        thenApplyAsyncWithExecutorExample();
        System.out.println("消费前一阶段的结果");
        thenAcceptExample();
        System.out.println("异步消费前一阶段的结果");
        thenAcceptAsyncExample();

        applyToEitherExample();
    }

    //创建一个完成的completableFuture
    public static void completableFuture() {
        CompletableFuture<String> message = CompletableFuture.completedFuture("message");
        assertTrue(message.isDone());
        assertEquals("message", message.getNow(null));
    }

    //运行一个简单的一步阶段
    public static void runAsyncExample() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Assert.assertFalse(voidCompletableFuture.isDone());
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(voidCompletableFuture.isDone());
    }

    //在前一个阶段上应用函数
    public static void thenApplyExample() {
        CompletableFuture<String> message = CompletableFuture.completedFuture("message").thenApply(s -> {
            Assert.assertFalse(Thread.currentThread().isDaemon());
            return s.toUpperCase();
        });
        assertEquals("MESSAGE", message.getNow(null));
    }

    //在前一个阶段应用异步函数
    public static void thrnApplyAsyncExample() {
        CompletableFuture<Object> message = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().isDaemon());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        });
        Assert.assertNull(message.getNow(null));
        assertEquals("MESSAGE", message.join());
    }

    //使用定制的Executor在前一个阶段上异步应用函数
    static ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    //使用定制的Executor在前一个阶段上异步应用函数
    public static void thenApplyAsyncWithExecutorExample() {
        CompletableFuture<Object> message = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
            assertTrue(Thread.currentThread().getName().startsWith("custom-executor-"));
            Assert.assertFalse(Thread.currentThread().isDaemon());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        }, executorService);
        Assert.assertNull(message.getNow(null));
        assertEquals("MESSAGE", message.join());
    }

    //消费前一阶段的结果
    public static void thenAcceptExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture("thenAccept message").thenAccept(s -> result.append(s));
        assertTrue("Result was empty", result.length() > 0);
    }

    //异步消费前一阶段的结果
    public static void thenAcceptAsyncExample() {
        StringBuilder result = new StringBuilder();
        CompletableFuture<Void> thenAcceptAsync = CompletableFuture.completedFuture("thenAcceptAsync").thenAcceptAsync(s -> result.append(s));
        thenAcceptAsync.join();
        assertTrue("Result was empty", result.length() > 0);
    }

    //完成计算异常
    public static void completeExceptionallyExample() {
        CompletableFuture<Void> message = CompletableFuture.completedFuture("message")
                .thenAcceptAsync(String::toUpperCase, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
        CompletableFuture<Object> handle = message.handle((s, th) -> {
            return (th != null) ? "message upon  cancle" : "";
        });
        message.completeExceptionally(new RuntimeException("completed exceptionally"));
        assertTrue("Was not completed exceptionally", message.isCompletedExceptionally());
        try {
            message.join();
            fail("Should have thrown an exception");
        } catch (CompletionException ex) { // just for testing
            assertEquals("completed exceptionally", ex.getCause().getMessage());
        }

        assertEquals("message upon cancel", handle.join());
    }

    //在两个完成的阶段其中之一上应用函数
    static void applyToEitherExample() {
        String original = "Message";
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.completedFuture(original).thenApplyAsync(s -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s.toUpperCase();
        });
        CompletableFuture<String> toEither = stringCompletableFuture.applyToEither(
                CompletableFuture.completedFuture(original).thenApplyAsync(s -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return s.toUpperCase();
                }),
                s -> s + " from applyToEither"
        );
        assertTrue(stringCompletableFuture.join().endsWith("MESSAGE"));
        assertTrue(toEither.join().endsWith(" from applyToEither"));
    }

    //在两个完成的阶段其中之一上调用消费函数
    static void acceptEitherExample() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(s -> delayedUpperCase(s))
                .acceptEither(CompletableFuture.completedFuture(original).thenApplyAsync(s -> delayedLowerCase(s)),
                        s -> result.append(s).append("acceptEither"));
        cf.join();
        assertTrue("Result was empty", result.toString().endsWith("acceptEither"));
    }

    // 在两个阶段都执行完后运行一个Runnable
    static void runAfterBothExample() {
        String original = "Meassge";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original)
                .thenApply(String::toUpperCase)
                .runAfterBoth(
                        CompletableFuture.completedFuture(original)
                                .thenApply(String::toLowerCase),
                        () -> result.append("node")
                );
        assertTrue("Result was empty", result.length() > 0);
    }

    //使用BiConsumer处理两个阶段的结果
    static void thenacceptBothExample() {
        String original = "Message";
        StringBuilder resulr = new StringBuilder();
        CompletableFuture.completedFuture(original)
                .thenApply(String::toUpperCase)
                .thenAcceptBoth(
                        CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                        (s1, s2) -> resulr.append(s1 + s2)
                );
        assertEquals("MESSAGEmessage", resulr.toString());
    }


    private static Object delayedUpperCase(String s) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s.toUpperCase();
    }

    private static String delayedLowerCase(String s) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return s.toLowerCase();
    }

}
