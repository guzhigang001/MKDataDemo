package com.ggxiaozhi.lib.class9.class5;

import com.ggxiaozhi.lib.class9.class1_4.Merger;
import com.ggxiaozhi.lib.class9.class1_4.SegmentTree;

/**
 * 307. 区域和检索 - 数组可修改
 */
class NumArray4 {

    private SegmentTree<Integer> sg;

    public NumArray4(int[] nums) {

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

    /**
     * 将第i个元素更新后 那么 sum中i+1以后的元素都要更新
     *
     * 经过main方法的测试没问题
     * @param index
     * @param val
     */
    public void update(int index, int val) {

        sg.set(index,val);

    }



    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray4 numArray = new NumArray4(nums);
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