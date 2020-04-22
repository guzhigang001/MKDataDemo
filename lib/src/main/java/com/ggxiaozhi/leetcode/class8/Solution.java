package com.ggxiaozhi.leetcode.class8;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by ggxz
 * 2020/4/22
 * description:
 */
public class Solution {

    /**
     * 93. 复原IP地址
     * <p>
     * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
     * <p>
     * 示例:
     * <p>
     * 输入: "25525511135"
     * 输出: ["255.255.11.135", "255.255.111.35"]
     * <p>
     * 思路：
     * 回溯+DFS
     * 这里的夜店是 有效IP有几点要求：
     * 1. IP分4段 每段用.分隔
     * 2. 每段的数字开头不能是0 但是可以只是0 如 192.168.0.1 可以 192.168.0.01不可以
     * 3. 每段不能超过255
     * <p>
     * 逻辑：
     * 1. 首先 ip地址4个位置 那么这个遍历的深度就是4，当达到第五层的时候如果s不为空 也就是还剩字符 那么不符合
     * 2. 其次要满足上面的3个要求
     * 3. s是层级递减的
     */
    public static List<String> restoreIpAddresses(String s) {

        List<String> res = new ArrayList<>();
        DFS(s, 0, 0, s.length(), "", res);
        return res;
    }

    /**
     * @param s     原始字符串 "25525511135" 在递归中不会变的
     * @param start 记录当先取到了那个位置，每次从下层从start+1的位置开始找一个坑位的数值
     * @param len   s的长度 不会变
     * @param temp  每次经过的树的路径的存储
     * @param level 遍历到了那一层
     */
    private static void DFS(String s, int level, int start, int len, String temp, List<String> res) {

        if (start == len || level == 4) {
            if (start == len && level == 4) {
                if (temp.endsWith(".")) //去掉最后以为小数点
                    temp = temp.substring(0, temp.length() - 1);
                res.add(temp);
            }
            return;
        }

        //每个位置最多三位数 同时要小于等于255 1  2  3位 循环时这个意思
        for (int i = 1; i <= 3; i++) {
            //start + i 这个可能越界 但是也会有疑问 我们已经在最后判断了 终止条件 为什么 这里会越界？
            // 其实终止条件时表示递归的结束 说明我们我递归到底了 返回上层调用处 也就是for循环中 这里我依然可以 i++ 那么就可能导致越界
            if (start + i > len)
                continue;
            //表示 这一层 这一个坑位要放的数字时p
            String p = s.substring(start, start + i);
            int num = Integer.valueOf(p);
            if (num <= 255) {//首先 一个位置的数值要小于255
                if (p.equals("0") || !p.startsWith("0")) {//这个位置要么时0 要么不是以0开头的
                    //都满足 我们查找下一个坑位 并将这个位置的数值添加上
                    //这里主要 要是这时最后一位切合法 记得添加res前要取出小数点
                    DFS(s, level + 1, start + i, len, temp + p + ".", res);
                }
            }
        }
    }


    /**
     * 77. 组合
     * <p>
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     * <p>
     * 示例:
     * <p>
     * 输入: n = 4, k = 2
     * 输出:
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     */
    public static List<List<Integer>> combine(int n, int k) {
        if (n == 0)
            return new ArrayList<>();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> temp = new ArrayList<>();
        DFS(arr, 1, n, k, res, temp);

        return res;
    }

    private static void DFS(int[] arr, int start, int n, int k, List<List<Integer>> res, List<Integer> temp) {

        if (temp.size() == k) {
            List<Integer> list = new ArrayList<>(temp);
            res.add(list);
            return;
        }

        //一个典型的 DFS回溯算法优化 根据这个逻辑 当我们的n-start
        for (int i = start; i <= n; i++) {

            int p = arr[i - 1];
            temp.add(p);
            DFS(arr, i + 1, n, k, res, temp);
            temp.remove(temp.size() - 1);
        }
    }
    public static List<List<Integer>> combine2(int n, int k) {
        if (n == 0)
            return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> temp = new ArrayList<>();
        DFS2( 1, n, k, res, temp);

        return res;
    }
    //num代替数组arr
    private static void DFS2( int start, int n, int k, List<List<Integer>> res, List<Integer> temp ){

        if (temp.size() == k) {
            List<Integer> list = new ArrayList<>(temp);
            res.add(list);
            return;
        }

        //一个典型的 DFS回溯算法优化
        // TODO 这个问题还是不好理解 要多练习看看别人是怎么用的  这个就是剪枝
        // 还有k - c.size()个空位, 所以, [i...n] 中至少要有 k - c.size() 个元素
        // i最多为 n - (k - c.size()) + 1
        for (int i = start; i <= n-(k-temp.size())+1; i++) {

            temp.add(i);
            DFS2( i + 1, n, k, res, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {

//        String s = "25525511135";
//
//        System.out.println(s.substring(0, 2));
//        System.out.println(restoreIpAddresses(s));
//        int[] arr = {1, 2, 3};
//        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, arr.length, arr.length)));

        System.out.println(combine(4, 2));

        int[][] arr = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(arr.length);
        System.out.println(arr[0].length);
    }
}
