package com.ggxiaozhi.libreview;

/**
 * Create by ggxz   92. 反转链表 II
 * 2020/4/3
 * description:
 */
public class Solution2 {

    public static ListNode reverseBetween(ListNode head, int m, int n) {


        //虚拟头节点
        ListNode dummyHead = new ListNode(-1, null);
        dummyHead.next = head;
        //新的头节点
        ListNode tempHead = null;
        ListNode tail = null;
        ListNode tailHead = null;
        ListNode cur = dummyHead.next;
        ListNode tempPrev = dummyHead;
        ListNode prev = dummyHead;

        if (head == null || head.next == null) {
            return head;
        }

        if (m == n) {
            return head;
        }

        int idx = 1;


        while (cur != null) {

            if (idx == m) {
                tempHead = cur;

                //这里不能直接用prev
                //这位这个是prev是变化的
                tempPrev = prev;

            } else if (idx == n) {
                tail = cur;
                tailHead = cur.next;
                break;
            }
            prev = cur;
            cur = cur.next;
            idx++;
        }

        assert tail != null;
        tail.next = null;
        ListNode reverseHead = reverseNode(tempHead);
        assert reverseHead != null;
        tempPrev.next = reverseHead;
        tempHead.next = tailHead;
        return dummyHead.next;


    }

    public ListNode reverseBetween1(ListNode head, int m, int n) {
        ListNode dmy = new ListNode(0, null);
        dmy.next = head;
        int delta = n - m;
        ListNode pre = dmy, tail = head;
        //先定位出m节点和m之前的节点
        while (m > 1) {
            pre = tail;
            tail = tail.next;
            m--;
        }
        while (delta > 0) {
            ListNode next = tail.next;
            tail.next = next.next;//tail一直不变，只要修改指针到next.next
            next.next = pre.next;//next.next指向pre的next，也就是最新的第m个位置
            pre.next = next;//更新next为最新的第m个位置
            delta--;
        }

        return dmy.next;
    }

    public static ListNode reverseNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseNode(head.next);

        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        head.next = removeElements2(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null)))));
//        ListNode node = new ListNode(5, null);
        reverseBetween(node, 1, 5);
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */