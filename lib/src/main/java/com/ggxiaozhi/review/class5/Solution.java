package com.ggxiaozhi.review.class5;

/**
 * @Description: 链表的反转  https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
 * @Author: ggxz
 * @CreateDate: 2020/3/18 17:38
 * @UpdateUser:
 * @UpdateDate: 2020/3/18 17:38
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Solution {

    public ListNode reverseList(ListNode head) {

        ListNode cur = head;
        ListNode temp = null;
        ListNode prev = null;

        while (cur.next != null) {
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return head;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
