package com.gu.algorithm.search;

/**
 * 二分查找算法
 *
 * @author gu
 * @create 2020/12/23 下午4:46
 */
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 900};
        int resIndex = binarySearch(arr, 0, arr.length - 1, 10);
        System.out.println("resIndex=" + resIndex);
    }

    public static int binarySearch(int[] data, int left, int right, int value) {
        if (right < left || data[0] > value || data[data.length - 1] < value) {
            return -1;
        }
        int middle = left + (right - left) / 2;
        int middleData = data[middle];

        if (middleData < value) {
            return binarySearch(data, middle + 1, right, value);
        } else if (middleData > value) {
            return binarySearch(data, left, middle - 1, value);
        } else {
            return middle;
        }
    }
}
