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
     * <p>
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


    /**
     * 237. 删除链表中的节点
     * 这里问题的难点在于 不是给你一个头结点 而是直接给你一个待删出的节点
     * 那么我们就不可以用前prev指针的思想
     * 所以我们只能用值移动 比如head = [4,5,1,9], node = 5
     * 我们可以 4119 也就是覆盖
     * <p>
     * //TODO 自己一遍思考出来的 加油
     */
    public void deleteNode(ListNode node) {

        if (node == null || node.next == null)
            return;

        node.val = node.next.val;
        node.next = node.next.next;

    }

    /**
     * 19. 删除链表的倒数第N个节点
     * 解题思想就是 创建一个为n的固定长度的滑动窗口
     * p1指向n的前一个元素间隔n个距离找到p2 然后一起前进
     * //TODO 自己一遍思考出来的 加油
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {


        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = p1;

        int idx = 0;
        while (p2 != null) {

            if (n + 1 <= idx) {
                p1 = p1.next;
            }
            p2 = p2.next;
            idx++;
        }
        p1.next = p1.next.next;
        return dummy.next;

    }


    /**
     * 61. 旋转链表
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     * <p>
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * <p>
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     * <p>
     * 思路
     * 我们可以先从头遍历 链表 然后让最后一个节点指向头结点 形成一个环  然后我们直接判断从哪里断开链表就可以了
     * 如何判断的依据是 遍历后我们知道链表的size size-k=n 就是从头遍历到n然后让n的next指向空就好
     * <p>
     * 这里可以看到k是可能大于size 有个小技巧 用 k%size 就可以得到k<size
     * <p>
     * //TODO 自己一遍思考出来的 加油
     */
    public static ListNode rotateRight(ListNode head, int k) {

        if (head == null || head.next == null)

            return head;
        int size = 1;

        ListNode tail;
        ListNode temp = head;
        //这里不能用temp!=null 如果temp指向最后的null
        //那么下面  tail = temp; 这句就获取不到最后一个节点了
        while (temp.next != null) {
            temp = temp.next;
            size++;
        }
        tail = temp;
        //形成一个环
        tail.next = head;

        //避免多次旋转调换
        k = k % size;
        int n = size - k;

        ListNode cur = head;

        for (int i = 1; i < n; i++) {

            cur = cur.next;
        }
        //因为cur.next 是新的头 我们要保存下
        ListNode newHead = cur.next;
        cur.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        /**
         * 指针测试
         */
        ListNode listNode = rotateRight(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, null))))), 2);
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
