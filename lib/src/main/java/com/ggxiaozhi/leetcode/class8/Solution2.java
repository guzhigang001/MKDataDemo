package com.ggxiaozhi.leetcode.class8;

import java.util.Arrays;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/4/23 22:13
 * @UpdateUser:
 * @UpdateDate: 2020/4/23 22:13
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Solution2 {
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
     *
     * @see Solution2
     */
    //direction 方向  这里是上右下左 顺时针
    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    //m是行数  n是列数
    static int m, n;
    char[][] grid;
    boolean[][] visit;

    public int numIslands(char[][] grid) {

        if (grid.length == 0)
            return 0;
        if (grid[0].length == 0)
            return 0;
        int res = 0;

        m = grid.length;
        n = grid[0].length;

        this.grid = grid;

        visit = new boolean[m][n];


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                //是陆地 同时没有访问过
                //起始位置遍历 遍历到一个陆地后 通过findLands方法 将传入的以i，j为起始整个陆地
                //并且标记 下次已经被visit标记的陆地不再访问 知道找到新的陆地res++
                if (!visit[i][j] && grid[i][j] == '1') {
                    res++;
                    //TODO 其实也是DFS
                    findLands(i, j);
                }
            }
        }
        return res;
    }

    /**
     * //TODO 这里的DFS 有2个特点：
     * 1. 没有递归终止条件         这样会一直递归到底 但是我们循环的if语句中有判断条件 ，这就相当于剪枝 其实也可以看成递归条件 融合进判断条件中
     * 2. 回溯时我们没有重置数据    这是因为 这道题和79有些不一样 79是找一个路径 找到后保存避免同级搜索 状态不一致，但是这个题是区域，不是单独的一个路径
     * 也就是说我们 找到了符合要求的就保存这个元素的标示
     * <p>
     * TODO 这个还是要多去练习理解 但是从题解和思想上可以看到 是不需要重置标识的
     */
    // 从grid[x][y]的位置开始,进行floodfill
    // 保证(x,y)合法,且grid[x][y]是没有被访问过的陆地
    private void findLands(int x, int y) {
        //首先确定了grid[x][y]是陆地且我们现在访问他 那么 设置标识
        visit[x][y] = true;
        //以grid[x][y]为中心向四个方向查看是否有陆地
        for (int i = 0; i < 4; i++) {
            //其中一个方向的坐标
            int newX = x + d[i][0];
            int newY = y + d[i][1];

            //按照顺序满足三个条件
            //1 首先newX newY是合法的
            //2 没有访问过
            //3 必须是陆地
            //那么我们找到了一个符合要求的陆地
            if (isLegal(newX, newY) && grid[newX][newY] == '1' && !visit[newX][newY]) {
                findLands(newX, newY);
            }
        }
        //这里我们不需要重置visit
    }

    public boolean isLegal(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }



}
