package com.ggxiaozhi.lib.class7.class7_4;

import java.util.Set;
import java.util.TreeSet;

/**
 * @Description: https://leetcode-cn.com/problems/unique-morse-code-words/
 * 单词列表words 的长度不会超过 100。
 * 每个单词 words[i]的长度范围为 [1, 12]。
 * 每个单词 words[i]只包含小写字母
 * @Author: ggxz
 * @CreateDate: 2020/3/22 22:00
 * @UpdateUser:
 * @UpdateDate: 2020/3/22 22:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class Solution {
    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};


        Set<String> set = new TreeSet<>();

        for (String word : words) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                // a=97 A=65  word.charAt(i) - 'a' 表示偏移  如果word.charAt(i)=a a-a=0  b-a=1 c-a=2 ... 题目中说了只有小写字母
                builder.append(codes[word.charAt(i) - 'a']);
            }
            set.add(builder.toString());
        }

        return set.size();
    }

    public static void main(String[] args) {

        System.out.println("wo ");

    }
}