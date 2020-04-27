package com.ggxiaozhi.leetcode.class9;

import java.util.Arrays;

/**
 * @Description: 背包问题课程代码
 * @Author: ggxz
 * @CreateDate: 2020/4/27 22:25
 */
public class Knapsack01 {
    private static int[][] memo;

    public static int knapsack01(int[] w, int[] v, int C) {

        if (w == null || v == null || w.length != v.length)
            throw new IllegalArgumentException("Invalid w or v");

        if (C < 0)
            throw new IllegalArgumentException("C must be greater or equal to zero.");

        int n = w.length;
        if (n == 0 || C == 0)
            return 0;

        memo = new int[n][C + 1];
        return bestValue(w, v, n - 1, C);
    }

    // 用 [0...index]的物品,填充容积为c的背包的最大价值
    private static int bestValue(int[] w, int[] v, int index, int c) {

        if (c <= 0 || index < 0)
            return 0;

        if (memo[index][c] != 0)
            return memo[index][c];

        int res = bestValue(w, v, index - 1, c);
        if (c >= w[index])
            res = Math.max(res, v[index] + bestValue(w, v, index - 1, c - w[index]));

        return memo[index][c] = res;
    }

    public static void main(String[] args) {
        int[] w = {1, 2, 3};
        int[] v = {6, 10, 12};
        System.out.println(knapsack01(w, v, 3));
    }
}
