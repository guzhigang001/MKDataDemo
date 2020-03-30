package com.ggxiaozhi.lib.class14;

import java.util.TreeMap;

/**
 * Create by ggxz
 * 2020/3/28
 * description: 387. 字符串中的第一个唯一字符
 */
public class Solution {
    public static int firstUniqChar(String s) {

        TreeMap<Character, Integer> map = new TreeMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            if (map.keySet().contains(charAt)) {
                map.put(charAt, map.get(charAt) + 1);
            } else {
                map.put(charAt, 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            if (map.keySet().contains(key)) {
                if (map.get(key) == 1) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
       String s = "loveleetcode";

        System.out.println(firstUniqChar(s));
    }
}
