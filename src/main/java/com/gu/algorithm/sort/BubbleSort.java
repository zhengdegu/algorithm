package com.gu.algorithm.sort;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * 冒泡排序（Bubble Sorting）的基本思想是：通过对待排序序列从前向后（从下标较小的元素开始）,依次比较
 * 相邻元素的值，若发现逆序则交换，使值较大的元素逐渐从前移向后部，就象水底下的气泡一样逐渐向上冒。
 *
 * @author gu
 * @create 2020/12/11 上午9:15
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] data = new int[]{-9, 78, 0, 23, -567, 70, -1, 900, 4561};
        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(bubbleSort(data)));
        System.out.println(Arrays.toString(quickSort(data, 0, data.length - 1)));
        Stream.of(data).sorted().forEach(a -> {
            System.out.println(Arrays.toString(a));
        });


    }


    public static int[] quickSort(int[] data, int left, int right) {
        int l = left;
        int r = right;
        int temp = 0;
        int middle = data[(r + l) / 2];

        while (l < r) {

            while (data[l] < middle) {
                l += 1;
            }
            while (data[r] > middle) {
                r -= 1;
            }
            if (l >= r) {
                break;
            }
            temp = data[l];
            data[l] = data[r];
            data[r] = temp;

            if (data[l] == middle) {
                r -= 1;
            }
            if (data[r] == middle) {
                l += 1;
            }
        }
        if (l == r) {
            l += 1;
            r -= 1;
        }
        if (r > left) {
            quickSort(data, left, r);
        }
        if (l < right) {
            quickSort(data, l, right);
        }
        return data;
    }

    public static int[] bubbleSort(int[] data) {
        //临时变量
        int temp = 0;
        //标志是否交换数据
        boolean flag = false;
        int length = data.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    flag = true;
                }

            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
        return data;
    }


}
