package com.gu.algorithm.search;

/**
 * @author gu
 * @create 2020/12/23 下午5:22
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int arr[] = { 1, 8, 10};
        int resIndex=insertValueSearch(arr,0, arr.length-1,10);
        System.out.println("resIndex=" + resIndex);
    }

    public static int insertValueSearch(int[] data, int left, int right, int value) {
        if (left > right || data[0] > value || data[data.length - 1] < value) {
            return -1;
        }

        int middle = left + (right - left) * (value - data[left]) / (data[right] - data[left]);
        int middleData = data[middle];

        if (middleData > value) {
            return insertValueSearch(data, left, middle-1, value);
        } else if (middleData < value) {
            return insertValueSearch(data, middle+1, right, value);
        } else {
            return middle;
        }
    }
}
