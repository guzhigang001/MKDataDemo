package com.ggxiaozhi.lib.class8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution2 {


    public List<Integer> topKFrequent(int[] nums, int k) {

        //<数组中的元素,这个元素出现的频数>
        //完成这个后 map中放的就是数组中 无重复每个数和出现的频数
        final HashMap<Integer, Integer> treeMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int keyNum = nums[i];
            if (treeMap.containsKey(keyNum)) {
                treeMap.put(keyNum, treeMap.get(keyNum) + 1);
            } else {
                treeMap.put(keyNum, 1);
            }
        }

        //java中的PriorityQueue 是用的最小堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return treeMap.get(a) - treeMap.get(b);
            }
        });

        for (Integer key : treeMap.keySet()) { //O(n)
            if (priorityQueue.size() >= k) {//说明队列中的元素已经满了 取到了k个元素
                Integer peek = priorityQueue.peek();
                if (treeMap.get(peek) < treeMap.get(key)) {// logk 所以整个算法是O(nlogk)
                    priorityQueue.remove(priorityQueue.peek());
                    priorityQueue.add(key);
                }
            } else {
                priorityQueue.add(key);
            }
        }

        LinkedList<Integer> list = new LinkedList<>();

        while (!priorityQueue.isEmpty())
            list.add(priorityQueue.remove());
        return list;
    }
}
