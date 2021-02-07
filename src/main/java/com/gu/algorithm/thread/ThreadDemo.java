package com.gu.algorithm.thread;

/**
 * @author gu
 * @create 2021/1/25 下午2:33
 */
public class ThreadDemo {
    public static void main(String[] args) {

        new  ThreadTest().start();

        new  Thread(new Runnable()).start();

        new Thread((java.lang.Runnable) new Callable()).start();
    }
}

class ThreadTest extends java.lang.Thread {
    //车票数量
    private int tickets = 100;

    @Override
    public void run() {

        while (tickets > 0) {
            System.out.println(this.getName() + "卖出第【" + tickets-- + "】张火车票");
        }
    }
}

class Runnable implements java.lang.Runnable {

    @Override
    public void run() {

    }
}

class Callable implements java.util.concurrent.Callable {

    @Override
    public Object call() throws Exception {
        return null;
    }
}