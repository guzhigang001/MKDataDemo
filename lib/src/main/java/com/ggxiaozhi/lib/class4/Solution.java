package com.ggxiaozhi.lib.class4;


import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.Node;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public class ListNode {
        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        @Override
        public String toString() {


            return String.valueOf(val);
        }
    }

    public ListNode createListNode(List<Integer> data) {
        if (data.isEmpty()) {
            return null;
        }
        ListNode head = new ListNode(data.get(0));
        ListNode secondNode = createListNode(data.subList(1, data.size()));
        head.next = secondNode;
        return head;

    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);

        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public ListNode reverseNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseNode(head.next);

        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            list.add(i);
        }

        Solution solution = new Solution();
        ListNode listNode = solution.createListNode(list);
        printListNode(listNode);

        printListNode(solution.reverseNode(listNode));
    }


    private static void printListNode(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
