package com.gu.algorithm.datastructures.sort;

import java.util.Arrays;

/**
 * 选择排序（select sorting）也是一种简单的排序方法。它的基本思想是：
 * 第一次从 arr[0]~arr[n-1]中选取最小值，与 arr[0]交换，
 * 第二次从 arr[1]~arr[n-1]中选取最小值，与 arr[1]交换，
 * 第三次从 arr[2]~arr[n-1]中选取最小值，与 arr[2]交换，…，
 * 第 i 次从 arr[i-1]~arr[n-1]中选取最小值，与 arr[i-1]交换，…,
 * 第 n-1 次从 arr[n-2]~arr[n-1]中选取最小值，与 arr[n-2]交换，总共通过 n-1 次，得到一个按排序码从小到大排列的有序序列
 *
 * @author gu
 * @create 2021/1/20 上午11:16
 */
public class SelectSort {

    public static void main(String[] args) {
        //创建要给 80000 个的随机的数组
        int[] arr = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
        //测试冒泡排序
        selectSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
