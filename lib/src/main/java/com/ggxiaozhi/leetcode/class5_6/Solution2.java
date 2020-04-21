package com.ggxiaozhi.leetcode.class5_6;

/**
 * Create by ggxz
 * 2020/4/21
 * description:
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution2 {

    public static List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // Stack 这个类 Java 的文档里推荐写成 Deque<Integer> stack = new ArrayDeque<Integer>();
        // 注意：只使用 stack 相关的接口
        Deque<String> stack = new ArrayDeque<>();
        backtracking(s, 0, len, stack, res);
        return res;
    }

    /**
     * @param s
     * @param start 起始字符的索引
     * @param len   字符串 s 的长度，可以设置为全局变量
     * @param path  记录从根结点到叶子结点的路径
     * @param res   记录所有的结果
     */
    private static void backtracking(String s, int start, int len, Deque<String> path, List<List<String>> res) {
        System.out.println("into->backtracking  s= " + s + " from start= " + getDepth(start) + " len = " + len + " path= " + path + " res + " + res);
        if (start == len) {
            res.add(new ArrayList<>(path));
            System.out.println("end--->start == len  s= " + s + " from start= " + getDepth(start) + " len = " + len + " path= " + path + " res + " + res);
            return;
        }

        for (int i = start; i < len; i++) {
            System.out.println("开始循环  start = " + getDepth(start) + " i = " + i);

            // 因为截取字符串是消耗性能的，因此，采用传子串索引的方式判断一个子串是否是回文子串
            // 不是的话，剪枝
            System.out.println("checkPalindrome -> " + s.substring(start, i + 1));
            if (!checkPalindrome(s, start, i)) {

                continue;
            }

            System.out.println("------path add = " + s.substring(start, i + 1));
            path.addLast(s.substring(start, i + 1));
            backtracking(s, i + 1, len, path, res);
            System.out.println("------before removeLast-> " + path);
            path.removeLast();
            System.out.println("------after removeLast->" + path);


        }
    }

    /**
     * 这一步的时间复杂度是 O(N)，因此，可以采用动态规划先把回文子串的结果记录在一个表格里
     *
     * @param str
     * @param left  子串的左边界，可以取到
     * @param right 子串的右边界，可以取到
     * @return
     */
    private static boolean checkPalindrome(String str, int left, int right) {
        // 严格小于即可
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static String getDepth(int start) {
        StringBuilder builder = new StringBuilder();
        builder.append(start).append("->");
        for (int i = 0; i < start * 3; i++) {
            builder.append("==");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        List<List<String>> aab = partition("aab");
        System.out.println(aab);
    }
}

