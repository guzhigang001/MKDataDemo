package com.ggxiaozhi.lib.class5.main5_1;

import javax.xml.soap.Node;

class Solution {
    public ListNode deleteNode(ListNode head, int val) {

        while (head != null && head.val == val) {//开始的节点就要移除的情况
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;

        }

        if (head == null)
            return null;

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }


    public ListNode conver(ListNode head) {


        ListNode prev = null;
        ListNode cur = head;
        ListNode temp = null;

        while (cur != null) {

            temp = cur.next;
            cur.next = prev;

            prev = cur;
            cur = temp;

        }
        return prev;
    }


    public static ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList(head.next);

        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(new int[]{1, 2, 3, 4});
        System.out.println(head);
        System.out.println(reverseList(head));
    }
}