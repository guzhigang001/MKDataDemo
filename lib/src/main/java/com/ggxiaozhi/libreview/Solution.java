package com.ggxiaozhi.libreview;

/**
 * Create by ggxz leetcode上找队列标签  中级难度 字符串转数字
 * 2020/4/3
 * description:
 */
public class Solution {
    public static int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        int idx = 0;
        while (idx < n && chars[idx] == ' ') {
            // 去掉前导空格
            idx++;
        }
        if (idx == n) {
            //去掉前导空格以后到了末尾了
            return 0;
        }
        boolean negative = false;
        if (chars[idx] == '-') {
            //遇到负号
            negative = true;
            idx++;
        } else if (chars[idx] == '+') {
            // 遇到正号
            idx++;
        } else if (!Character.isDigit(chars[idx])) {
            // 其他符号
            return 0;
        }
        int ans = 0;
        while (idx < n && Character.isDigit(chars[idx])) {
            int digit = chars[idx] - '0';
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }
        return negative ? -ans : ans;
    }

    public static void main(String[] args) {

        String s = "04193 with words";
        System.out.println(myAtoi(s));
        System.out.println();
        System.out.println("5");
    }

    public static int yAtoi(String str) {
        char[] chars = str.toCharArray();
        int idx = 0;

        boolean negative = false;
        if (chars[idx] == ' ') {
            idx++;
        } else if (chars[idx] == '-') {
            idx++;
            negative = true;
        } else if (!Character.isDigit(chars[idx])) {
            return 0;
        }

        int ans = 0;
        while (idx < chars.length && Character.isDigit(chars[idx])) {
            int digit = chars[idx] - '0';
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }
        return negative ? -ans : ans;
    }
}
