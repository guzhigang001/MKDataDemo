package com.ggxiaozhi.libreview.class4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

    /**
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {

            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                Integer pop = stack.pop();
                map.put(pop, nums2[i]);
            }
            stack.push(nums2[i]);
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();

            map.put(pop, -1);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {

            res[i] = map.get(nums1[i]);

        }

        return res;
    }

    public static void main(String[] args) {

        int[] nums1 = {4,1,2}, nums2 = {1,3,4,2};
        String string = Arrays.toString(nextGreaterElement(nums1, nums2));
        System.out.println(string);
    }
}
