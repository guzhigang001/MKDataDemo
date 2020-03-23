package com.ggxiaozhi.lib.class6.class6_9;


import java.util.Stack;

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

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 利用栈结构来非递归前序遍历
     */
    void printByStackPre() {

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
//            if (cur.right != null && cur.left != null) {
//                stack.push(cur.right);
//                stack.push(cur.left);
//            }else if (cur.right != null) {
//                stack.push(cur.right);
//            }else if (cur.left != null){
//                stack.push(cur.left);
//            }

            if (cur.right != null) {
                stack.push(cur.right);
            }

            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }


    public int maxDepth() {

        if (root == null) {
            return 0;
        }
        Node cur;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        int depth = 1;
        while (!stack.isEmpty()) {

            depth = Math.max(depth, stack.size());

            cur = stack.pop();
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return depth;
//        if (root == null) {
//            return 0;
//        }
//        Stack<Node> stack = new Stack<>();
//        stack.push(root);
//        Node lastVisit = root;
//        Node top;
//        int res = 0;
//        while (!stack.isEmpty()) {
//            res = Math.max(res, stack.size());
//            top = stack.peek();
//            if (top.left != null && lastVisit != top.left && lastVisit != top.right) {
//                stack.push(top.left);
//            } else if (top.right != null && lastVisit != top.right) {
//                stack.push(top.right);
//            } else {
//                lastVisit = stack.pop();
//            }
//        }
//        return res;

//        return maxDepth(root);
    }

    public int maxDepthX() {
        return maxDepthX(root);
    }


    private String pr() {
        return "--";
    }

    private int maxDepthX(Node node) {

        if (node == null)
            return 0;
        else {
            int depth = Math.max(maxDepthX(node.left), maxDepthX(node.right)) + 1;
            return depth;
        }
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
}
