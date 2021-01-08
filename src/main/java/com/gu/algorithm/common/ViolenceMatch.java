package com.gu.algorithm.common;

import java.util.Stack;

/**
 * KMP 方法算法就利用之前判断过信息， 通过一个 next 数组， 保存模式串中前后最长公共子序列的长度， 每次
 * 回溯时， 通过 next 数组找到， 前面匹配过的位置， 省去了大量的计算时间
 *
 * @Author gu
 * @create 2020/12/13 下午4:06
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "ABCEABCD";
        String s2 = "ABCD";
        System.out.println(violenceMatch(s1, s2));
        System.out.println(judge(0));
    }

    //获取到一个字符串(子串) 的部分匹配值表
//    public static int[] kmpNext(String dest) {
//
//    }


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


    public static String judge(int score) {
//        if (score < 0) {
//            throw new IllegalAccessException("分数异常：" + score);
//        }
        return score >= 90 ? "A" : score >= 60 ? "B" : "C";
    }


    public static class Solution {
        boolean invalidInput = false;

        public double Power(double base, int exponent) {
            int negative = exponent;
            if (this.equal(base, 0.0) && exponent < 0) {
                return 0d;
            }
            if (exponent < 0) {
                exponent = -exponent;
            }
            double power = getPower(base, exponent);
            if (negative < 0) {
                power = 1 / power;
            }
            return power;
        }

        double getPower(double b, int e) {
            if (e == 0) {
                return 1d;
            }
            if (e == 1) {
                return b;
            }
            double power = getPower(b, e >> 1);
            power *= power;
            if ((e & 1) == 1) {
                power *= b;
            }
            return power;
        }

        boolean equal(double num1, double num2) {
            if (num1 - num2 > -0.000001 && num1 - num1 < 0.000001) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 输⼊两个整数序列，第⼀个序列表示栈的压⼊顺序，请判断第⼆个序列是否为该栈的弹出顺序。假设压
     * ⼊栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压⼊顺序，序列5，4,3,2,1是该压栈序列对应
     * 的⼀个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的⻓度是相等
     * 的）
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA.length == 0 || popA.length == 0) {
            return false;
        }
        Stack<Integer> stack = new Stack<>();
        //⽤于标识弹出序列的位置
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.empty() && stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.empty();
    }
}
