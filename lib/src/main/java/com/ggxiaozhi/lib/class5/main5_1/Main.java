package com.ggxiaozhi.lib.class5.main5_1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            data.add(i);
        }

        data.add(2);
        //0 1 2 3 2
//        ListNode head = createNode(data);

        int[] sums = {1, 2, 3, 4, 5};
        ListNode head = new ListNode(sums);
        ListNode deleteNode = solution.deleteNode(head, 6);
        System.out.println(deleteNode);
    }

    public static ListNode createNode(List<Integer> data) {

        if (data.isEmpty()) {
            return null;
        }
        ListNode head = new ListNode(data.get(0));
        head.next = createNode(data.subList(1, data.size()));
        return head;
    }
}
