package com.gu.algorithm.datastructures.sort;

import java.util.Arrays;

/**
 * @author gu
 * @create 2021/1/20 上午11:06
 */
public class BubbleSort {
    public static void main(String[] args) {
        //创建要给 80000 个的随机的数组
        int[] arr = new int[80000];
        for(int i =0; i < 80000;i++) {
            arr[i] = (int)(Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
        //测试冒泡排序
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] bubbleSort(int[] data) {

        int length = data.length;
        boolean flag = false;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    flag = true;
                }
            }
            if (flag) {
                flag = false;
            } else {
                break;
            }
        }
        return data;
    }
}
