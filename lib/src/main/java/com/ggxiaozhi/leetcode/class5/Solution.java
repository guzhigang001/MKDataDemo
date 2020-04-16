package com.ggxiaozhi.leetcode.class5;

import java.util.List;

/**
 * Create by ggxz
 * 2020/4/15
 * description: 括号里的可以先不做
 * <p>
 * 链表相关问题   83(判断 当时重复元素就删除On) 86(虚拟头节点On)  82 2  21 (445 328 25) 24  147 148
 */
@SuppressWarnings("ConstantConditions")
public class Solution {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }

        ListNode(int x) {
            val = x;
            this.next = null;
        }
    }

    /**
     * 面试题24. 反转链表
     * <p>
     * 递归
     *
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {

            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    /**
     * 面试题24. 反转链表
     * <p>
     * 迭代
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {

        if (head == null || head.next == null)
            return head;

        ListNode node = reverseList2(head.next);

        head.next.next = head;//
        head.next = null;
        return node;
    }

    /**
     * 思想是截取[m,n]之间的链表 然后翻转 翻转后返回头节点 然后原来m的前一个节点 prevTemp.next 接上翻转head  m节点反转后指向null 这里指向n.next
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null)
            return head;

        ListNode prevTemp = null;//翻转前 m前一个节点
        ListNode tailTemp = null;//翻转前 n的后一个节点
        ListNode curTemp = null;//翻转前 m节点用于指向tailTemp

        if (m == n)
            return head;

        int idx = 1;//从1开始

        ListNode cur = head;
        ListNode prev = null;

        while (cur != null) {

            if (idx == m) {
                curTemp = cur;
                prevTemp = prev;
            } else if (idx == n) {
                tailTemp = cur.next;
                cur.next = null;
                break;
            }
            prev = cur;
            cur = cur.next;
            idx++;
        }

        ListNode newHead = reverseList(curTemp);
        if (m != 1) {//不用虚拟头节点 就要额外判断 如果m==1 那么新的头节点和老的头节点一样 尾节点不用管
            prevTemp.next = newHead;
        } else {
            head = newHead;
        }
        curTemp.next = tailTemp;
        return head;
    }

    /**
     * 83. 删除排序链表中的重复元素
     */
    public ListNode deleteDuplicates(ListNode head) {

        return null;
    }

    /**
     * 203. 移除链表元素
     */
    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        // 新链表的头结点
        ListNode newHead = new ListNode(0, null);
        // 新链表的尾节点
        ListNode newTail = newHead;

        while (head != null) {
            if (head.val != val) {
                newTail.next = head;
                newTail = head;
            }

            head = head.next;
        }

        newTail.next = null;

        return newHead.next;
    }


    /**
     * 24. 两两交换链表中的节点
     * 思路 就是 没便利一个节点记录 idx(从1开始) 维护两个指针 prev和cur 然后调换后
     * prev.next=原cur.next
     * // 1  2  3  4  5
     * // p  c
     * // c  p
     * <p>
     * //TODO 想法是对的 但是还是对 java的引用不太理解 这个要多复习下
     */
    public static ListNode swapPairs(ListNode head) {

        ListNode dummy = new ListNode(-1, null);
        dummy.next = head;

        ListNode prev = dummy;

        while (prev.next != null && prev.next.next != null) {//交换的元素是 prev的下一个元素和 下一个元素 如果有一个为null就没办法交换了
            ListNode node1 = prev.next;
            ListNode node2 = prev.next.next;
            ListNode next = node2.next;

            //交换 node1和node2
            node2.next = node1;
            node1.next = next;
            //更新头结点  第一次循环p指向dummy 交换后node2是新的头 所以prev.next = node2;

            prev.next = node2;

            //更新prev的引用(指针) 那么 第一次指向是dummy那么就更新头
            prev = node1;
        }
        return dummy.next;
    }

    /**
     * 24. 两两交换链表中的节点 递归思想  //TODO 这个需要那纸笔画一下
     */
    public static ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }


    /**
     * 148. 排序链表
     *
     * //         ListNode f = head;
     * //        ListNode s = head;
     * //        ListNode p = head;
     * //        while (f != null && f.next != null) {
     * //
     * //            p = s;
     * //            s = s.next;
     * //            f = f.next.next;
     * //        }
     * 最后p相当于mid
     */
    public static ListNode sortList(ListNode head) {

        if (head == null || head.next == null)
            return head;

        //快慢指针 找到中间节点，类似于归并排序找mid的过程 fast2步 slow1步
        //最后fast指向最后一个节点 slow指向中间节点
        ListNode slow = head;
        ListNode fast = head.next;//起始的时候 fast比slow快一步
        //也可以写成注释的样子
        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;
        }



        ListNode head2 = slow.next;//归并排序 右边的链表

        //cut节点 head节点一直到slow节点 成一个新的链表
        slow.next = null;

        head = sortList(head);
        head2 = sortList(head2);

        return merge(head, head2);

    }

    private static ListNode merge(ListNode head, ListNode head2) {


        ListNode dummyHead = new ListNode(-1);

        ListNode p = dummyHead;//p存储排序后的链表 p的指针指着上一个排好序的节点同时 p.next=null等待接入下一个排序节点
        ListNode p1 = head;//左边排序链表节点 从头节点一直向后移动
        ListNode p2 = head2;//右边排序链表节点 从头节点一直向后移动
        while (true) {
            if (p1.val > p2.val) {
                p.next = p2;
                p = p.next;
                p2 = p2.next;

                //p.next=null等待接入下一个排序节点
                p.next = null;

            } else {
                p.next = p1;
                p = p.next;
                p1 = p1.next;

                p.next = null;
            }


            if (p1 == null) {//如果p1=null 说明左边的 宣布排完序了 那么直接p接入右边链表的剩下节点
                p.next = p2;
                break;
            }
            if (p2 == null) {
                p.next = p1;
                break;
            }
        }

        return dummyHead.next;
    }

    private static int getNodeSize(ListNode head) {
        int size = 0;
        for (ListNode temp = head; temp != null; temp = temp.next) {
            size++;
        }
        return size;
    }

    public static void main(String[] args) {
        /**
         * 指针测试
         */
        ListNode listNode = sortList(new ListNode(3, new ListNode(2, new ListNode(1, new ListNode(4, null)))));
        System.out.println(listNode);
//
//        ListNode node = new ListNode(1, new ListNode(2, null));
//        ListNode temp = node.next;
//        node.next = null;
//        System.out.println(temp.val);
//        System.out.println(node.next.val);

        System.out.println(getNodeSize(listNode));
    }
}
