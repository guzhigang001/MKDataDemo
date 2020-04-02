package com.ggxiaozhi.libreview.class4;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

/**
 * @Description: 496. 下一个更大元素 I
 * @Author: ggxz
 * @CreateDate: 2020/4/2 22:49
 * @UpdateUser:
 * @UpdateDate: 2020/4/2 22:49
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressWarnings("unchecked")
public class Solution {

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Stack<Integer> stackA = new Stack<>();
        Stack<Integer> stackB = new Stack<>();
        for (int i = nums1.length - 1; i >= 0; i--) {
            stackA.push(nums1[i]);
        }

        for (int i = nums2.length - 1; i >= 0; i--) {
            stackB.push(nums2[i]);
        }


        int[] ret = new int[nums1.length];
        int i = 0;
        while (!stackA.isEmpty()) {
            Integer popA = stackA.pop();
            Stack<Integer> temp = (Stack<Integer>) stackB.clone();
            while (!temp.isEmpty()) {
                Integer popT = temp.pop();
                if (popT.equals(popA)) {


                    while (!temp.isEmpty()) {
                        Integer popB = temp.pop();
                        if (popB > popA) {
                            ret[i] = popB;
                            i++;
                            break;
                        }
                    }
                    if (temp.isEmpty()) {
                        ret[i] = -1;
                        i++;
                        break;
                    }
                    break;
                }
            }
        }
        return ret;
}

    public static void main(String[] args) {

        int[] nums1 = {1, 3, 5, 2, 4}, nums2 = {6, 5, 4, 3, 2, 1, 7};
        String string = Arrays.toString(nextGreaterElement(nums1, nums2));
        System.out.println();
    }
}
