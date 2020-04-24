package com.ggxiaozhi.leetcode.class9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/4/23 23:17
 * @UpdateUser:
 * @UpdateDate: 2020/4/23 23:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * <p>
 * 动态规划
 * <p>
 * //TODO 动态规划
 * 将原问题拆解成若干子问题 同时保存子问题的答案 使得每个子问题只求解一次 最终获得原问题的答案
 * 因为存在重叠子问题
 * 动态规划往往子递归问题------------------> 记忆化搜索(自顶向下的解决问题)/动态规划(自顶向上的解决问题)
 */
@SuppressWarnings("ConstantConditions")
public class Solution {

    /**
     * 动态规划
     * 自底向上
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
     * <p>
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


    /**
     * 70. 爬楼梯
     * <p>
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。
     * <p>
     * 示例 1：
     * <p>
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * <p>
     * 示例 2：
     * <p>
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     */
    public int climbStairs(int n) {

        return climWays(n);
    }

    private int climWays(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climWays(n - 1) + climWays(n - 2);
    }

    /**
     * 70. 爬楼梯 记忆化搜索
     */
    int[] climbs;

    public int climbStairs2(int n) {
        climbs = new int[n + 1];
        Arrays.fill(climbs, -1);
        return climWays2(n);
    }

    private int climWays2(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        if (climbs[n] == -1) {
            climbs[n] = climWays2(n - 1) + climWays2(n - 2);
        }

        return climbs[n];
    }


    /**
     * 70. 爬楼梯 非递归实现
     */
    public int climbStairs3(int n) {

        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr[n];
    }


    /**
     * 120. 三角形最小路径和
     * <p>
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     * <p>
     * 例如，给定三角形：
     * <p>
     * [
     * [2],
     * [3,4],
     * [6,5,7],
     * [4,1,8,3]
     * ]
     * <p>
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     * <p>
     * 说明：
     * <p>
     * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
     */
    static List<List<Integer>> triangle;
    static int size;
    //将所有可能存入p最小优先队列 添加元素为logn 取出元素为O1
    static PriorityQueue<Integer> p;

    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0)
            return 0;
        Solution.triangle = triangle;
        p = new PriorityQueue<>();
        //  表示递归的总深度
        size = triangle.size();
        minimum(0, 0);
        return p.peek();
    }

    /**
     * 从一个值出发 找到下一级的所有路径的所有和
     *
     * @param index 当前递归的深度 从0开始 当index=size时 表示已经超过深度 返回
     */
    private static void minimum(int index, int res) {
        if (index == size) {
            //找到一个路径
            p.add(res);
            return;
        }

        List<Integer> list = triangle.get(index);
        for (int i = 0; i < list.size(); i++) {

            res = res + list.get(i);
            minimum(index + 1, res);
            res = res - list.get(i);
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> p = new PriorityQueue<>();
        p.add(5);
        p.add(2);
        p.add(10);
        p.add(1);
        p.add(3);
        System.out.println(p.peek());

        int[][] arr = {
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}
        };

        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int[] ints = arr[i];
            List<Integer> list = new ArrayList<>();
            for (int i1 = 0; i1 < ints.length; i1++) {
                list.add(ints[i1]);
                lists.add(list);
            }
        }
        System.out.println(minimumTotal(lists));
    }
}
