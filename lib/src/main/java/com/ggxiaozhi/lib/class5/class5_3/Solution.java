package com.ggxiaozhi.lib.class5.class5_3;


class Solution {
//    public ListNode deleteNode(ListNode head, int val) {
//
//        while (head != null && head.val == val) {//开始的节点就要移除的情况
//            ListNode delNode = head;
//            head = head.next;
//            delNode.next = null;
//
//        }
//
//        if (head == null)
//            return null;
//
//        ListNode prev = head;
//        while (prev.next != null) {
//            if (prev.next.val == val) {
//                ListNode delNode = prev.next;
//                prev.next = delNode.next;
//                delNode.next = null;
//            } else {
//                prev = prev.next;
//            }
//        }
//        return head;
//    }


    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) return null;

        ListNode res = deleteNode(head.next, val);
        if (head.val == val) {
            return res;
        } else {
            head.next = res;
            return head;
        }


    }

    public ListNode deleteNodeSimp(ListNode head, int val) {
        if (head == null) return null;
        head.next = deleteNode(head.next, val);
        return head.val == val ? head.next : head;


    }

    public static int fib(int n) {

        int[] arr = new int[101];

        arr[0]=0;
        arr[1]=1;

        for (int i = 2; i <= 100; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % 1000000007;
        }

        return arr[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(45));

    }
}