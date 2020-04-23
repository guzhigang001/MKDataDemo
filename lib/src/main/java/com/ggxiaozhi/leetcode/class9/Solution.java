package com.ggxiaozhi.leetcode.class9;

import java.util.Arrays;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/4/23 23:17
 * @UpdateUser:
 * @UpdateDate: 2020/4/23 23:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 *
 * 动态规划
 *
 * //TODO 动态规划
 *        将原问题拆解成若干子问题 同时保存子问题的答案 使得每个子问题只求解一次 最终获得原问题的答案
 *                             因为存在重叠子问题
 *        动态规划往往子递归问题------------------> 记忆化搜索(自顶向下的解决问题)/动态规划(自顶向上的解决问题)
 */
public class Solution {

    /**
     * 动态规划
     *  自底向上
     */
    public static int fib(int n) {

        int[] arr = new int[101];

        arr[0] = 0;
        arr[1] = 1;

        for (int i = 2; i <= 100; i++) {//不用递归该循环 先把 0<=n<=100 所有的斐波那契数列 和n对应起来然后直接去数组中查找
            arr[i] = (arr[i - 1] + arr[i - 2]) % 1000000007;
        }

        return arr[n];
    }


    /**
     * 记忆化搜索
     *
     * 自定向下 O(n) 具体是O(2n-1)
     */
    private static int[] arr = new int[101];

    static {
        Arrays.fill(arr, -1);

    }

    public static int fib2(int n) {

        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        if (arr[n] == -1) {
            arr[n] = fib(n - 1) + fib(n - 2);
        }


        return arr[n];
    }

    public static void main(String[] args) {
        System.out.println(fib2(100));
    }
}
