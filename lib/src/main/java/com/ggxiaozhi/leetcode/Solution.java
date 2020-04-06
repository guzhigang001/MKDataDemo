package com.ggxiaozhi.leetcode;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/4/6 15:31
 * @UpdateUser:
 * @UpdateDate: 2020/4/6 15:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }

    //141. 环形链表
    public boolean hasCycle(ListNode head) {


        //龟兔赛跑p1 每次有1个节点 p2每次走2个节点 如果有环 那么p2会追上p1 并接首次相遇p2比p1夺走了一个环
        //如果p2 遍历到了最后一个节点 就表示没有环
        ListNode p1 = head, p2 = head;


        while (p2 != null && p2.next != null) {

            p1 = p1.next;
            p2 = p2.next.next;

            if (p2 == p1) {
                return true;
            }
        }

        //循环中没有返回就说明 没有环
        return false;
    }

    //求环的长度
    public static int cycleLength(ListNode head) {
        if (head == null || head.next == null) {
            return -1;
        }

        //龟兔赛跑p1 每次有1个节点 p2每次走2个节点 如果有环 那么p2会追上p1 并接首次相遇p2比p1夺走了一个环
        //如果p2 遍历到了最后一个节点 就表示没有环
        ListNode p1 = head, p2 = head;

        int n1 = 0, n2 = 0;

        while (p2.next != null && p2.next.next != null) {

            p1 = p1.next;
            n1 = n1 + 1;
            p2 = p2.next.next;
            n2 = n2 + 2;

            if (p2.val == p1.val) {
                return n2 - n1;
            }
        }

        //循环中没有返回就说明 没有环
        return -1;
    }

    //142. 环形链表 II
    public static ListNode detectCycle(ListNode head) {
        ListNode p1 = head, p2 = head;

        boolean isCycle = false;

        while (p2 != null && p2.next != null) {

            p1 = p1.next;
            p2 = p2.next.next;

            if (p2 == p1) {
                isCycle = true;
                break;
            }
        }

        if (isCycle) {
            p1 = head;
            while (p2 != null && p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
                if (p1 == p2)
                    break;
            }
            return p1;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {

        ListNode node4 = new ListNode(-4);
        ListNode node3 = new ListNode(0, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(3, node2);
        node4.next = node2;

        System.out.println(detectCycle(node1).val);


    }
}
