package com.ggxiaozhi.lib.class6.class11;

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


    public E minmum() {
        if (size == 0) {
            throw new IllegalArgumentException("is Empty");
        }

        return minmum(root).e;
    }

    private Node minmum(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return minmum(node.left);
        }
    }

    public E maxmum() {
        if (size == 0) {
            throw new IllegalArgumentException("is Empty");
        }

        return maxmum(root).e;
    }

    private Node maxmum(Node node) {
        if (node.right == null) {
            return node;
        } else {
            return maxmum(node.right);
        }
    }

    public E removeMin() {

        E e = minmum();

        root = removeMin(root);

        return e;

    }

    /**
     * --A
     * /   \
     * B     D
     * \
     * E
     * <p>
     * 递归要先确定终止的条件，  删除最小节点（B） 如果最小节点的右子树为null
     * 那么当前节点可以直接删除 如果右节点有值（E） 那么删除最小的右节点（B）后
     * 要将最小右节点（B）的左节点（E）接到原最小左节点B的位置
     * <p>
     * 所以终止条件是左节点为null 返回右节点
     * 然后上一节点接住E
     *
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null) {//

            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }

        //此时的node再递归中的方法栈中为上一节点 那么要用上一节点接住返回的最小节点的作节点
        node.left = removeMin(node.left);
        return node;
    }


    public E removeMax() {

        E e = maxmum();

        root = removeMax(root);

        return e;

    }

    /**
     * 同最小值
     *
     * @param root
     * @return
     */
    private Node removeMax(Node root) {

        if (root.right == null) {
            Node left = root.left;
            root.left = null;
            size--;
            return left;
        }

        root.right = removeMax(root.right);
        return root;
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
