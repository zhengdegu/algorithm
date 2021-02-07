package com.gu.algorithm.thread;

/**
 * @author gu
 * @create 2021/2/7 下午3:50
 */
public class DeadLockDemo {
    private static Object resource1 = new Object();//资源 1
    private static Object resource2 = new Object();//资源 2

    public static void main(String[] args) {
        new Thread(()->{
             synchronized (resource1){
                 System.out.println(Thread.currentThread()+"get resource1");
                 try {
                     Thread.sleep(1000);
                 }catch (Exception e){
                     e.printStackTrace();
                 }
                 System.out.println(Thread.currentThread()+"waiting get resource2");
                 synchronized (resource2){
                     System.out.println(Thread.currentThread()+"get  resource2");
                 }
             }
        },"线程1").start();
        new Thread(()->{
            synchronized (resource2) {
                System.out.println(Thread.currentThread() + "get resource2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resource1");
                synchronized (resource1) {
                    System.out.println(Thread.currentThread() + "get resource1");
                }
            }
        },"线程2");
    }
}