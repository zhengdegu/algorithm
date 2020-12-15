package com.gu.algorithm.string;

import java.util.Arrays;

/**
 * 最⻓公共前缀
 * 输⼊: ["flower","flow","flight"]
 * 输出: "fl"
 *
 * @author gu
 * @create 2020/12/15 下午5:36
 */
public class ReplaceSpace {


    public static void main(String[] args) {
        String[] strs = {"customer", "car", "cat"};
        // String[] strs = { "customer", "car", null };//空 // String[] strs = {};//空串
        // String[] strs = null;//空串
        System.out.println(replaceSpace(strs));// c
    }

    public static String replaceSpace(String[] data) {
        //对字符串进行null判断


        int length = data.length;
        StringBuilder stringBuilder = new StringBuilder();
        Arrays.sort(data);
        int one = data[0].length();
        int two = data[length - 1].length();
        int min = Math.min(one, two);
        for (int i = 0; i < min - 1; i++) {
            if (data[0].charAt(i) == data[length - 1].charAt(i)) {
                stringBuilder.append(data[0].charAt(i));
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }
}
