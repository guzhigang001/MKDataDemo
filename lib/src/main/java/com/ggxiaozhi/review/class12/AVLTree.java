package com.ggxiaozhi.review.class12;

import com.ggxiaozhi.lib.class12.class2.FileOperation;
import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/4/6 18:33
 * @UpdateUser:
 * @UpdateDate: 2020/4/6 18:33
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressWarnings({"SuspiciousNameCombination", "UnnecessaryLocalVariable"})
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

//        if (root == null)//空节点也是BST
//            throw new IllegalArgumentException("node is null");
        List<K> list = new ArrayList<>();

        inOrder(root, list);

//        if (list.size() > 1) {//如果node不为空 那么至少size=1 1个节点也是BST
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
//        }

        return true;
    }

    private void inOrder(Node node, List<K> keys) {

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

    /**
     * 判断这个二叉树是否是平衡二叉树
     *
     * @return
     */
    public boolean isBalance() {
        return isBalance(root);
    }

    private boolean isBalance(Node node) {
        if (node == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(node);

        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalance(node.left) && isBalance(node.right);
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


        //LL
        if (balanceFactor > 1 && getBalanceFactor(root.left) >= 0) {
            return rightRotate(root);
        }

        //RR
        if (balanceFactor < -1 && getBalanceFactor(root.right) <= 0) {
            return leftRotate(root);
        }

        //LR
        if (balanceFactor > 1 && getBalanceFactor(root.left) < 0) {
            root.left = leftRotate(root.left);

            return rightRotate(root);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(root.right) > 0) {
            root.right = rightRotate(root.right);
            root = leftRotate(root);
        }

        return root;
    }

    public Node minimum(Node node) {
        if (node == null || node.left == null) return node;

        return minimum(node.left);

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
            root = remove(root, key);
            return node.value;
        }
        return null;
    }


    //返回删除指定key节点后的根节点
    public Node remove(Node node, K key) {

        if (node == null)
            return null;

        Node retNode;
        if (node.key.compareTo(key) > 0) {//左子树

            node.left = remove(node.left, key);
            retNode = node;
        } else if (node.key.compareTo(key) < 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {//相等

            if (node.left == null) {

                Node right = node.right;
                node.right = null;
                size--;
                retNode = right;
            } else if (node.right == null) {

                Node left = node.left;
                node.left = null;
                size--;
                retNode = left;
            } else {//左右子树都不为空

                Node successor = minimum(node.right);
                Node removeRoot = remove(node.right, successor.key);
                successor.right = removeRoot;
                successor.left = node.left;
                node.left = node.right = null;

                retNode = successor;

            }
        }

        if (retNode == null) {//删除的是叶子节点 下面的代码就不用维护
            return null;
        }
        //由于添加了元素 此时的root节点 高度变化了
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        int balanceFactor = getBalanceFactor(retNode);


        //LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        //RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        //LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);

            return rightRotate(retNode);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(root.right) > 0) {
            retNode.right = rightRotate(retNode.right);
           return leftRotate(retNode);
        }

        return retNode;
    }

    /**
     * 左旋转
     *
     * @param y
     * @return
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;
        x.left = y;
        y.right = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right))+1;
        return x;
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

        y.height = Math.max(getHeight(y.left), getHeight(y.right))+1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right))+1;

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
            System.out.println("is Balanced : " + map.isBalance());

            for(String word: words){
                map.remove(word);
                if(!map.isBST() || !map.isBalance())
                    throw new RuntimeException();
            }
        }

        System.out.println();
    }
}
