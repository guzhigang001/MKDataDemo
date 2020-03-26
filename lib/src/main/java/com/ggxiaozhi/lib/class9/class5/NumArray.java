package com.ggxiaozhi.lib.class9.class5;

import com.ggxiaozhi.lib.class9.class1_4.Merger;
import com.ggxiaozhi.lib.class9.class1_4.SegmentTree;

/**
 * 这个问题存在一个 更优的解决方案
 * @see NumArray2
 */
public class NumArray {

    private SegmentTree<Integer> sg;

    public NumArray(int[] nums) {

        if (nums.length > 0) {
            Integer[] integers = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                integers[i] = nums[i];
            }
            sg = new SegmentTree<>(integers, new Merger<Integer>() {
                @Override
                public Integer merge(Integer a, Integer b) {
                    return a + b;
                }
            });
        }

    }

    public int sumRange(int i, int j) {

        return sg.query(i, j);
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);
        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }
}
