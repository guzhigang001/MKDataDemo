package com.ggxiaozhi.lib.class7.class7_9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

class Solution {

    public static void main(String[] args) {

        int[] nums1 = new int[]{1,2,2,1};
        int[] nums2 = new int[]{2,2};
        System.out.println(Arrays.toString(intersection3(nums1, nums2)));
    }

    /**
     * O(n*n) leetcode 349 效率低
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums1.length; i++) {
            int num1 = nums1[i];
            for (int j = 0; j < nums2.length; j++) {
                int num2 = nums2[j];
                if (num1 == num2) {
                    set.add(num2);
                }
            }
        }
        int[] ints = new int[set.size()];
        int i = 0;
        for (Integer integer : set) {
            ints[i] = integer;
            i++;
        }
        return ints;
    }

    public static int[] intersection2(int[] nums1, int[] nums2) {

        TreeSet<Integer> set = new TreeSet<>();

        /**
         * 去除nums1中重复元素
         */
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                list.add(nums2[i]);
                //找到重复的元素后 要把这个元素删除 否则 nums2中可能有重复的元素
                //那么 set不删除的话就会在list中重复添加
                set.remove(nums2[i]);
            }
        }

        int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i);
        }

        return ints;
    }

    public static int[] intersection3(int[] nums1, int[] nums2) {

        // 第一个代表集合中的元素  第二个元素代表 这个元素在集合中出现的次数
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums1.length; i++) {
            if (!map.containsKey(nums1[i])) {
                map.put(nums1[i], 1);
            } else {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            }
        }


        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num)) {
                list.add(num);
                map.put(num, map.get(num) - 1);

                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }

        int[] ints = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i);
        }
        return ints;
    }

}