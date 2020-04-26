package com.ggxiaozhi.leetcode.class9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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


    /**
     * 343. 整数拆分
     * <p>
     * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     * <p>
     * 示例 2:
     * <p>
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
     * <p>
     * 说明: 你可以假设 n 不小于 2 且不大于 58。
     */
    static int[] findArr;

    public static int integerBreak(int n) {

        if (n == 2) {
            return 1;
        }
        findArr = new int[n + 1];
        Arrays.fill(findArr, -1);
        return findInteger(n);
    }

    /**
     * 将n进行分割(至少分割成两部分) 可以获得他们的最大乘机
     *
     * @param n 每次n得规模
     */

    private static int findInteger(int n) {
        if (n == 1) {//当n==1时 不能再分割了
//            temp.add(1);  //乘法 不加最后的1也没问题
            return 1;
        }

        if (findArr[n] != -1) {
            return findArr[n];
        }
        //res 不能放在方法外面
        int res = 0;
        for (int i = 1; i < n; i++) {
            int max = Math.max(i * (n - i), i * findInteger(n - i));
            res = Math.max(res, max);
        }
        findArr[n] = res;
        return res;
    }

    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    public int integerBreak2(int n) {

        if (n < 1)
            throw new IllegalArgumentException("n should be greater than zero");

        int[] memo = new int[n + 1];
        memo[1] = 1;
        for (int i = 2; i <= n; i++)
            // 求解memo[i] n进行分割(至少分割成两部分) 可以获得他们的最大乘机
            for (int j = 1; j <= i - 1; j++)
                memo[i] = max3(memo[i], j * (i - j), j * memo[i - j]);

        return memo[n];
    }

    private int max3(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    /**
     * 62. 不同路径
     * <p>
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     * <p>
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     * <p>
     * 问总共有多少条不同的路径？
     * <p>
     * 例如，上图是一个7 x 3 的网格。有多少可能的路径？
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: m = 3, n = 2
     * 输出: 3
     * 解释:
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向右 -> 向下
     * 2. 向右 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向右
     * <p>
     * 示例 2:
     * <p>
     * 输入: m = 7, n = 3
     * 输出: 28
     * <p>
     * 思路：
     * 创建一个二维的数组
     *
     * @param m
     * @param n
     * @return
     */
    //路径上的点[x][y]
    static int[][] upiqueArr;
    //[x][y]这个点对应有多少条路径

    static int m, n;

    //记忆化搜索
    public static int uniquePaths(int m, int n) {

        if (m == 1)
            return 1;
        Solution.m = m;
        Solution.n = n;
        upiqueArr = new int[n][m];

        int num = 0;
        for (int i = 0; i < upiqueArr.length; i++) {
            for (int j = 0; j < upiqueArr[i].length; j++) {
                upiqueArr[i][j] = num++;
            }
        }
        int[] arr = new int[m * n];
        Arrays.fill(arr, -1);


        return findPaths(0, 0, arr);
    }

    /**
     * 以[1][1]点出发 到 [m][n] 共有多少条路径
     */
    private static int findPaths(int x, int y, int[] arr) {
        if (x == n - 1 || y == m - 1) {
            return 1;
        }

        if (arr[upiqueArr[x][y]] != -1) {
            return arr[upiqueArr[x][y]];
        }

        //总路径=向左走一步的总路径+向右走一步的总路径
        int res = findPaths(x + 1, y, arr) + findPaths(x, y + 1, arr);
        arr[upiqueArr[x][y]] = res;
        return res;
    }

    /**
     * 动态规划
     *
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths2(int m, int n) {
        if (m == 1)
            return 1;
        upiqueArr = new int[n][m];


        for (int i = 0; i < n; i++) {
            upiqueArr[i][m - 1] = 1;
        }
        for (int i = 0; i < m; i++) {
            upiqueArr[n - 1][i] = 1;
        }


        for (int i = n - 1; i > 0; i--) {
            for (int j = m - 1; j > 0; j--) {

                upiqueArr[i][j] = upiqueArr[i - 1][j] + upiqueArr[i][j - 1];
            }
        }
        return upiqueArr[0][0];
    }

    public static void main(String[] args) {
//        PriorityQueue<Integer> p = new PriorityQueue<>();
//        p.add(5);
//        p.add(2);
//        p.add(10);
//        p.add(1);
//        p.add(3);
//        System.out.println(p.peek());
//
//        int[][] arr = {
//                {2},
//                {3, 4},
//                {6, 5, 7},
//                {4, 1, 8, 3}
//        };
//
//        List<List<Integer>> lists = new ArrayList<>();
//        for (int i = 0; i < arr.length; i++) {
//            int[] ints = arr[i];
//            List<Integer> list = new ArrayList<>();
//            for (int i1 = 0; i1 < ints.length; i1++) {
//                list.add(ints[i1]);
//                lists.add(list);
//            }
//        }
//        System.out.println(minimumTotal(lists));

        System.out.println(uniquePaths2(7, 3));
    }
}
