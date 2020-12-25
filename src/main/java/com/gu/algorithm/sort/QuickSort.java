package com.gu.algorithm.sort;

import java.util.Arrays;

/**
 * @author gu
 * @create 2020/12/21 上午10:32
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] data = new int[]{-9, 78, 0, 23, -567, 70, -1, 900, 4561};
        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(quickSort(data, 0, data.length - 1)));
    }

    public static int[] quickSort(int[] data, int left, int right) {
        int l = left;
        int r = right;
        int temp = 0;
        int middle = data[(r + l) / 2];

        while (l < r) {
            while (data[l] < middle) {
                l++;
            }
            while (data[r] > middle) {
                r--;
            }
            if (l >= r) {
                break;
            }
            temp = data[l];
            data[l] = data[r];
            data[r] = temp;

            if (data[l] == middle) {
                r--;
            }

            if (data[r] == middle) {
                l++;
            }
        }
        if (l == r) {
            l++;
            r--;
        }
        if (left < r) {
            quickSort(data, left, r);
        }

        if (right > l) {
            quickSort(data, l, right);
        }
        return data;
    }
}
