package com.gu.algorithm.datastructures.sort;

import java.util.Arrays;

/**
 *
 * 快速排序（Quicksort）是对冒泡排序的一种改进。基本思想是：通过一趟排序将要排序的数据分割成独立的两
 * 部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排
 * 序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 * @author gu
 * @create 2021/1/20 下午2:25
 */
public class QuickSort {

    public static void main(String[] args) {

        int [] data=new int[]{1,5,2,4,9,8};
        quickSort(data,0,data.length-1);
        System.out.println(Arrays.toString(data));
    }
    public static void quickSort(int[] arr, int left, int right) {
        int r = right, l = left, temp = 0, middle = arr[(left + right) / 2];
        while (l < r) {

            while (arr[l] < middle) {
                l++;
            }

            while (arr[r] > middle) {
                r--;
            }
            if (l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            if (arr[l] == middle) {
                r--;
            }
            if (arr[r] == middle) {
                l++;
            }

        }

        if (r == l) {
            r--;
            l++;
        }

        if (left < r){
            quickSort(arr, left, r);
        }

        if (l<right){
            quickSort(arr, l, right);
        }
    }
}
