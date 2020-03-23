package com.ggxiaozhi.lib.class5;

import com.ggxiaozhi.lib.class5.class5_3.ListNode;

/**
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 * <p>
 * 这些数位是反向存放的，也就是个位排在链表首部。
 * <p>
 * 编写函数对这两个整数求和，并用链表形式返回结果。
 * <p>
 * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
 * 输出：2 -> 1 -> 9，即912
 */
class Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;

        ListNode ans = new ListNode(0);//类似虚拟头节点
        ListNode cur = ans;
        while (l1 != null || l2 != null) {
            int sum1 = l1 == null ? 0 : l1.val;
            int sum2 = l2 == null ? 0 : l2.val;

            int sum = sum1 + sum2 + carry;//位数相加 carry表示是否有进位
            carry = sum / 10;//如果有进位 carry则+1

            //上面的操作完 cur这个节点 完成创建
            cur.next = new ListNode(sum % 10);
            cur = cur.next;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;


        }
        if (carry == 1) {
            cur.next  = new ListNode(carry);
        }

        return ans.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(new int[]{7, 1, 6});
        ListNode node2 = new ListNode(new int[]{5, 9, 2});

        System.out.println(addTwoNumbers(node1, node2));
    }
}
