package com.gu.algorithm.common;

/**
 * KMP 方法算法就利用之前判断过信息， 通过一个 next 数组， 保存模式串中前后最长公共子序列的长度， 每次
 * 回溯时， 通过 next 数组找到， 前面匹配过的位置， 省去了大量的计算时间
 *
 * @Author gu
 * @create 2020/12/13 下午4:06
 */
public class ViolenceMatch {


    //暴力匹配算法的实现
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Length = s1.length;
        int s2Length = s2.length;
        int i = 0;
        int j = 0;
        while (i < s1Length && j < s2Length) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == s2Length) {
            return i - j;
        }
        return -1;
    }
}
