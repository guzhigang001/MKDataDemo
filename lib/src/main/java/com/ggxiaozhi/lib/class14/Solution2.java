package com.ggxiaozhi.lib.class14;

import java.util.HashMap;

/**
 * Create by ggxz
 * 2020/3/28
 * description:
 */
public class Solution2 {

    public static int firstUniqChar(String s) {

        int[] ints = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            ints[charAt - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            if (ints[charAt - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        String s = "leetcode";

        System.out.println(firstUniqChar(s));
    }
}
