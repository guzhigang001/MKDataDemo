package com.ggxiaozhi.lib.class7;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/3/18 21:13
 * @UpdateUser:
 * @UpdateDate: 2020/3/18 21:13
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BST<E extends Comparable<E>> {


    private class Node {

        public E e;

        private Node left;

        private Node right;


        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
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

        //非递归
        if (root == null) {
            size++;
            root = new Node(e);
        } else {
            Node cur = root;
            while (true) {
                if (cur.e.compareTo(e) < 0) {// 在右子树上
                    if (cur.right == null) {
                        cur.right = new Node(e);
                        size++;
                        return;
                    } else {
                        cur = cur.right;
                    }
                } else if (cur.e.compareTo(e) > 0) {//再左子树上
                    if (cur.left == null) {
                        cur.left = new Node(e);
                        size++;
                        return;
                    } else {
                        cur = cur.left;
                    }

                } else {
                    return;
                }
            }
        }


        //递归写法
//        if (root == null) {
//            root = new Node(e);
//        }else {
//            add(root, e);
//        }

    }

    public boolean contains(E e) {

        Node cur = root;
        if (cur == null)
            return false;

        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            } else if (cur.e.compareTo(e) > 0) {//在左子树上
                cur = cur.left;
            } else {//进入这个条件一定是在右子树上
                cur = cur.right;
            }
        }
        //如果在循环中没有返回 那么一定是没有找到 那么这里返回false
        return false;

        //递归实现
