package com.gu.algorithm.zijie.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gu
 * @create 2021/1/26 下午4:25
 */
public class SolutionDemo {
}

/**
 * 无重复字符的最长子串
 *
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

}




