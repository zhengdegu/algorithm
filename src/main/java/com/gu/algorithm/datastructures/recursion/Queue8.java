package com.gu.algorithm.datastructures.recursion;

/**
 * @author gu
 * @create 2021/1/20 上午11:02
 */
public class Queue8 {
    //定义一个max表示有多少个杭后
    int max = 8;
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {


    }

    //编写一个方法   防止地n个皇后
    private void check(int n) {
        if (n == max) {
            return;
        }
        ;
        //依次放入此皇后  并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后n   放到改行对的第1列
            array[n] = i;
            //判断当放置第 n 个皇后到 i 列时，是否冲突
        }
    }

    //查看当我们放置第 n 个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {

        }

        return    false;
    }
}
