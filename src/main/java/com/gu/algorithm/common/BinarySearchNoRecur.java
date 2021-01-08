package com.gu.algorithm.common;

/**
 * 二分查找的非递归
 * <p>
 * 二分查找法只适用于从有序的数列中进行查找(比如数字和字母等)，将数列排序后再进行查找
 * 二分查找法的运行时间为对数时间 O(㏒₂n) ，即查找到需要的目标位置最多只需要㏒₂n 步，假设从[0,99]的
 * 队列(100 个数，即 n=100)中寻到目标数 30，则需要查找步数为㏒₂100 , 即最多需要查找 7 次( 2^6 < 100 < 2^7)
 *
 * @author gu
 * @create 2020/12/11 下午1:15
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, 9);
        System.out.println(index);



    }

    public static int binarySearch(int[] data, int target) {
        int left = 0;
        int right = data.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (data[mid] == target) {
                return mid;
            } else if (data[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }
}
