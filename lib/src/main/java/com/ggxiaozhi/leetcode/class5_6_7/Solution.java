package com.ggxiaozhi.leetcode.class5_6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import javafx.util.Pair;

/**
 * Create by ggxz
 * 2020/4/15
 * description: 括号里的可以先不做
 * <p>
 * 链表相关问题   83(判断 当时重复元素就删除On) 86(虚拟头节点On)  82 2  21 (445 328 25) 24  147 148
 * (129 113)
 * <p>
 * //TODO 437递归题解 解释递归的含义：
 * 递归的一个重要思想就是两部分：1.找到最简单的子问题求解，2.其他问题不考虑内在细节，只考虑整体逻辑
 */
@SuppressWarnings({"ConstantConditions", "ResultOfMethodCallIgnored"})
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


    /**
     * 143. 重排链表
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     * <p>
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * <p>
     * 示例 1:
     * <p>
     * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
     * <p>
     * 示例 2:
     * <p>
     * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
     * <p>
     * 思想就是 找到中间节点 然后 断链 将后面的翻转 然后将两个链表依次向后链接
     */
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;

        ListNode fast = head;
        ListNode slow = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode head2 = slow.next;
        slow.next = null;

        head2 = reverseList(head2);

        /**
         *
         * 1. 先取出 head2的下一个节点temp
         * 2. head2.next=head.next
         * 3. head.next=head2
         * 4. head2=temp
         *
         * 这个难点在于指针的指向
         * 我们要保证 head节点的next不会丢失
         * 我们可以用temp存head2的下个一节点  head2的节点不会丢失了
         * 然后让head2=temp 相当于head2向后移动
         *
         * temp=head2.next head2不会丢失
         * 然后head2.next=head.next 保证head的下一个节点不会丢失
         *
         *
         */
        while (head2 != null) {

            ListNode temp = head2.next;
            head2.next = head.next;
            head.next = head2;
            head = head2.next;
            head2 = temp;
        }
    }

    /**
     * 143. 重排链表 递归
     * https://leetcode-cn.com/problems/reorder-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-34/
     * 思想是
     * 先处理中间 然后中间处理好了 再将外面的head 和tail 连接  递归过程
     * 1 2 3 4 5
     * 开始：
     * 1 2 3 4 5
     * 1 4 2 3 5
     * 1 5 4 2 3
     * 这里的关键是每次递归 len-1
     */
    public static void reorderList2(ListNode head) {

        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        int len = 0;
        ListNode h = head;
        //求出节点数
        while (h != null) {
            len++;
            h = h.next;
        }

        reorderListHelper(head, len);
    }

    private static ListNode reorderListHelper(ListNode head, int len) {
        if (len == 1) {
            ListNode outTail = head.next;
            head.next = null;
            return outTail;
        }
        if (len == 2) {
            ListNode outTail = head.next.next;
            head.next.next = null;
            return outTail;
        }
        //得到对应的尾节点，并且将头结点和尾节点之间的链表通过递归处理
        ListNode tail = reorderListHelper(head.next, len - 2);
        ListNode subHead = head.next;//中间链表的头结点
        head.next = tail;
        ListNode outTail = tail.next;  //上一层 head 对应的 tail
        tail.next = subHead;
        return outTail;
    }

    /**
     * 234. 回文链表
     * <p>
     * 请判断一个链表是否为回文链表。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2
     * 输出: false
     * <p>
     * 示例 2:
     * <p>
     * 输入: 1->2->2->1
     * 输出: true
     * <p>
     * 递归练习 根据143. 重排链表 递归思想练习
     */

    static ListNode front = null;

    public static boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        front = head;
        return recursive(head);

    }

    private static boolean recursive(ListNode head) {
        if (head.next == null) {
            boolean b = front.val == head.val;
            front = front.next;
            return b;
        }
        if (!recursive(head.next)) {
            return false;
        } else {
            boolean b = front.val == head.val;
            front = front.next;

            return b;
        }
    }

    /**
     * 150. 逆波兰表达式求值
     * <p>
     * 根据逆波兰表示法，求表达式的值。
     * <p>
     * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     * <p>
     * 说明：
     * <p>
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     * <p>
     * 示例 1：
     * <p>
     * 输入: ["2", "1", "+", "3", "*"]
     * 输出: 9
     * 解释: ((2 + 1) * 3) = 9
     * <p>
     * 示例 2：
     * <p>
     * 输入: ["4", "13", "5", "/", "+"]
     * 输出: 6
     * 解释: (4 + (13 / 5)) = 6
     * <p>
     * 示例 3：
     * <p>
     * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
     * 输出: 22
     * 解释:
     * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
     * = ((10 * (6 / (12 * -11))) + 17) + 5
     * = ((10 * (6 / -132)) + 17) + 5
     * = ((10 * 0) + 17) + 5
     * = (0 + 17) + 5
     * = 17 + 5
     * = 22
     */
    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();
        int p1 = 0;
        int p2 = 0;
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "+":
                    p2 = stack.pop();
                    p1 = stack.pop();
                    stack.push(p1 + p2);
                    break;
                case "-":
                    p2 = stack.pop();
                    p1 = stack.pop();
                    stack.push(p1 - p2);
                    break;
                case "*":
                    p2 = stack.pop();
                    p1 = stack.pop();
                    stack.push(p1 * p2);
                    break;
                case "/":
                    p2 = stack.pop();
                    p1 = stack.pop();
                    stack.push(p1 / p2);
                    break;
                default:
                    stack.push(Integer.valueOf(tokens[i]));
                    break;
            }
        }

        return stack.pop();
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int x, TreeNode left, TreeNode right) {
            val = x;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 144. 二叉树的前序遍历
     * 给定一个二叉树，返回它的 前序 遍历。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,null,2,3]
     * 1
     * \
     * 2
     * /
     * 3
     * <p>
     * 输出: [1,2,3]
     * <p>
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     */
    public static List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        preorderTraversal(root, list);

        return list;

    }

    /**
     * 递归前序遍历
     */
    private static void preorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        list.add(root.val);
        preorderTraversal(root.left, list);
        preorderTraversal(root.right, list);
    }


    /**
     * 144 非递归
     */
    public static List<Integer> preorderTraversal2(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop == null) {
                continue;
            }
            list.add(pop.val);
            if (pop.right != null) {
                stack.push(pop.right);
            }

            if (pop.left != null) {
                stack.push(pop.left);
            }
        }

        return list;
    }


    /**
     * 94. 二叉树的中序遍历
     * <p>
     * 给定一个二叉树，返回它的中序 遍历。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,null,2,3]
     * 1
     * \
     * 2
     * /
     * 3
     * <p>
     * 输出: [1,3,2]
     * <p>
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     */
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        inorderTraversal(root, list);
        return list;
    }

    private void inorderTraversal(TreeNode root, List<Integer> list) {

        if (root == null)
            return;
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }


    /**
     * 94. 二叉树的中序遍历
     * 非递归
     */
    public static List<Integer> inorderTraversal2(TreeNode root) {

        if (root == null)
            return new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        stack.push(root);
        while (!stack.isEmpty()) {
            while (cur.left != null) {
                stack.push(cur.left);
                cur = cur.left;
            }
            TreeNode pop = stack.pop();

            if (pop != null) {
                list.add(pop.val);
                if (pop.right != null) {
                    stack.push(pop.right);
                    cur = pop.right;
                }
            }


        }

        return list;
    }

    /**
     * 145. 二叉树的后序遍历
     * <p>
     * 递归遍历
     * <p>
     * 给定一个二叉树，返回它的 后序 遍历。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,null,2,3]
     * 1
     * \
     * 2
     * /
     * 3
     * <p>
     * 输出: [3,2,1]
     * <p>
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     * <p>
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        postorderTraversal(root, list);
        return list;
    }

    private void postorderTraversal(TreeNode root, List<Integer> list) {

        if (root == null) {
            return;
        }
        postorderTraversal(root.left, list);
        postorderTraversal(root.right, list);
        list.add(root.val);
    }

    /**
     * 后序遍历(非递归算法)
     * ①先序遍历顺序：根节点-左孩子-右孩子
     * ②后序遍历顺序：左孩子-右孩子-根节点
     * ③后序遍历倒过来：根节点-右孩子-左孩子
     * ①和③对比发现，访问顺序只有左孩子和右孩子颠倒了一下
     * 思路：
     * 第一步，将二叉树按照先序非递归算法进行遍历，
     * 注意在入栈的时候左右孩子入栈的顺序，先左后右 。
     * 第二步，将遍历得到的结果进行倒置。
     * <p>
     * 也可以用队列
     */
    public List<Integer> postorderTraversal2(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop == null) {
                continue;
            }
            list.add(pop.val);
            if (pop.left != null) {
                stack.push(pop.left);
            }

            if (pop.right != null) {
                stack.push(pop.right);
            }


        }

        Collections.reverse(list);
        return list;
    }

    /**
     * https://leetcode-cn.com/problems/flatten-nested-list-iterator/solution/shen-du-bian-li-java-4ms-by-dong-ci-da-ci-7/
     * <p>
     * 341. 扁平化嵌套列表迭代器
     * <p>
     * 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
     * <p>
     * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: [[1,1],2,[1,1]]
     * 输出: [1,1,2,1,1]
     * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
     * <p>
     * 示例 2:
     * <p>
     * 输入: [1,[4,[6]]]
     * 输出: [1,4,6]
     * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
     */
    public static class NestedIterator implements Iterator<Integer> {

        Queue<Integer> queue = new LinkedList<>();

        public NestedIterator(List<NestedInteger> nestedList) {

            for (NestedInteger nestedInteger : nestedList) {
                separation(nestedInteger);
            }

        }

        @Override
        public Integer next() {

            return queue.poll();
        }

        @Override
        public boolean hasNext() {

            return !queue.isEmpty();
        }

        public void separation(NestedInteger nestedList) {

            if (nestedList.isInteger()) {
                queue.offer(nestedList.getInteger());
            } else {
                for (NestedInteger integer : nestedList.getList()) {
                    separation(integer);
                }
            }
        }

    }

    /**
     * 341. 扁平化嵌套列表迭代器利用站的思想
     * 两个栈一个存入 所有元素 开始元素再栈低
     * 最后元素再栈定
     * <p>
     * 第二个栈是用来存数字 将第一个栈取出来 顺序就变成正序了
     */
    public static class NestedIterator2 implements Iterator<Integer> {

        //存入所有元素
        Stack<NestedInteger> mainStack = new Stack<>();
        //存入int数据 正序输出
        Stack<Integer> intStack = new Stack<>();

        public NestedIterator2(List<NestedInteger> nestedList) {

            for (NestedInteger nestedInteger : nestedList) {
                mainStack.push(nestedInteger);
            }

            separation(mainStack);
        }

        private void separation(Stack<NestedInteger> mainStack) {

            while (!mainStack.isEmpty()) {
                NestedInteger pop = mainStack.pop();
                if (pop.isInteger()) {
                    intStack.push(pop.getInteger());
                } else {
                    for (NestedInteger integer : pop.getList()) {
                        mainStack.push(integer);
                    }
                }
            }
        }

        @Override
        public Integer next() {

            return intStack.pop();
        }

        @Override
        public boolean hasNext() {

            return !intStack.isEmpty();
        }


    }

    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }


    /**
     * 102. 二叉树的层序遍历
     * <p>
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * <p>
     * <p>
     * <p>
     * 示例：
     * 二叉树：[3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * 返回其层次遍历结果：
     * <p>
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     * <p>
     * <p>
     * 思路：
     * 这个每一层是一组 那么我们就要判断每每个元素处于那一层
     * 所以我们利用层序遍历 将每一次全部放进队列 然后 全部取出 再将下一层全部放入 全部取出的数据就是当前层的数据
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

        if (root == null)
            return new ArrayList<>();

        //用于返回
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(list);
        }

        return res;
    }

    /**
     * 102. 二叉树的层序遍历
     */
    public List<List<Integer>> levelOrder2(TreeNode root) {

        if (root == null)
            return new ArrayList<>();

        //用于返回
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()) {

            int size = queue.size();
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(list);
        }

        return res;
    }

    /**
     * 111. 二叉树的最小深度
     * <p>
     * 给定一个二叉树，找出其最小深度。
     * <p>
     * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例:
     * <p>
     * 给定二叉树 [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * <p>
     * 返回它的最小深度  2.
     */
    public static int minDepth(TreeNode root) {

        if (root == null)
            return 0;
        if (root.left == null && root.right == null) {
            return 1;
        }

        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);

        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }
        return min_depth + 1;
    }


    /**
     * 226. 翻转二叉树
     * <p>
     * 翻转一棵二叉树。
     * <p>
     * 示例：
     * <p>
     * 输入：
     * <p>
     * 4
     * /   \
     * 2     7
     * / \   / \
     * 1   3 6   9
     * <p>
     * 输出：
     * <p>
     * 4
     * /   \
     * 7     2
     * / \   / \
     * 9   6 3   1
     * <p>
     * 备注:
     * 这个问题是受到 Max Howell 的 原问题 启发的 ：
     * <p>
     * 谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
     */
    public TreeNode invertTree(TreeNode root) {

        if (root == null || (root.left == null && root.right == null))
            return root;
        TreeNode right = root.right;
        TreeNode left = root.left;
        root.left = right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    /**
     * 100. 相同的树
     * <p>
     * 给定两个二叉树，编写一个函数来检验它们是否相同。
     * <p>
     * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
     * <p>
     * 示例 1:
     * <p>
     * 输入:       1         1
     * / \       / \
     * 2   3     2   3
     * <p>
     * [1,2,3],   [1,2,3]
     * <p>
     * 输出: true
     * <p>
     * 示例 2:
     * <p>
     * 输入:      1          1
     * /           \
     * 2             2
     * <p>
     * [1,2],     [1,null,2]
     * <p>
     * 输出: false
     * <p>
     * 示例 3:
     * <p>
     * 输入:       1         1
     * / \       / \
     * 2   1     1   2
     * <p>
     * [1,2,1],   [1,1,2]
     * <p>
     * 输出: false
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null)
            return true;
        else {
            if (p == null || q == null) {
                return false;
            } else {
                if (p.val == q.val) {
                    return (isSameTree(p.left, q.left)) && isSameTree(p.right, q.right);
                } else
                    return false;
            }

        }
    }

    /**
     * 101. 对称二叉树
     * <p>
     * 给定一个二叉树，检查它是否是镜像对称的。
     * <p>
     * <p>
     * <p>
     * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
     * <p>
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * <p>
     * <p>
     * <p>
     * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
     * <p>
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     * <p>
     * <p>
     * <p>
     * 进阶：
     * <p>
     * 你可以运用递归和迭代两种方法解决这个问题吗？
     * 将左子树 或者右子树翻转 然后判断两个数是否是相同的数
     */
    public boolean isSymmetric(TreeNode root) {

        if (root == null)
            return true;
        if (root.left != null && root.right != null) {
            TreeNode invertTree = invertTree(root.left);
            return isSameTree(invertTree, root.right);
        } else {
            return root.left == null && root.right == null;
        }
    }

    /**
     * 101. 对称二叉树
     * <p>
     * 非递归遍历
     * 利用二叉树的层序遍历
     * 再利用双端对列 比较首尾元素是否相等 相等出队 再成对比较
     * <p>
     * 这种丢列的思想 实验后发现还是有问题
     * 这种做法丢失了 左右孩子的衔接性
     * 具体错误用例和错误原因 参考   https://coding.imooc.com/learn/questiondetail/183123.html
     */
    public static boolean isSymmetric2(TreeNode root) {

        if (root == null)
            return true;
        if (root.right == null && root.left == null)
            return true;
        if (root.left == null || root.right == null)
            return false;
        Deque<Pair<TreeNode, Boolean>> deque = new LinkedList<>();
        Pair<TreeNode, Boolean> pairL = new Pair<>(root.left, true);
        Pair<TreeNode, Boolean> pairR = new Pair<>(root.right, false);


        deque.add(pairL);
        deque.add(pairR);

        while (!deque.isEmpty()) {
            List<Pair<TreeNode, Boolean>> list = new ArrayList<>(deque);

            if (deque.size() % 2 != 0)//因为是成对出现的 队列里面的数一定是偶数如果不是那么就不用判断了 就是false
                return false;
            while (!deque.isEmpty()) {
                Pair<TreeNode, Boolean> t1 = deque.removeFirst();
                Pair<TreeNode, Boolean> t2 = deque.removeLast();

                TreeNode first = t1.getKey();
                TreeNode last = t2.getKey();
                if (first == null && last == null) {
                    continue;
                }
                if (first == null || last == null)
                    return false;
                if (first.val != last.val)
                    return false;
                if (first.val == last.val) {
                    if (t1.getValue() && t2.getValue())
                        return false;
                    if (!t1.getValue() && !t2.getValue())
                        return false;
                }

            }
            for (int i = 0; i < list.size(); i++) {
                Pair<TreeNode, Boolean> pair = list.get(i);
                if (pair.getKey().left != null)
                    deque.add(new Pair<TreeNode, Boolean>(pair.getKey().left, true));
                if (pair.getKey().right != null)
                    deque.add(new Pair<TreeNode, Boolean>(pair.getKey().right, false));

            }
        }
        return true;
    }

    /**
     * 101. 对称二叉树
     * <p>
     * 非递归遍历
     * 利用二叉树的层序遍历
     * 再利用队列 分别将下层的相同位置的左孩子和有孩子比较
     */

    public static boolean isSymmetric3(TreeNode root) {

        if (root == null)
            return true;
        if (root.right == null && root.left == null)
            return true;
        if (root.left == null || root.right == null)
            return false;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root.left);
        queue.add(root.right);
        while (!queue.isEmpty()) {

            TreeNode p1 = queue.poll();
            TreeNode p2 = queue.poll();

            if (p1 == null && p2 == null) {
                continue;
            }
            if (p1 == null || p2 == null)
                return false;
            if (p1.val != p2.val)
                return false;

            //这个顺序是不能乱的
            queue.offer(p1.left);
            queue.offer(p2.right);
            queue.offer(p1.right);
            queue.offer(p2.left);
        }

        return true;
    }

    /**
     * 112. 路径总和
     * <p>
     * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例:
     * 给定如下二叉树，以及目标和 sum = 22，
     * <p>
     * 5
     * / \
     * 4   8
     * /   / \
     * 11  13  4
     * /  \      \
     * 7    2      1
     * <p>
     * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
     * <p>
     * <p>
     * <p>
     * 思路：
     * 就是向左右子树找 sum-root.val 一直找到叶子节点 看是否存在
     */
    public boolean hasPathSum(TreeNode root, int sum) {

        if (root == null)
            return false;
        if (root != null && (root.left == null && root.right == null)) {
            return root.val == sum;
        }
        boolean l = false;
        if (root.left != null) {
            l = hasPathSum(root.left, sum - root.val);
        }
        boolean r = false;
        if (root.right != null) {
            r = hasPathSum(root.right, sum - root.val);
        }
        return l || r;

    }

    /**
     * 257. 二叉树的所有路径
     * <p>
     * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     * <p>
     * 示例:
     * <p>
     * 输入:
     * <p>
     * 1
     * /   \
     * 2     3
     * \
     * 5
     * <p>
     * 输出: ["1->2->5", "1->3"]
     * <p>
     * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
     * <p>
     * 思路：
     * 玄幻遍历左右子数 判断是否是叶子节点
     */
    public List<String> binaryTreePaths(TreeNode root) {

        List<String> list = new ArrayList<>();


        binaryTreePaths(root, list, "");
        return list;
    }

    private void binaryTreePaths(TreeNode root, List<String> list, String paths) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {

            paths += "" + root.val;
            list.add(paths);
            return;
        }

        paths += root.val + "->";
        binaryTreePaths(root.left, list, paths);
        binaryTreePaths(root.right, list, paths);
    }

    /**
     * 437. 路径总和 III
     * <p>
     * //TODO 课程问题
     * <p>
     * <p>
     * 给定一个二叉树，它的每个结点都存放着一个整数值。
     * <p>
     * 找出路径和等于给定数值的路径总数。
     * <p>
     * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
     * <p>
     * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
     * <p>
     * 示例：
     * <p>
     * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
     * <p>
     * 10
     * /  \
     * 5   -3
     * / \    \
     * 3   2   11
     * / \   \
     * 3  -2   1
     * <p>
     * 返回 3。和等于 8 的路径有:
     * <p>
     * 1.  5 -> 3
     * 2.  5 -> 2 -> 1
     * 3.  -3 -> 11
     * <p>
     * 解题思路
     * 题解
     * <p>
     * 本题需要去计算路径和等于给定数值的路径总数，我们依旧遵循树模型的解题思路，按照递归的方式去求解（递归的一个重要思想就是两部分：1.找到最简单的子问题求解，2.其他问题不考虑内在细节，只考虑整体逻辑），那我们现在来设计递归关系：
     * <p>
     * 首先，最简单的子问题是什么呢？由于这道题是在树的框架下，我们最容易想到的就是遍历的终止条件：
     * <p>
     * if(root == null){
     * return 0;
     * }
     * <p>
     * 接下来，我们来考虑再上升的一个层次，题目要求 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点） 。这就要求我们只需要去求三部分即可：
     * <p>
     * 以当前节点作为头结点的路径数量
     * 以当前节点的左孩子作为头结点的路径数量
     * 以当前节点的右孩子作为头结点啊路径数量
     * <p>
     * 将这三部分之和作为最后结果即可。
     * <p>
     * 最后的问题是：我们应该如何去求以当前节点作为头结点的路径的数量？这里依旧是按照树的遍历方式模板，每到一个节点让sum-root.val，并判断sum是否为0，如果为零的话，则找到满足条件的一条路径。
     * <p>
     * 链接：https://leetcode-cn.com/problems/path-sum-iii/solution/437lu-jing-zong-he-iii-di-gui-fang-shi-by-ming-zhi/
     */
    //返回 以root为节点的路径个数
    public int pathSum(TreeNode root, int sum) {//这个解题的代码思想就是 ，以当前节点为root节点 ，从这个节点开始向下找
        //看是否有这个节点 和上一题不同的是 我们不用找到叶子节点返回，这里是我们找到了也不返回 因为这个题中存在负数 可能sum为8 我们找到了8
        // 8+(-a)+(a)=8 所以我们找到了也不返回

        if (root == null)
            return 0;
        int res = 0;

        //返回以root为节点 存在sum的和的路径个数
        res = findPath(root, sum);
        //这里res可能有 可能没有 不过没关系 没有我们就看下一个节点 看看是否能找到
        //符合题意得路径
        res += pathSum(root.left, sum);
        res += pathSum(root.right, sum);
        return res;
    }

    //找到以root为节点的和为sum的路径个数
    private int findPath(TreeNode root, int sum) {
        if (root == null)
            return 0;

        int res = 0;
        if (root.val == sum) {
            res += 1;
        }

        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
    }

    /**
     * 235. 二叉搜索树的最近公共祖先
     * <p>
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * <p>
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     * <p>
     * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
     * 输出: 6
     * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
     * <p>
     * 示例 2:
     * <p>
     * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
     * 输出: 2
     * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
     * <p>
     * <p>
     * <p>
     * 说明:
     * <p>
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉搜索树中。
     * <p>
     * //TODO BST 概念还是不熟悉 算法也要多练习BST相关的额
     * //这个问题在于 如果PQ 在root两侧 那么root就是最小公共节点这一个定理
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) {// 1. 其中一个为父亲节点得情况
            return root;
        }

        if ((root.val > p.val && root.val < q.val) || (root.val < p.val && root.val > q.val)) {//4. 如果PQ 再root两侧 那么root就是最小公共节点 返回
            return root;
        }

        if (root.val > p.val && root.val > q.val) {//2. 都在左侧 难么继续调用
            return lowestCommonAncestor(root.left, p, q);//去左子树去找
        } else {//root.val < p.val && root.val < q.val 3 都在右子树 那么去右子树去找。
            return lowestCommonAncestor(root.right, p, q);
        }
    }

    /**
     * 17. 电话号码的字母组合
     * TODO 视频中的讲解
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * <p>
     * 示例:
     * <p>
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * <p>
     * 说明:
     * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
     * <p>
     * 思路：
     * 可以想成是一种类似数的结构 比如 23
     * //             2
     * //         a/ b| \c
     * //        3    3   3
     * //     d/e|\f
     * //    ad ae af bd be bf cd ce cf
     * //
     * 1. 针对数字 去letterMap中找到对应的字符串
     * 2. 取出字符串中的每个字符
     * 3. 将每个字符拼接到已经拼接好的字符中
     * 4. index+1看下一个字符
     */
    private static final String[] letterMap = {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    static List<String> res = new ArrayList<>();

    public static List<String> letterCombinations(String digits) {

        if (digits.isEmpty())
            return res;
        findLetter(digits, 0, "");
        return res;
    }

    /**
     * 针对给定字符 我们要组合的所有路径
     *
     * @param digits 问题中的字符串
     * @param index  我们要处理digits的下标对应的字符
     * @param s      这条树中经过路径的之前已经拼接好的字符
     */
    private static void findLetter(String digits, int index, String s) {
        //递归终止条件 index已经达到digits长度 到这里说明一个路径已经走到头了 那么我们需要将结果加入到res中
        if (digits.length() == index) {
            res.add(s);
            return;
        }
        //取出要遍历到了字符串的哪个元素
        char c = digits.charAt(index);
        //取出数字对应的字符串
        String letter = letterMap[c - '0'];

        for (int i = 0; i < letter.length(); i++) {
            //取出每个字符串的字符 然后和已经拼接好的字符串s进行拼接 同时index要+1
            String nextS = s + letter.charAt(i);
            findLetter(digits, index + 1, nextS);
            nextS.substring(0, nextS.length() - 1);
        }
    }


    /**
     * 450. 删除二叉搜索树中的节点
     * <p>
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     * <p>
     * 一般来说，删除节点可分为两个步骤：
     * <p>
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     * <p>
     * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
     * <p>
     * 示例:
     * <p>
     * root = [5,3,6,2,4,null,7]
     * key = 3
     * <p>
     * 5
     * / \
     * 3   6
     * / \   \
     * 2   4   7
     * <p>
     * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
     * <p>
     * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
     * <p>
     * 5
     * / \
     * 4   6
     * /     \
     * 2       7
     * <p>
     * 另一个正确答案是 [5,2,6,null,4,null,7]。
     * <p>
     * 5
     * / \
     * 2   6
     * \   \
     * 4   7
     * <p>
     * 这个方法返回删除后的根节点
     */
    public TreeNode deleteNode(TreeNode root, int key) {

        if (root == null) {
            return root;
        }

        if (root.val > key) {//key在左子树
            root.left = deleteNode(root.left, key);
            return root;
        } else if (root.val < key) {//key在右子树
            root.right = deleteNode(root.right, key);
            return root;
        } else {//相等
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            TreeNode min = min(root.right);
            TreeNode successor = removeMin(root.right);

            min.left = root.left;
            min.right = successor;
            root.left = root.right = null;
            return min;
        }
    }


    /**
     * 找到一个根节点的最小值
     */
    private TreeNode min(TreeNode root) {
        if (root == null || root.left == null)
            return root;
        return min(root.left);
    }

    /**
     * 删除最小值 并返回删除最小值后的根节点
     */
    private TreeNode removeMin(TreeNode root) {

        if (root == null || root.left == null) {//找到了最小值
            return root.right;
        }
        //这一步删除了最小节点
        root.left = removeMin(root.left);
        return root;
    }

    /**
     * 230. 二叉搜索树中第K小的元素
     * <p>
     * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
     * <p>
     * 说明：
     * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: root = [3,1,4,null,2], k = 1
     * 3
     * / \
     * 1   4
     * \
     * 2
     * 输出: 1
     * <p>
     * 示例 2:
     * <p>
     * 输入: root = [5,3,6,2,4,null,null,1], k = 3
     * 5
     * / \
     * 3   6
     * / \
     * 2   4
     * /
     * 1
     * 输出: 3
     * <p>
     * 进阶：
     * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
     * 思路 ：
     * 中序遍历思想
     * <p>
     * //TODO 解法二 分治法也是一个很好的思路
     * https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--48/
     */
    public static int kthSmallest(TreeNode root, int k) {
        if (root == null)
            return 0;
        List<Integer> list = new ArrayList<>();
        findK(root, list);
        return list.get(k - 1);
    }

    private static void findK(TreeNode root, List<Integer> vector) {

        if (root == null)
            return;

        findK(root.left, vector);
        vector.add(root.val);
        System.out.println(root.val);
        findK(root.right, vector);
    }


    /**
     * 131. 分割回文串
     * <p>
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     * <p>
     * 返回 s 所有可能的分割方案。
     * <p>
     * 示例:
     * <p>
     * 输入: "aab"
     * 输出:
     * [
     * ["aa","b"],
     * ["a","a","b"]
     * ]
     * <p>
     * https://leetcode-cn.com/problems/palindrome-partitioning/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-3-7/
     * <p>
     * //TODO 黄色笔记本上 这个可以参考上面 思路在本子上 回溯法 不太好理解
     */
    public static List<List<String>> partition(String s) {

        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0)
            return res;

        List<String> temp = new ArrayList<>();
        cutMatch(s, 0, len, res, temp);

        return res;

    }

    /**
     * 思路就是依次切割判断 已经切割的是否是回文串
     * 如果是 那么继续向下切割 如果不是 跳过
     * 从start位置开始切割 如果左边的是回文串 那么继续向下切割
     * 如果不是 跳过 继续下一个切割 知道 切割s的最后一个 也就是start==len
     *
     * @param s     要访问的字符串
     * @param start 将要访问s中的开始下标
     * @param len   s的长度 固定不变
     * @param res   最后返回的结果
     */
    private static void cutMatch(String s, int start, int len,
                                 List<List<String>> res, List<String> temp) {

        //如果已经切割到最后一个字符串了 这里是如果到这里就说明找到了一条答案
        //如果不是当我们遍历的时候 如果不符合回文串的定义 我们直接越过
        if (start == len) {
            //这里要新创建一个 以为res存的是temp的引用 那么最后 temp是会回溯删除的
            //那么也就是 最后res会为null 所以我们要创建一个新的List
            //TODO 这也就解释了 为什么我们再list中添加了一个元素 后又要删除 如果不删除我们的list只创建了一个
            // 那么这个list会存入错乱 最后的结果会特别长
            List<String> list = new ArrayList<>(temp);
            res.add(list);
            return;
        }


        //从start开始向下树形的下面寻找 相当于越过 start之前的位置
        for (int i = start; i < len; i++) {

            //依次切割aab:  a->a,ab->aa,b->aab
            //substring[start,end)
            String p = s.substring(start, i + 1);
            //左边的不是回文串 右边也就不用判断了
            if (!isPalindrome(p)) {
                continue;
            }
            //如果是回文串
            //1.先添加入 temp集合中
            temp.add(p);
            cutMatch(s, i + 1, len, res, temp);
            //删除上边添加的 回溯还原 temp和他的同级相同在向下面寻找
            temp.remove(temp.size() - 1);
        }

    }

    public static boolean isPalindrome(String p) {
        int start = 0;
        int len = p.length() - 1;
        while (start < len) {
            if (p.charAt(start) != p.charAt(len)) {
                return false;
            }
            start++;
            len--;
        }

        return true;
    }

    public static void main(String[] args) {

//        boolean b = isPalindrome(new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(1, null)))));
//        System.out.println(b);
//        kthSmallest(new TreeNode(5, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7, new TreeNode(6), new TreeNode(8))), 3);

        System.out.println(partition("acca"));


    }
}
