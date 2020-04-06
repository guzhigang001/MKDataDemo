package com.ggxiaozhi.libreview.class6;

import com.ggxiaozhi.review.class4.LinkedListQueue;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

import javafx.util.Pair;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/4/4 14:29
 * @UpdateUser:
 * @UpdateDate: 2020/4/4 14:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;

        public Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "left: " + left + " right: " + right;
        }
    }

    private Node root;

    private int size;

    public BST() {

        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回BST的根节点
     *
     * @param e
     * @return
     */
    public void add(E e) {

        //递归写法
//        root = addR(e, root);

        addUR(e);
    }


    //递归 最后返回更节点
    private Node addR(E e, Node root) {
        if (root == null) {
            root = new Node(e);
            size++;
            return root;
        }

        if (e.compareTo(root.e) < 0) {//向左子树添加
            root.left = addR(e, root.left);
        } else if (e.compareTo(root.e) > 0) {//向右子树添加  注意 这里我们没有写== 所以我的BST 在相等的时候我们什么也不做
            root.right = addR(e, root.right);

        }

        return root;
    }


    public void addUR(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
            return;
        }

        Node temp = root;
        while (true) {
            if (e.compareTo(temp.e) < 0) {//左

                if (temp.left == null) {
                    temp.left = new Node(e);
                    size++;
                    return;
                } else {
                    temp = temp.left;
                }
            } else if (e.compareTo(temp.e) > 0) {
                if (temp.right == null) {
                    temp.right = new Node(e);
                    size++;
                    return;
                } else {
                    temp = temp.right;
                }
            } else {//相等什么都不做
                return;
            }
        }
    }


    public boolean contains(E e) {

        return contains(e, root);
    }

    private boolean contains(E e, Node root) {
        if (root == null) {
            return false;
        }

        if (e.compareTo(root.e) < 0) {
            return contains(e, root.left);
        } else if (e.compareTo(root.e) > 0) {
            return contains(e, root.right);
        } else {
            return true;
        }
    }

    private void preOrder() {
        preOrder(root);
    }

    public void preOrderUR() {

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.print(pop.e);
            if (pop.right != null) {
                stack.push(pop.right);
            }

            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }

    private void preOrder(Node root) {

        if (root == null)
            return;
        System.out.println(root.e);
        preOrder(root.left);
        preOrder(root.right);
    }

    public void inOrder() {

        inOrder(root);
    }

    public void inOrderUR() {
        Stack<Node> stack = new Stack<>();
        List<Node> list = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.peek();
            if (pop.left != null) {
                if (!list.contains(pop.left)) {
                    stack.push(pop.left);
                    continue;
                }

            }
            Node node = stack.pop();
            list.add(node);
            System.out.print(node.e);
            if (pop.right != null) {
                stack.push(pop.right);
            }
        }
    }

    private void inOrder(Node root) {

        if (root == null)
            return;
        inOrder(root.left);
        System.out.println(root.e);
        inOrder(root.right);
    }

    public void postOrder() {

        postOrder(root);
    }

    private void postOrder(Node root) {

        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.e);
    }

    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.e);
            if (poll.right != null) {
                queue.add(poll.right);
            }
            if (poll.left != null) {
                queue.add(poll.left);
            }
        }

    }

    public int maxDepth() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int h = 0;
        Map<Node, Integer> map = new Hashtable<>();
        map.put(root, 1);

        while (!queue.isEmpty()) {

            Node node = queue.poll();
            h = Math.max(h, map.get(node));
            if (node.right != null) {
                queue.add(node.right);
                map.put(node.right, h + 1);
            }

            if (node.left != null) {
                queue.add(node.left);
                map.put(node.left, h + 1);
            }
        }
        return h;
    }


    public E min() {
        if (size == 0)
            throw new IllegalArgumentException();
        return min(root).e;
    }

    private Node min(Node root) {
        if (root.left == null)
            return root;

        return min(root.left);
    }


    public E max() {
        if (size == 0)
            throw new IllegalArgumentException();
        return max(root).e;
    }

    private Node max(Node root) {
        if (root.right == null)
            return root;

        return max(root.right);
    }

    private E minUR() {
        if (root == null)
            return null;
        if (root.left == null) {
            return root.e;
        }

        Node temp = root;
        while (temp.left != null) {

            temp = temp.left;
        }
        return temp.e;
    }


    public E removeMin() {
        E min = min();

        root = removeMin(root);
        return min;
    }

    public E removeMax() {
        E max = max();

        root = removeMin(root);
        return max;
    }

    /**
     * 返回删除最小值 新的根
     *
     * @param root
     * @return
     */
    private Node removeMin(Node root) {

        if (root.left == null) {
            Node temp = root.right;

            root.right = null;
            size--;
            return temp;
        }

        root.left = removeMin(root.left);
        return root;
    }

    private Node removeMax(Node root) {

        if (root.right == null) {
            Node temp = root.left;

            root.left = null;
            size--;
            return temp;
        }

        root.right = removeMin(root.right);
        return root;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node root, E e) {

        if (root == null) {
            return null;
        }

        if (e.compareTo(root.e) < 0) {//左子树
            root.left = remove(root.left, e);
            return root;
        } else if (e.compareTo(root.e) > 0) {//右子树
            root.right = remove(root.right, e);
            return root;
        } else {//就是找到这个元素了
            if (root.left == null) {
                Node temp = root.right;
                root.right = null;
                size--;
                return temp;
            }
            if (root.right == null) {
                Node temp = root.left;
                root.left = null;
                size--;
                return temp;
            }


            Node successor = min(root.right);//右子树的最小值
            successor.right = removeMin(root.right);
            successor.left = root.left;

            root.left = root.right = null;
            return successor;

        }
    }

    public int maxDepthR() {

        return maxDepth(root, 0, 0);
    }

    private int maxDepth(Node root, int leftDepth, int rightDepth) {

        if (root == null) {
            return 0;
        }
        leftDepth = maxDepth(root.left, leftDepth + 1, rightDepth);
        rightDepth = maxDepth(root.right, leftDepth, rightDepth + 1);
        return Math.max(leftDepth + 1, rightDepth + 1);
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();

        outputDepth(root, 0, res);
        return res.toString();
    }

    private void outputDepth(Node node, int depth, StringBuilder res) {

        if (node == null) {
            String s = output(depth) + "null\n";
            res.append(s);
            return;
        }
        res.append(output(depth)).append(node.e).append("\n");
        outputDepth(node.left, depth + 1, res);
        outputDepth(node.right, depth + 1, res);
    }

    private String output(int depth) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("--");
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        int[] arr = {12, 3, 6, 9, 5, 2, 8, 15};
//        int[] arr = {5, 3, 6, 4, 2};
//        int[] arr = {9, 5, 3, 7, 6, 8};
//        int[] arr = {6, 12, 19, 18, 17, 21, 20};

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }

        System.out.println(bst.max());
        System.out.println("depth1===" + bst.maxDepth());
        System.out.println("depth2===" + bst.maxDepthR());
        bst.preOrder();
        System.out.println();
        bst.inOrder();
        bst.preOrderUR();
        System.out.print("中序===");
        bst.inOrderUR();
        System.out.println();
        bst.postOrder();
        System.out.println();
        System.out.println(bst);
        System.out.println(bst.getSize());
        System.out.println(bst.contains(0));
        System.out.println(bst.contains(12));
        System.out.println(bst.contains(15));
        System.out.println(bst.contains(5));
        System.out.println(bst.contains(-1));

        //------------------------------------------------------------

        Map<Integer, Boolean> map = new HashMap<>();
        System.out.println(map.get(1) == null);


        Random random = new Random();

        int n = 1000;

        // test removeMin
        for (int i = 0; i < n; i++)
            bst.add(random.nextInt(10000));

        ArrayList<Integer> nums = new ArrayList<>();
        while (!bst.isEmpty())
            nums.add(bst.removeMin());

        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++)
            if (nums.get(i - 1) > nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMin test completed.");


        // test removeMax
        for (int i = 0; i < n; i++)
            bst.add(random.nextInt(10000));

        nums = new ArrayList<>();
        while (!bst.isEmpty())
            nums.add(bst.removeMax());

        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++)
            if (nums.get(i - 1) < nums.get(i))
                throw new IllegalArgumentException("Error!");
        System.out.println("removeMax test completed.");

        //------------------------------------------------------------
