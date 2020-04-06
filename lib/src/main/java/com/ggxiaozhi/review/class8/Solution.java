package com.ggxiaozhi.review.class8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @Description: 347
 * @Author: ggxz
 * @CreateDate: 2020/4/5 18:50
 * @UpdateUser:
 * @UpdateDate: 2020/4/5 18:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Solution {
    public static List<Integer> topKFrequent(int[] nums, int k) {

        final TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer key1, Integer key2) {
                if (map.get(key1) > map.get(key2)) return 1;
                else if (map.get(key1) < map.get(key2)) return -1;
                else return 0;
            }
        });

        for (Integer key : map.keySet()) {
            if (queue.size() >= k) {

                Integer peek = queue.peek();
                if (map.get(peek) < map.get(key)) {
                    queue.remove(peek);
                    queue.add(key);
                }
            } else {
                queue.add(key);
            }

        }

        List<Integer> list = new LinkedList<>();
        while (!queue.isEmpty()) {
            list.add(queue.remove());
        }

        return list;
    }

    public static void main(String[] args) {
        int[] nums = {5,2,5,3,5,3,1,1,3};
        System.out.println(topKFrequent(nums,2));
    }
}
