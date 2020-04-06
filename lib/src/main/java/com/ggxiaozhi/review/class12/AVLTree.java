package com.ggxiaozhi.review.class12;

import com.ggxiaozhi.lib.class12.class2.FileOperation;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/4/6 18:33
 * @UpdateUser:
 * @UpdateDate: 2020/4/6 18:33
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressWarnings("SuspiciousNameCombination")
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;

        public V value;

        public Node left, right;

        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            height = 1;
        }

        @Override
        public String toString() {
            return "left: " + left + " right: " + right;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    // 判断该二叉树是否是一棵二分搜索树
    public boolean isBST() {

        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++)
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {

        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(K key, V value) {


        root = add(root, key, value);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 判断以Node为根的二叉树是否是一棵平衡二叉树，递归算法
    private boolean isBalanced(Node node) {

        if (node == null)
            return true;
        int balanceFactor = getBalanceFactor(node);

        if (Math.abs(balanceFactor) > 1) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);

    }

    //递归添加节点返回添加后的根节点root
    private Node add(Node root, K key, V value) {
        if (root == null) {
            Node node = new Node(key, value);
            size++;
            return node;
        }

        if (root.key.compareTo(key) > 0) {//左子树
            root.left = add(root.left, key, value);
        } else if (root.key.compareTo(key) < 0) {
            root.right = add(root.right, key, value);
        } else {
            root.value = value;
        }

        //由于添加了元素 此时的root节点 高度变化了
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        int balanceFactor = getBalanceFactor(root);
//        if (Math.abs(balanceFactor) > 1) {
//            System.out.println("unBalanceFactor: " + balanceFactor);
//        }

        if (balanceFactor > 1 && getBalanceFactor(root.left) >= 0) {
            root = rightRotate(root);
        }

        return root;
    }

    /**
     * 旋转root节点 返回旋转后的根节点
     *
     * @param
     * @return
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right));
        x.height = Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }


    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("key null value");
        }
        node.value = value;
    }

    public V get(K key) {

        return getNode(root, key).value;
    }

    public boolean contains(K key) {
        Node node = getNode(root, key);
        return node != null;
    }

    private Node getNode(Node root, K key) {
        if (root == null)
            return null;
        if (key.compareTo(root.key) > 0) {
            return getNode(root.right, key);
        } else if (key.compareTo(root.key) < 0) {
            return getNode(root.left, key);
        } else {
            return root;
        }

    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());
        }

        System.out.println();
    }
}
