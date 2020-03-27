package com.ggxiaozhi.lib.class12.class2;


import com.ggxiaozhi.lib.class7.class7_9.FileOperation;
import com.ggxiaozhi.lib.class7.class7_9.Map;

import java.util.ArrayList;

public class BST<K extends Comparable<K>, V> {
    private class Node {

        private K key;
        private V value;
        public Node left, right;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node() {
            this.key = null;
            this.value = null;
            this.left = null;
            this.right = null;

        }

        @Override
        public String toString() {
            return String.format("[key = %s,value = %s]", key.toString(), value.toString());
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public void add(K key, V value) {

        root = add(root, key, value);
    }

    /**
     * 递归 添加元素  如果找到已经添加元素 就将value修改
     *
     * @param root
     * @param key
     * @param value
     * @return 返回添加元素后的根节点
     */
    private Node add(Node root, K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            size++;
            return root;
        }

        if (key.compareTo(root.key) > 0) {//右子树
            root.right = add(root.right, key, value);
        } else if (key.compareTo(root.key) < 0) {
            root.left = add(root.left, key, value);
        } else {
            root.value = value;
        }

        return root;
    }


    /**
     * 删除 BST中的任意元素 在删除后可以选择右子树的最小值 也可以选择左子树的最大值
     *
     * @param key
     * @return
     */
    public V remove(K key) {

        Node node = getNode(root, key);
        if (node != null) {
            root = removeElement(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 返回最小值的那个节点
     *
     * @param root
     * @return
     */
    private Node minimum(Node root) {

        if (root.left == null)
            return root;
        return minimum(root.left);
    }

    /**
     * 删除最小值 返回新的BST的根节点
     *
     * @param root
     * @return
     */
    private Node removeMin(Node root) {
        if (root.left == null) {
            return root;
        }

        Node node = removeMin(root.left);
        if (node.right != null) {
            root.left = node.right;
            node.right = null;
            size--;
        }

        return root;
    }


    /**
     * 删除指定的任意元素
     *
     * @return 返回删除元素后新的bst的根
     */
    public Node removeElement(Node root, K key) {

        if (root == null) {
            return null;
        }

        if (key.compareTo(root.key) > 0) {//在右子树上
            root.right = removeElement(root.right, key);
            return root;
        } else if (key.compareTo(root.key) < 0) {//在左子树上
            root.left = removeElement(root.left, key);
            return root;
        } else {//相等
            if (root.left == null) {
                Node rightNode = root.right;
                root.right = null;
                size--;
                return rightNode;
            }
            if (root.right == null) {
                Node leftNode = root.left;
                root.left = null;
                size--;
                return leftNode;
            }

            Node successor = minimum(root.right);
            successor.right = removeMin(root.right);
            successor.left = root.left;

            root.left = root.right = null;
            return successor;
        }
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("key 不存在");
        }

        node.value = newValue;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException("key没有对应的value");
        }
        return node.value;
    }

    public boolean contains(K key) {
        Node node = getNode(this.root, key);
        return node != null;
    }

    private Node getNode(Node node, K key) {

        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) > 0) {//在右子树上
            return getNode(node.right, key);
        } else if (key.compareTo(node.key) < 0) {//在左子树上
            return getNode(node.left, key);
        } else {//相等

            return node;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
//        Map<Integer, String> map = new BSTMap<>();
//        for (int i = 0; i < 5; i++) {
//            map.add(i, "value" + i);
//        }
//        System.out.println(map.getSize());
//
//        String remove = map.remove(2);
//        System.out.println(remove);
//        System.out.println(map.getSize());
//        System.out.println("Pride and Prejudice");
//


        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BST<String, Integer> map = new BST<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }
}
