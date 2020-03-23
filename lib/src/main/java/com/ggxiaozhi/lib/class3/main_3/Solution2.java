package com.ggxiaozhi.lib.class3.main_3;

import java.util.Stack;

/**
 * 844. 比较含退格的字符串
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 */
public class Solution2 {


    public boolean backspaceCompare(String S, String T) {

        return compare(S).equals(compare(T));
    }

    private Stack<Character> compare(String T) {
        Stack<Character> stackT = new Stack<>();
        for (int i = 0; i < T.length(); i++) {
            char c = T.charAt(i);
            if (c == '#') {
                if (!stackT.isEmpty()) {
                    stackT.pop();
                }
            } else {
                stackT.push(c);
            }
        }
        return stackT;
    }
}
