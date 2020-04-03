package com.ggxiaozhi.libreview.class4;

/**
 * Create by ggxz
 * 2020/4/3
 * description:
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution2 {
    public static ListNode removeElements(ListNode head, int val) {

        if (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null) {
            return null;
        }

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode del = prev.next;
                prev.next = del.next;
                del.next = null;
                //TODO  这里不能直接赋值  可能 删除得点是连续得
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    public static ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        head.next = removeElements2(head.next, val);
        return head.val==val?head.next:head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        ListNode(int x, ListNode node) {
            val = x;
            next = node;
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(3, new ListNode(2, null)))));
        ListNode listNode = removeElements2(node, 2);
        System.out.println(listNode);
    }
}