//        BST<Integer> bst = new BST<>();
//        Random random = new Random();
//
//        int n = 10000;
//
//        for(int i = 0 ; i < n ; i ++)
//            bst.add(random.nextInt(n));
//
//        // 注意, 由于随机生成的数据有重复, 所以bst中的数据数量大概率是小于n的
//
//        // order数组中存放[0...n)的所有元素
//        Integer[] order = new Integer[n];
//        for( int i = 0 ; i < n ; i ++ )
//            order[i] = i;
//        // 打乱order数组的顺序
//        shuffle(order);
//
//        // 乱序删除[0...n)范围里的所有元素
//        for( int i = 0 ; i < n ; i ++ )
//            if(bst.contains(order[i])){
//                bst.remove(order[i]);
//                System.out.println("After remove " + order[i] + ", size = " + bst.getSize() );
//            }
//
//        // 最终整个二分搜索树应该为空
//        System.out.println(bst.getSize());
//
//
//    }
//    // 打乱数组顺序
//    private static void shuffle(Object[] arr){
//
//        for(int i = arr.length - 1 ; i >= 0 ; i --){
//            int pos = (int) (Math.random() * (i + 1));
//            Object t = arr[pos];
//            arr[pos] = arr[i];
//            arr[i] = t;
//        }
    }
}
