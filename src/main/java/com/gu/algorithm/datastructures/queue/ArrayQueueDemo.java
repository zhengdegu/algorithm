package com.gu.algorithm.datastructures.queue;

import java.util.Scanner;

/**
 * 1) 队列是一个有序列表，可以用数组或是链表来实现。
 * 2) 遵循先入先出的原则。即：先存入队列的数据，要先取出。后存入的要后取出
 * add数据 rear+1,get数据  front+1;
 *
 * @author gu
 * @create 2021/1/19 上午9:40
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';//接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数据");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
// TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
// TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
    }

// 使用数组模拟队列-编写一个 ArrayQueue 类

    public static class ArrayQueue {
        //队列的大小
        private int maxSize;
        //队列的头
        private int front;
        //队列的尾
        private int rear;
        //用数组存放数据
        private int[] arr;


        //创建队列的构造器
        public ArrayQueue(int maxSize) {
            this.maxSize = maxSize;
            this.arr = new int[maxSize];
            this.front = -1;
            this.rear = -1;
        }

        //判断队列是否满
        private boolean isFull() {
            return rear == maxSize - 1;
        }

        // 判断队列是否为空
        public boolean isEmpty() {
            return rear == front;
        }

        //添加数据到队列
        public void addQueue(int data) {
            if (isFull()) {
                System.out.println("队列满，不能加入数据~");
                return;
            }
            rear++;
            arr[rear] = data;
        }

        //获取队列的数据
        public int getQueue() {
            if (isEmpty()) {
                throw new NullPointerException("队列空，不能取数据");
            }
            front++;
            return arr[front];
        }

        //显示队列的所有数据
        public void showQueue() {
            if (isEmpty()) {
                if (isFull()) {
                    System.out.println("队列满，不能加入数据~");
                    return;
                }
                for (int i = 0; i < arr.length; i++) {
                    System.out.printf("arr[%d]=%d\n", i, arr[i]);
                }
            }
        }

        //显示队列的头数据
        public int headQueue() {
            if (isEmpty()) {
                throw new NullPointerException("队列空，不能取数据");
            }
            return arr[front + 1];
        }
    }


    public static class CircleQueue {
        //队列的大小
        private int maxSize;
        //front 变量的含义做一个调整： front 就指向队列的第一个元素, 也就是说 arr[front] 就是队列的第一个元素
        //front 的初始值 = 0
        private int front;
        //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置. 因为希望空出一个空间做为约定.
        // rear 的初始值 = 0
        private int rear;
        //用数组存放数据
        private int[] arr;


        //创建队列的构造器
        public CircleQueue(int maxSize) {
            this.maxSize = maxSize;
            this.arr = new int[maxSize];
        }

        //判断队列是否满
        private boolean isFull() {
            return (rear + 1) % maxSize == front;
        }

        // 判断队列是否为空
        public boolean isEmpty() {
            return rear == front;
        }

        //添加数据到队列
        public void addQueue(int data) {
            if (isFull()) {
                System.out.println("队列满，不能加入数据~");
                return;
            }
            arr[rear] = data;
            rear = (rear + 1) % maxSize;
        }

        //获取队列的数据
        public int getQueue() {
            if (isEmpty()) {
                throw new NullPointerException("队列空，不能取数据");
            }
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }

        //显示队列的所有数据
        public void showQueue() {
            if (isEmpty()) {
                if (isFull()) {
                    System.out.println("队列满，不能加入数据~");
                    return;
                }
                for (int i = 0; i < arr.length; i++) {
                    System.out.printf("arr[%d]=%d\n", i, arr[i]);
                }
            }
        }

        public int size() {
            // rear = 2
            // front = 1
            // maxSize = 3
            return (rear + maxSize - front) % maxSize;
        }

        //显示队列的头数据
        public int headQueue() {
            if (isEmpty()) {
                throw new NullPointerException("队列空，不能取数据");
            }
            return arr[front ];
        }
    }
}
