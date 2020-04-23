package com.ggxiaozhi.leetcode.class8;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Create by ggxz
 * 2020/4/22
 * description:
 * <p>
 * //TODO 8-6 8-7 8-8 介绍 经典的和回溯相关的问题 包括 树形问题 排列问题 组合问题 DFS floodfill 二位平面回溯法 N Queens
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

    static List<String> res = new ArrayList<>();

    public static List<String> letterCombinations(String digits) {

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
    private static void findLetter(String digits, int index, String s) {
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
            String nextS = s + letter.charAt(i);
            findLetter(digits, index + 1, nextS);
            nextS.substring(0, nextS.length() - 1);
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
    public static List<List<String>> partition(String s) {

        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0)
            return res;

        List<String> temp = new ArrayList<>();
        cutMatch(s, 0, len, res, temp);

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
    private static void cutMatch(String s, int start, int len,
                                 List<List<String>> res, List<String> temp) {

        //如果已经切割到最后一个字符串了 这里是如果到这里就说明找到了一条答案
        //如果不是当我们遍历的时候 如果不符合回文串的定义 我们直接越过
        if (start == len) {
            //这里要新创建一个 以为res存的是temp的引用 那么最后 temp是会回溯删除的
            //那么也就是 最后res会为null 所以我们要创建一个新的List
            //TODO 这也就解释了 为什么我们再list中添加了一个元素 后又要删除 如果不删除我们的list只创建了一个
            // 那么这个list会存入错乱 最后的结果会特别长
            List<String> list = new ArrayList<>(temp);
            res.add(list);
            return;
        }


        //从start开始向下树形的下面寻找 相当于越过 start之前的位置
        for (int i = start; i < len; i++) {

            //依次切割aab:  a->a,ab->aa,b->aab
            //substring[start,end)
            String p = s.substring(start, i + 1);
            //左边的不是回文串 右边也就不用判断了
            if (!isPalindrome(p)) {
                continue;
            }
            //如果是回文串
            //1.先添加入 temp集合中
            temp.add(p);
            cutMatch(s, i + 1, len, res, temp);
            //删除上边添加的 回溯还原 temp和他的同级相同在向下面寻找
            temp.remove(temp.size() - 1);
        }

    }

    public static boolean isPalindrome(String p) {
        int start = 0;
        int len = p.length() - 1;
        while (start < len) {
            if (p.charAt(start) != p.charAt(len)) {
                return false;
            }
            start++;
            len--;
        }

        return true;
    }

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
        DFS2(1, n, k, res, temp);

        return res;
    }

    //num代替数组arr
    private static void DFS2(int start, int n, int k, List<List<Integer>> res, List<Integer> temp) {

        if (temp.size() == k) {
            List<Integer> list = new ArrayList<>(temp);
            res.add(list);
            return;
        }

        //一个典型的 DFS回溯算法优化
        // TODO 这个问题还是不好理解 要多练习看看别人是怎么用的  这个就是剪枝
        // 还有k - c.size()个空位, 所以, [i...n] 中至少要有 k - c.size() 个元素
        // i最多为 n - (k - c.size()) + 1
        for (int i = start; i <= n - (k - temp.size()) + 1; i++) {

            temp.add(i);
            DFS2(i + 1, n, k, res, temp);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * 79. 单词搜索
     * <p>
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * <p>
     * <p>
     * <p>
     * 示例:
     * <p>
     * board =
     * [
     * ['A','B','C','E'],
     * ['S','F','C','S'],
     * ['A','D','E','E']
     * ]
     * <p>
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     * <p>
     * <p>
     * <p>
     * 提示：
     * <p>
     * board 和 word 中只包含大写和小写英文字母。
     * 1 <= board.length <= 200
     * 1 <= board[i].length <= 200
     * 1 <= word.length <= 10^3
     * <p>
     * 思路：
     * 将二位数组看成一个坐标系 然后 从[0][0]开始找word的第一个字母，一直找到结束，找到后从第一个字母开始
     * 然后根据上右下左的 顺时针顺序 依次递归移动
     * 找到下一个位置 再按照顺时针顺序 依次类推
     * 最后我们走过的路径需要标记 以为我们只查找我们没有查找过的路径坐标
     */
    //direction 方向  这里是上右下左 顺时针
    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    //m是行数  n是列数
    static int m, n;

    //判断下标是否合法
    public static boolean isLegal(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static boolean exist(char[][] board, String word) {
        if (board.length == 0)
            return false;
        if (word.isEmpty())
            return true;
        m = board.length;
        n = board[0].length;

        //记录当前的点我们是否访问过
        //数据初始值是false 数据个数和board一样
        boolean[][] visit = new boolean[m][n];

        //双层循环  遍历每一个位置的点 看这个位置是否是满足word的第一个字母
        //因为我们只有找到了第一个字母 后面的查找才是有意义的
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (searchWord(board, word, 0, i, j, visit)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean searchWord(char[][] board, String word, int index, int x, int y, boolean[][] visit) {
        //终止条件 当我们编译到 word的最后一个元素
        //这时候说明 之前的word.length() - 1之前的元素都找到了
        //那么我们只需要判断最后一个元素 和我们找到元素是否相等
        if (index == word.length() - 1) {
            return board[x][y] == word.charAt(index);
        }


        char c = board[x][y];
        //首先判断传来的坐标 是否是我们要寻找的
        if (word.charAt(index) == c && !visit[x][y]) {
            //找到了一个匹配的元素 访问这个元素 并设置标识位
            visit[x][y] = true;
            //现在表示 已经找到了 上一个元素
            //我们这里要做的就是 以  board[x][y] 为中心 顺时针寻找4个方向 看是否能找到 下一个匹配的元素
            for (int i = 0; i < 4; i++) {

                //获取 下一个寻找到的方向的左边
                int newX = x + d[i][0];
                int newY = y + d[i][1];

                //如果我们找到的坐标是合法的 同时 没有被访问过 那么我们取访问他
                if (isLegal(newX, newY) && !visit[newX][newY]) {
                    //我们要判断 这个位置是否符合要求 也就是 能否和word对应的字母匹配上 继续递归抵用
                    if (searchWord(board, word, index + 1, newX, newY, visit)) {
                        return true;

                    }
                }
            }
            //回溯标识位
            visit[x][y] = false;
        }


        return false;
    }

    /**
     * 200. 岛屿数量
     * <p>
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * <p>
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * <p>
     * 此外，你可以假设该网格的四条边均被水包围。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * 11110
     * 11010
     * 11000
     * 00000
     * 输出: 1
     * <p>
     * 示例 2:
     * <p>
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     * 输出: 3
     * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
     * <p>
     * 思路：
     * 这道题和79号题得思路基本一致
     * 主要还是利用回溯思想
     * 这里不同得是 当我们遍历的回溯回去的时候  我们是不需要还原数据的
     * 因为我们寻找陆地 陆地本身存在 我们找到后直接回溯就好了同下次再找的时候我们就不用看它了
     */


    public int numIslands(char[][] grid) {

        if (grid.length == 0)
            return 0;
        if (grid[0].length == 0)
            return 0;
        int res = 0;

        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visit = new boolean[m][n];


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //是陆地 同时没有访问过
                //起始位置遍历 遍历到一个陆地后 通过findLands方法 将传入的以i，j为起始整个陆地
                //并且标记 下次已经被visit标记的陆地不再访问 知道找到新的陆地res++
                if (grid[i][j] == '1' && !visit[i][j]) {
                    res++;
                    //TODO 其实也是DFS
                    findLands(grid, i, j, visit);
                }
            }
        }
        return res;
    }

    /**
     * //TODO 这里的DFS 有2个特点：
     *        1. 没有递归终止条件         这样会一直递归到底 但是我们循环的if语句中有判断条件 ，这就相当于剪枝 其实也可以看成递归条件 融合进判断条件中
     *        2. 回溯时我们没有重置数据    这是因为 这道题和79有些不一样 79是找一个路径 找到后保存避免同级搜索 状态不一致，但是这个题是区域，不是单独的一个路径
     *           也就是说我们
     */
    // 从grid[x][y]的位置开始,进行floodfill
    // 保证(x,y)合法,且grid[x][y]是没有被访问过的陆地
    private void findLands(char[][] grid, int x, int y, boolean[][] visit) {
        //首先确定了grid[x][y]是陆地且我们现在访问他 那么 设置标识
        visit[x][y] = true;
        //以grid[x][y]为中心向四个方向查看是否有陆地
        for (int i = 0; i < 4; i++) {
            //其中一个方向的坐标
            int newX = x + d[i][0];
            int newY = x + d[i][1];

            //按照顺序满足三个条件
            //1 首先newX newY是合法的
            //2 没有访问过
            //3 必须是陆地
            //那么我们找到了一个符合要求的陆地
            if (isLegal(newX, newY) && !visit[newX][newY] && grid[newX][newY] == '1') {
                findLands(grid, newX, newY, visit);
            }
        }
    }

    public boolean isLegal(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    /**
     * 51. N皇后
     * <p>
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * <p>
     * 上图为 8 皇后问题的一种解法。
     * <p>
     * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
     * <p>
     * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     * <p>
     * 示例:
     * <p>
     * 输入: 4
     * 输出: [
     * [".Q..",  // 解法 1
     * "...Q",
     * "Q...",
     * "..Q."],
     * <p>
     * ["..Q.",  // 解法 2
     * "Q...",
     * "...Q",
     * ".Q.."]
     * ]
     * 解释: 4 皇后问题存在两个不同的解法。
     */

    //表示列(竖直方向)上是否满足 一列 只有一个皇后
    public boolean[] col;//column
    /**
     * 对角线的逻辑就是   一条对角线上 对应dia数组中的一个Boolean值
     * 因为当这条对角线上 有一个值了直接为true 表示这条对角线就不能发其他的皇后了
     * <p>
     * 对角线的个数为2n-1
     */
    //对角线1(左下-右上) :每个对角线的点相加都相等  x+y
    public boolean[] dia1;//diagonal
    // 对角线2(右下-左上) :每个对角线的点相减都相等  x-y
    // 但是这里是负值 但是我们的dia2的下标是从0开始的
    // 那我们需要偏移 x-y+n-1 也就是偏移n-1个
    public boolean[] dia2;//diagonal

    //最后返回的结果
    List<List<String>> resQ = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        if (n <= 0)
            return new ArrayList<>();
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];

        //这个集合有个特点 集合的下标表示在第几行 和 下面的index对应
        //值对应皇后存在这行中第几列 比如 row.get(1)=3 表示[1][2] 第二行 第三列(下标从0开始) 有一个皇后
        LinkedList<Integer> row = new LinkedList<>();

        putQueens(n, 0, row);


        return resQ;
    }

    /**
     * 在指定行(index)中 寻找哪列放入皇后 其实也是树形搜索 DFS结构
     *
     * @param n     表示 几皇后
     * @param index 表示现在我们在第几行寻找放皇后的位置
     * @param row   存入结果
     */
    public void putQueens(int n, int index, LinkedList<Integer> row) {

        //在4皇后的情况下 n=4 index从0开始 当index=4时 一定时结束了
        if (index == n) {
            List<Integer> list = new ArrayList<>(row);
            resQ.add(generateBoard(n, list));
            return;
        }

        //在当前行 index 寻找合适的放置位置
        for (int i = 0; i < n; i++) {


            //!col[i]表示i对应的列 没有放置
            //!dia1[index + i] 左下-右上 i所对应的对角线 没有放置
            //!dia2[index - i + n - 1] 右下-坐上 i所对应的对角线 没有放置
            //符合条件 同时满足本次循环[index,i] 这个位置可以放置皇后
            if (!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                //设置标识
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                row.addLast(i);
                putQueens(n, index + 1, row);
                //还原回溯表示 避免干扰下次查找时得影响
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
                row.removeLast();
            }
        }

    }

    private List<String> generateBoard(int n, List<Integer> row) {

        List<String> newRow = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            int index = row.get(i);
            chars[index] = 'Q';
            newRow.add(new String(chars));
        }
        return newRow;
    }


    public static void main(String[] args) {

//        String s = "25525511135";
//
//        System.out.println(s.substring(0, 2));
//        System.out.println(restoreIpAddresses(s));
//        int[] arr = {1, 2, 3};
//        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, arr.length, arr.length)));


        System.out.println(combine(4, 2));

        char[][] arr = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        System.out.println(exist(arr, ""));
    }
}
