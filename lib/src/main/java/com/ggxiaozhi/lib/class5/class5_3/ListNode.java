package com.ggxiaozhi.lib.class5.class5_3;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int[] arr) {

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("数组不能为null");
        }
        ListNode cur = this;
        val = arr[0];

        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        ListNode head = this;
        while (head != null) {
            builder.append(head.val).append("->");
            head = head.next;
        }
        builder.append("NULL");
        return builder.toString();
    }
}
