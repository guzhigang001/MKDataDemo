package com.ggxiaozhi.lib.class9.class5;

/**
 *
 * leetcode 303 https://leetcode-cn.com/problems/range-sum-query-immutable/submissions/
 * 我们这里使用一个数组 来存储 nums的和的值  sum
 * <p>
 * sum[i] 存储前i个元素的和
 * 比如 nums[] sum[0]=0 sum{0} 因为当元素为空的时候 和为0
 * num{1} sum={0,1}
 * nums = [-2, 0, 3, -5, 2, -1] sum=[0,-2,-2,1,-4,-2,-1]
 * <p>
 * sum[i] 存储num[0...i-1]
 * <p>
 * sum[0]=0
 * 这里sum[0]=0  sum的所以表示nums中2
 * <p>
 * nums = [-2, 0, 3, -5, 2, -1]
 */
class NumArray2 {

    private int[] sum;

    public NumArray2(int[] nums) {

        sum = new int[nums.length + 1];
        sum[0] = 0;

        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {

        return sum[j + 1] - sum[i];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray2 numArray = new NumArray2(nums);
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