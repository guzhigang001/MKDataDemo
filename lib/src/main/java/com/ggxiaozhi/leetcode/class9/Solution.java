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
            //乘法 不加最后的1也没问题
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
     * 以[10][0]点出发 到 [m-1][n-1] 共有多少条路径
     */
    private static int findPaths(int x, int y, int[] arr) {
        //已经到了边界 边界到达终点为1
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
            upiqueArr[i][0] = 1;
        }
        for (int i = 0; i < m; i++) {
            upiqueArr[0][i] = 1;
        }


        for (int i = 1; i < upiqueArr.length; i++) {
            for (int j = 1; j < upiqueArr[i].length; j++) {

                upiqueArr[i][j] = upiqueArr[i - 1][j] + upiqueArr[i][j - 1];
            }
        }
        return upiqueArr[n - 1][m - 1];
    }

    /**
     * //TODO 状态定义和状态转移
     * <p>
     * 198. 打家劫舍
     * <p>
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * <p>
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3,1]
     * 输出: 4
     * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     * 偷窃到的最高金额 = 1 + 3 = 4 。
     * <p>
     * 示例 2:
     * <p>
     * 输入: [2,7,9,3,1]
     * 输出: 12
     * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
     */
    static int[] robArr;

    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        robArr = new int[nums.length];
        Arrays.fill(robArr, -1);
        return findRob(0, nums, nums.length);
    }

    /**
     * 找到以index为起始点 返回nums中最大的和
     *
     * @param index
     * @param nums
     * @param len
     * @return
     */
    private static int findRob(int index, int[] nums, int len) {
        if (index == len - 1) {
            return nums[index];
        }

        int res = 0;

        for (int i = index; i < len; i++) {
            res = Math.max(res, nums[i] + findRob(i + 2, nums, len));
        }
        return res;
    }

    /**
     * 记忆化搜索
     *
     * @param index
     * @param nums
     * @param len
     * @return
     */

    private static int findRob2(int index, int[] nums, int len) {
        if (index == len - 1) {
            return nums[index];
        }

        if (index >= len) {
            return 0;
        }

        if (robArr[index] != -1) {
            return robArr[index];
        }
        int res = 0;
        for (int i = index; i < len; i++) {
            res = Math.max(res, nums[i] + findRob2(i + 2, nums, len));
        }
        robArr[index] = res;
        return res;
    }

    /**
     * 动态规划
     * <p>
     * TODO  利用状态转移
     * 状态转移就是将问题转化成方程的形式，然后我们根据方程写出我们的程序、
     * 在动态规划中我们要先确定最小问题是什么  然后通过最小子问题一点一点求
     * 除最终的问题
     * 本题的思路是求[i...n-1]中最大的值 每次不能取相邻的两个值
     * 那么本体的状态转移方程：f(x)表示我们函数 v(x)表示数组中的值
     * f(0)=max{v(0)+f(2),v(1)+f(3),v(2)+f(4)...,v(n-3)+f(n-1),v(n-2),v(n-1)}
     * 其中v(n-2),v(n-1)表示最后两个数字 我们去完后无法再取到后面的值
     */

    static int[] memoRob;

    //思路是求[i...n-1]中最大的值 每次不能取相邻的两个值
    public static int rob2(int[] nums) {

        int len = nums.length;
        if (len == 0)
            return 0;
        if (len == 1)
            return nums[0];

        //1 创建结果数组
        memoRob = new int[len];
        //2 确定最小子问题 根据子问题求解
        memoRob[len - 1] = nums[len - 1];
        //这个循环表示 我们已经找到了n-1的子问题解  我们根据这个解 从右向左 一次递归
        //一致到i=0 那么memoRob[0]就是我们要求的解
        //TODO memoRob[i] 从i到n-1找到最到的值 这里我们可能不是从i开始 但是memoRob[i]存的是 [i...n-1]中最大的值
        //     这个过程就是 从思路所说求[i...n-1]中最大的值 每次不能取相邻的两个值
        for (int i = len - 2; i >= 0; i--) {

            //j从i开始 也就是不相邻的要求的体现
            for (int j = i; j < len; j++) {

                // memoRob[i]是表示从i开始 通过j的循环找到i以后最大的和的值
                //这个值的 memoRob[i]可能求过 也可能没求过 这里的作用是优化重复子问题
                // nums[j] +  memoRob[j + 2]表示  nums[j]这个值开始加上下个值得最大和 也就是 memoRob[j + 2]来得到
                // memoRob[i]
                memoRob[i] = Math.max(memoRob[i], nums[j] + (j + 2 < len ? memoRob[j + 2] : 0));
            }
        }
        //最后 memoRob[0]的 位置 存的就是最终的解
        return memoRob[0];
    }

    /**
     * 有一个背包，它的容量为C (Capacity)，。 现在有n种不同的物品，编号为0...n-1，其中每一件物品的重量为w(i)，价值为v(i)。
     * 问可以向这个背包中盛放哪些物品，使得在不超过背包容量的基础上，物品的总价值最大。
     * <p>
     * <p>
     * 思路：
     * F(n, C)考虑将n个物品放进容量为C的背包，使得价值最大
     * <p>
     * 我们假设从0...n-1中 不停的通过函数F(i,c)求得价值最大 i表示背包里的物品个数  c表示背包 剩余容量
     * 那么当一个新物品 也就是i++的时候 我们有两个选择：
     * 1. 不放新的元素 也就是原函数不变 由于i++了 所以原函数为① F(i-1 ,c)
     * 2. 放新元素② 那么现在背包的价值等于=新加物品的价值v(i)+ 原来物品的价值F(i-1 ,C-W(i) )   C-W(i)表示新加了元素 那么背包的容量现在等于=原来的重量C=C-新的物品的重量w(i)
     * <p>
     * F(i,c) = F(i-1 ,c)  ①
     * = v(i)+F(i-1 ,C-W(i) )  ②
     * <p>
     * 3. 最后我们通过上面的分析 我们知道我们是否添加进背包的取决于这两个选择错产生的价值那个更大 所以通过max函数对比选择
     * F(i,c) = max(F(i-1 ,C),v() +F(i-1 ,C- w(i) )
     */

    /**
     * @param w 表示有w个物品 数组中从0...w.length-1 每个元素为这个物品的重量
     * @param v v与w是对应的长度必须一直 否则无意义 v中0...v.length-1对应0...w.length-1中物品的价值
     * @param C 背包的容量 是指能承受w中多少个物品对应的重量
     * @return C容量能装下的最大价值
     */
    public static int knapsack01(int[] w, int[] v, int C) {
        int lenW = w.length, lenV = v.length;

        if (lenV == 0 || lenV != lenW)
            return 0;

        if (C == 0)
            return 0;

        return bestValue(w, v, lenW - 1, C);
    }

    /**
     * 用 [index...len]的物品,填充容积为c的背包的最大价值 注意index不一定一定会遍历到0
     * 因为可能我们添加的第一元素重量正好填满我们容量C 那么
     *
     * @param w
     * @param v
     * @param index 初始值为len-1 表示 从后往前 是否放入最后一个物品 使得此方法返回的值 为最大的价值
     * @param c
     * @return 返回c容量下 放入
     */
    private static int bestValue(int[] w, int[] v, int index, int c) {
        //当容量满的时候 递归终止
        //index表示下标我们传入的是len-1 保证合法性 此时如果index<0 那么直接返回
        if (c <= 0 || index < 0) {
            return 0;
        }

        //index元素不添加直接添加下一个元素 一直这样一直遍历 知道遍历到index=0
        int res = bestValue(w, v, index - 1, c);
        if (c >= w[index]) {//添加index元素 然后继续和下一个元素比较去最大值
            // v[index]添加入背包  bestValue(w, v, index - 1, c - w[index])看下一个元素 他们的总和  在与res比较
            res = Math.max(res, v[index] + bestValue(w, v, index - 1, c - w[index]));
        }
        return res;
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

//        int[] nums = {2, 7, 9, 3, 1};
//        System.out.println(rob2(nums));
        int[] w = {1, 2, 3};
        int[] v = {6, 10, 12};
        System.out.println(knapsack01(w, v, 3));
    }
}
