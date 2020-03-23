package com.ggxiaozhi.review;

import com.ggxiaozhi.review.class3.ArrayStack;
import com.ggxiaozhi.review.class3.Stack;

public class Main {

    public static void main(String[] args) {
        /**
         * LeetCode 20 括号匹配
         */

        isVaild("(])");

    }

    public static boolean isVaild(String s) {
        Stack<Character> stack = new ArrayStack<>();

        if (s.length() == 0) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {

                if (stack.isEmpty())
                    return false;
                Character peek = stack.pop();
                if (c == '}' && peek != '{') {
                    return false;
                } else if (c == ']' && peek != '[') {
                    return false;
                } else if (c == ')' && peek != '(') {
                    return false;
                }
            }
        }


        return stack.isEmpty();
    }


    public static boolean isValid(String s) {
        char[] chars = new char[s.length()];
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '}' && s.charAt(i) != ']' && s.charAt(i) != ')') {
                chars[index++] = s.charAt(i);
            } else if (index == 0) {//
                return false;
            } else if (s.charAt(i) == '}' && chars[index - 1] == '{') {
                index--;
            } else if (s.charAt(i) == ']' && chars[index - 1] == '[') {
                index--;
            } else if (s.charAt(i) == ')' && chars[index - 1] == '(') {
                index--;
            } else {
                return false;
            }
        }
        return index == 0;
    }
}

