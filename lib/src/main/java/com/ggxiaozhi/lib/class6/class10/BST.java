package com.ggxiaozhi.lib.class6.class10;


import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;

        }

        public Node() {
            this.e = null;
            left = null;
            right = null;

        }

        @Override
        public String toString() {
            return e.toString();
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

    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//            size++;
//        } else {
//            add(root, e);
//        }

        //优化后
        root = addUp(root, e);
    }

    private void add(Node node, E e) {

        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {// e比当前节点的要大 向右子树查找
            node.right = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        }

        if (e.compareTo(node.e) > 0) {
            add(node.right, e);
        } else {
            add(node.left, e);
        }
    }


    /**
     * Add 简化版本
     *
     * @param node
     * @param e
     * @return
     */
    private Node addUp(Node node, E e) {

        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) > 0) {
            node.right = addUp(node.right, e);
        } else if (e.compareTo(node.e) < 0) {
            node.left = addUp(node.left, e);
        }

        return node;
    }

    /**
     * 查询一个元素是否在二分搜索树中
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {

        return contains(root, e);

    }

    private boolean contains(Node node, E e) {

        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) > 0) {//向右子树查找
            return contains(node.right, e);
        } else if (e.compareTo(node.e) == 0) {
            return true;
        } else {//向左子树查找
            return contains(node.left, e);
        }
    }


    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {

        if (node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }


    public void inOrder() {

        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder() {

        postOrder(root);
    }


    public void levelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> q = new LinkedTransferQueue<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.left != null) {
                q.offer(node.left);
            }

            if (node.right!=null){
                q.offer(node.right);
            }

            System.out.println(node.e);
        }
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
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

    public int maxDepth() {
        return outputDepthX(root);
    }

    public int outputDepthX(Node node) {

        if (node == null) {
            return 0;
        }
        int depth;
        int left = outputDepthX(node.left);
        int right = outputDepthX(node.right);

        if (left > right) {
            depth = left + 1;

        } else {
            depth = right + 1;
        }
        return depth;
    }

    private String output(int depth) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("--");
        }

        return builder.toString();
    }
}
