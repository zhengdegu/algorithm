package com.gu.algorithm.search;

/**
 * 线性查找
 *
 * @author gu
 * @create 2020/12/23 下午4:42
 */
public class SeqSearch {

    public static void main(String[] args) {
        int arr[] = { 1, 9, 11, -1, 34, 89 };
        int index = seqSearch(arr, -11);
        if(index == -1) {
            System.out.println("没有找到到");
        } else {
            System.out.println("找到，下标为=" + index);
        }
    }
    public static int seqSearch(int[] data, int value) {
        // 线性查找是逐一比对，发现有相同值，就返回下标
        for (int i = 0; i < data.length; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

}
