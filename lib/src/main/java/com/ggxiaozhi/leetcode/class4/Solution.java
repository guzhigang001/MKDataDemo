package com.ggxiaozhi.leetcode.class4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Create by ggxz
 * 2020/4/13
 * description: set和map相关的算法
 * 242 202 290 205 451
 */
@SuppressWarnings("SuspiciousMethodCalls")
public class Solution {

    /**
     * 242 暴力解法 时间3n 空间2n
     */
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length())
            return false;

        //<元素，元素出现的次数>
        Map<Character, Integer> maps = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (maps.containsKey(c)) {
                maps.put(c, maps.get(c) + 1);
            } else {
                maps.put(c, 1);
            }
        }

        Map<Character, Integer> mapt = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (maps.containsKey(c)) {
                if (mapt.containsKey(c)) {
                    mapt.put(c, mapt.get(c) + 1);
                } else {
                    mapt.put(c, 1);
                }
            } else {
                return false;
            }
        }
        for (Character character : maps.keySet()) {
            Integer vs = maps.get(character);
            Integer vt = mapt.get(character);
            if (!vs.equals(vt))
                return false;
        }
        return true;
    }

    /**
     * 242 优化 根据题意知道 字符中只包含小写字母  可以使用数组映射 时间n 空间 1
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length())
            return false;
        int n = s.length();//因为长度相等 那么取值谁都行
        int[] arr = new int[26];
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 202. 快乐数
     */
    public static boolean isHappy(int n) {
        String value = String.valueOf(n);

        Set<Integer> set = new HashSet<>();
        int res = 0;
        while (true) {

            for (int i = 0; i < value.length(); i++) {
                char c = value.charAt(i);
                res = res + ((int) Math.pow(c - '0', 2));
            }
            if (res == 1)
                return true;
            if (set.contains(res)) {
                return false;
            } else {
                set.add(res);
                value = String.valueOf(res);
                res = 0;
            }
        }
    }


    /**
     * 202. 快乐数 快慢指针 看时间复杂度应该差不多 一个数量级 但是没有辅助空间的消耗
     */
    public static boolean isHappy2(int n) {


        int slow = n, fast = n;
        do {

            slow = sum(slow);
            fast = sum(fast);
            fast = sum(fast);

        } while (slow != fast);

        return slow == 1;
    }

    public static int sum(int n) {
        int sum = 0;
        while (n > 0) {

            int b = n % 10;//个位数
            sum = sum + b * b;
            n = n / 10;//向高位再去值
        }
        return sum;
    }

    /**
     * 290. 单词规律 双哈希 存下标
     */
    public boolean wordPattern(String pattern, String str) {
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(19));
        String str = "Hello I'm your String";//空格可以任意多个
        String[] splited = str.split(" ");//这样写就可以了
        System.out.println(splited);
    }
}