//        return contains(root, e);
    }

    private boolean contains(Node root, E e) {

        if (root == null) {
            return false;
        }

        if (root.e.equals(e)) {
            return true;
        } else if (root.e.compareTo(e) > 0) {//在左子树上
            return contains(root.left, e);
        } else {// (root.e.compareTo(e) < 0) 一定是在右子树上
            return contains(root.right, e);
        }
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (node.e.compareTo(e) < 0) {// 在右子树上
            node.right = add(node.right, e);
        } else if (root.e.compareTo(e) > 0) {//再左子树上
            node.left = add(node.left, e);
        }
        return node;
    }


    /**
     * 前序遍历
     */
    public void preOrder() {

        preOrder(root);
    }

    /**
     * 前序遍历的非递归遍历
     */
    public void preOrderUn() {

        if (root == null) {
            return;
        }


        Stack<Node> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            System.out.println(node.e);
            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }

        }

    }

    private void preOrder(Node root) {

        if (root == null) {
            return;
        }

        System.out.println(root.e);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * 递归中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        System.out.println(root.e);
        inOrder(root.right);
    }

    public void inOrderUn() {
        inOrderUn(root);
    }

    private void inOrderUn(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        Node cur = root;
        while (!stack.isEmpty()) {

            while (cur.left != null) {
                stack.push(cur.left);
                cur = cur.left;
            }

            Node pop = stack.pop();
            System.out.println(pop.e);
            if (pop.right != null) {
                stack.push(pop.right);
                cur = pop.right;
            }
        }
    }

    /**
     * 递归后序遍历
     */
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


    public void postOrderUn() {
        postOrderUn(root);
    }

    /**
     * https://blog.csdn.net/qq_39445165/article/details/90749343?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
     * <p>
     * 非递归后序遍历
     *
     * @param root
     */
    private void postOrderUn(Node root) {
        Stack<Node> src = new Stack<>();
        Stack<Node> pos = new Stack<>();

        src.push(root);

        while (!src.isEmpty()) {
            Node pop = src.pop();
            pos.push(pop);

            if (pop.left != null) {
                src.push(pop.left);
            }

            if (pop.right != null) {
                src.push(pop.right);
            }
        }

        while (!pos.isEmpty()) {
            System.out.println(pos.pop().e);
        }
    }

    /**
     * 查找二分搜索树的最小值
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("空树");
        }

        return minimum(root).e;
    }

    /**
     * 循环查找
     *
     * @param root
     * @return
     */
    private Node minimum(Node root) {
        if (root.left == null) {
            return root;
        }
        return minimum(root.left);
    }

    /**
     * 查找最大值
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("空树");
        }
        return maximum(root).e;
    }

    private Node maximum(Node root) {
        if (root.right == null) {
            return root;
        }
        return maximum(root.right);
    }

    /**
     * 删除最小值
     */
    public E removeMin() {
        E e = minimum();
        removeMin(root);
        return e;
    }


    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     * <p>
     * TODO 删除最大节点逻辑相同
     */
    private Node removeMin(Node root) {
        if (root.left == null) {//进入这个分支说明找到了最小节点 就是root
            //这时候root.right可能为空 但是不影响逻辑
            Node rightNode = root.right;
            //将带删除的root节点 right的这指向 为null
            root.right = null;
            //此时递归进入 是已root为根节点 那么 新的根节点就是rightNode;
            size--;
            return rightNode;
        }

        //递归最后 返回的是此时根节点的左子树的最小值已经删除掉了 那么此时根节点的左子树就是返回来的新的节点
        //如果是空的话 也是正确的
        root.left = removeMin(root.left);

        //最后返回递归结束的根节点  就是删除最小值的根节点
        return root;
    }

    public E removeMax() {
        E e = maximum();

        removeMax(root);
        return e;
    }

    private Node removeMax(Node root) {
        if (root.right == null) {
            Node leftNode = root.left;
            root.left = null;
            size--;
            return leftNode;
        }

        root.right = removeMax(root.right);
        return root;
    }


    /**
     * 删除任意元素
     */
    public void remove(E e) {
        remove(root, e);
    }

    /**
     * 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
     * 返回删除节点后新的二分搜索树的根
     */
    private Node remove(Node node, E e) {

        if (node == null) {//循环到最后都没有找到e元素
            return null;
        }

        if (e.compareTo(node.e) > 0) {//元素在右子树上
            //这个方法本身是删除以root为节点后的根据节点 因为传入的是右子树
            // 那么此时节点的右子树 应该接上删除节点后的根节点
            node.right = remove(node.right, e);
            //然后返回这个根节点
            return node;
        } else if (e.compareTo(node.e) < 0) {//元素在左子树上
            //同上
            node.left = remove(node.left, e);
            //然后返回这个根节点
            return node;
        } else {//e==node.e  找到了这个节点 那么判断删除节点是否有子节点

            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                //返回去 这是的rightNode因为在右节点上 那么比e要大 所以进入(e.compareTo(node.e) < 0)这个分支
                //然后用node.left接上 再一直递归返回
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                //返回去 这是的rightNode因为在右节点上 那么比e要大 所以进入(e.compareTo(node.e) < 0)这个分支
                //然后用node.left接上 再一直递归返回
                return leftNode;
            }

            //都没有return返回 走到这里 说明 左右子树都不是空

            //最小节点存起来
            Node successor = minimum(node);
            successor.right = removeMin(node.right); //查找右子树的最小节点  这里也可以换成查找左子树的最大节点
            successor.left = node.left;

            node.left = node.right = null;
            return successor;

        }
    }

    public int maxDepth(Node root, int left, int right) {

        if (root == null) {
            return 0;
        }

        left = maxDepth(root.left, left + 1, right);
        right = maxDepth(root.right, left, right + 1);

        return Math.max(left + 1, right + 1);
    }

    /**
     * 二分搜索树的层序遍历
     */
    public void levelOrder() {

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node cur = q.remove();
            System.out.println(cur.e);

            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
    }

    public int maxDepth() {
        return maxDepth(root, 0, 0);
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
//        int[] arr = {12, 3, 6, 9, 5, 2, 8, 15};
        int[] arr = {5, 3, 6, 4, 2};
//        int[] arr = {9, 5, 3, 7, 6, 8};
//        int[] arr = {6, 12, 19, 18, 17, 21, 20};

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }
        System.out.println(bst);
        System.out.println("size========" + bst.size);

        System.out.println(bst.contains(0));

        System.out.println("==========递归前序遍历");
        bst.preOrder();
        System.out.println("==========非递归前序遍历");
        bst.preOrderUn();
        System.out.println("==========");

        System.out.println("==========递归中序遍历");
        bst.inOrder();
        System.out.println("==========非递归中序遍历");
        bst.inOrderUn();
        System.out.println("==========");

        System.out.println("==========递归后序遍历");
        bst.postOrder();
        System.out.println("==========非递归后序遍历");
        bst.postOrderUn();
        System.out.println("==========");


        System.out.println("Depth======" + bst.maxDepth());

        System.out.println("==========查找最小值");
        System.out.println(bst.minimum());

        System.out.println("==========查找最大值");
        System.out.println(bst.maximum());
        System.out.println("==========删除最小节点");
        bst.removeMin();
        bst.inOrder();
        System.out.println("==========删除最大节点");
        bst.removeMax();
        bst.inOrder();


        System.out.println("==========删除任意节点");
        bst.inOrder();
        System.out.println("=========");
        bst.remove(6);
        bst.inOrder();
    }
}
