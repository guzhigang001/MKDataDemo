package com.ggxiaozhi.leetcode.class8;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/4/21 22:29
 * @UpdateUser:
 * @UpdateDate: 2020/4/21 22:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Solution {
    /**
     * 17. 电话号码的字母组合
     * TODO 视频中的讲解
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 示例:
     * <p>
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * <p>
     * 说明:
     * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
     * <p>
     * 思路：
     * 可以想成是一种类似数的结构 比如 23
     * //             2
     * //         a/ b| \c
     * //        3    3   3
     * //     d/e|\f
     * //    ad ae af bd be bf cd ce cf
     * //
     * 1. 针对数字 去letterMap中找到对应的字符串
     * 2. 取出字符串中的每个字符
     * 3. 将每个字符拼接到已经拼接好的字符中
     * 4. index+1看下一个字符
     */
    private static final String[] letterMap = {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {

        if (digits.isEmpty())
            return res;
        findLetter(digits, 0, "");
        return res;
    }

    /**
     * 针对给定字符 我们要组合的所有路径
     *
     * @param digits 问题中的字符串
     * @param index  我们要处理digits的下标对应的字符
     * @param s      这条树中经过路径的之前已经拼接好的字符
     */
    private void findLetter(String digits, int index, String s) {
        //递归终止条件 index已经达到digits长度 到这里说明一个路径已经走到头了 那么我们需要将结果加入到res中
        if (digits.length() == index) {
            res.add(s);
            return;
        }
        //取出要遍历到了字符串的哪个元素
        char c = digits.charAt(index);
        //取出数字对应的字符串
        String letter = letterMap[c - '0'];

        for (int i = 0; i < letter.length(); i++) {
            //取出每个字符串的字符 然后和已经拼接好的字符串s进行拼接 同时index要+1
            findLetter(digits, index + 1, s + letter.charAt(i));
        }
    }

    /**
     * 131. 分割回文串
     * <p>
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     * <p>
     * 返回 s 所有可能的分割方案。
     * <p>
     * 示例:
     * <p>
     * 输入: "aab"
     * 输出:
     * [
     * ["aa","b"],
     * ["a","a","b"]
     * ]
     * <p>
     * https://leetcode-cn.com/problems/palindrome-partitioning/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-7/
     * <p>
     * //TODO 黄色笔记本上 这个可以参考上面 思路在本子上 回溯法 不太好理解
     */
    public List<List<String>> partition(String s) {

        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0)
            return res;

        cutMatch(s, 0, len, res);

        return res;

    }

    /**
     * 思路就是依次切割判断 已经切割的是否是回文串
     * 如果是 那么继续向下切割 如果不是 跳过
     * 从start位置开始切割 如果左边的是回文串 那么继续向下切割
     * 如果不是 跳过 继续下一个切割 知道 切割s的最后一个 也就是start==len
     *
     * @param s     要访问的字符串
     * @param start 将要访问s中的开始下标
     * @param len   s的长度 固定不变
     * @param res   最后返回的结果
     */
    private void cutMatch(String s, int start, int len, List<List<String>> res) {

        //如果已经切割到最后一个字符串了 这里是如果到这里就说明找到了一条答案
        //如果不是当我们遍历的时候 如果不符合回文串的定义 我们直接越过
        if (start == len) {


            List<String> list = new ArrayList<>();
            list.add(s);
            return;
        }

        for (int i = 0; i < len; i++) {

        }

    }

    public boolean isPalindrome() {

        return false;
    }
}
