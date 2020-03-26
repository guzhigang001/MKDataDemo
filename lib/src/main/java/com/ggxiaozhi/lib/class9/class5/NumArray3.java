package com.ggxiaozhi.lib.class9.class5;

import java.util.Date;

/**
 * 307. 区域和检索 - 数组可修改
 *
 * 这个方法 比较低效率
 *
 * 树的特点就是在指定的区间(数组也好，链表也好等)
 * 区间元素的个数不变 内容变化 或是操作(求和 ，求最大值 最小值 等) 非常适合
 *
 * 都是在logN级别的
 * 相同的问题
 * @see NumArray4
 */
class NumArray3 {

    private int[] sum;

    private int[] data;

    public NumArray3(int[] nums) {

        data = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }

        sum = new int[nums.length + 1];
        sum[0] = 0;

        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    /**
     * 将第i个元素更新后 那么 sum中i+1以后的元素都要更新
     *
     * 经过main方法的测试没问题
     * @param index
     * @param val
     */
    public void update(int index, int val) {
        data[index] = val;

        for (int i = index + 1; i < sum.length; i++) {

            sum[i] = sum[i - 1] + data[i - 1];
        }

    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray3 numArray = new NumArray3(nums);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
        numArray.update(1,1);
        System.out.println();
        //int[] nums = {-2, 1, 3, -5, 2, -1};
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */