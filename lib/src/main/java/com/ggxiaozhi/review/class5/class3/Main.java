package com.ggxiaozhi.review.class5.class3;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/3/18 15:20
 * @UpdateUser:
 * @UpdateDate: 2020/3/18 15:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Main {


    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4};

        System.out.println(sum(nums, 0));
    }

    public static int sum(int[] arr, int l) {
        int n = arr.length;
        if (l == n) {
            return 0;
        }

        int sum = sum(arr, l + 1);
        int res = arr[l] + sum;
        return res;

    }
}
