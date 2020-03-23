package com.ggxiaozhi.review.class5;

import com.ggxiaozhi.lib.class5.main5_1.ListNode;

import javax.xml.soap.Node;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/3/18 13:59
 * @UpdateUser:
 * @UpdateDate: 2020/3/18 13:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) {

//        int[] arr = {1, 2, 6, 3, 4, 5, 6};
        int[] arr = {1, 2, 6, 3};
        ListNode node = new ListNode(arr);
        System.out.println(node);

        System.out.println(reverseList2(node));

//        System.out.println(removeElements4(node, 6));

    }

    public static ListNode removeElements(ListNode head, int val) {


        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode cur = dummyHead;
        while (cur.next != null) {


            if (cur.next.val == val) {
                cur.next = cur.next.next;

            } else {
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }

    public static ListNode removeElements2(ListNode head, int val) {

        if (head == null) {
            return null;
        }

        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {

                if (prev == null) {
                    cur = cur.next;
                    head = cur;
                } else {
                    prev.next = cur.next;
                    cur.next = null;
                    cur = prev.next;

                }
            } else {
                prev = cur;
                cur = cur.next;

            }
        }
        return head;
    }

    public static ListNode removeElements3(ListNode head, int val) {

        if (head == null) {
            return null;
        }


        ListNode newHead = removeElements(head.next, val);

        if (head.val == val) {
            return newHead;
        } else {
            head.next = newHead;
            return head;
        }
    }

    public static ListNode removeElements4(ListNode head, int val) {

        if (head == null) {
            return null;
        }


        head.next = removeElements4(head.next, val);

        return head.val == val ? head.next : head;
    }

    public static ListNode reverseList(ListNode head) {

        ListNode cur = head;
        ListNode temp = null;
        ListNode prev = null;


//        do {
//            temp = cur.next;
//            cur.next = prev;
//            prev = cur;
//            cur = temp;
//        } while (cur != null);

        while (cur != null) {
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    public static ListNode reverseList2(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode newHead = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public ListNode(int[] arr) {
            if (arr.length != 0) {
                val = arr[0];
                ListNode head = this;
                ListNode cur = head;
                for (int i = 1; i < arr.length; i++) {
                    cur.next = new ListNode(arr[i]);
                    cur = cur.next;
                }


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
}
