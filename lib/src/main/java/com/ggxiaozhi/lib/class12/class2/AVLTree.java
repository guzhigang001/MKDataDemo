package com.ggxiaozhi.lib.class12.class2;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/3/25 21:21
 * @UpdateUser:
 * @UpdateDate: 2020/3/25 21:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AVLTree<K extends Comparable<K>, V> {
    private class Node {

        private K key;
        private V value;
        public Node left, right;
        //每个节点维护自己的高度
        private int height;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            //根据二分搜索树的性质 新创建的节点 一定在叶子节点上 那么他的高度是1
            this.height = 1;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            //根据二分搜索树的性质 新创建的节点 一定在叶子节点上 那么他的高度是1
            this.height = 1;
        }

        public Node() {
            this.key = null;
            this.value = null;
            this.left = null;
            this.right = null;
            //根据二分搜索树的性质 新创建的节点 一定在叶子节点上 那么他的高度是1
            this.height = 1;

        }

        @Override
        public String toString() {
            return String.format("[key = %s,value = %s]", key.toString(), value.toString());
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public void add(K key, V value) {

        root = add(root, key, value);
    }

    /**
     * 求出指定节点的height
     *
     * @param node
     * @return
     */
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    /**
     * 计算指定节点的平衡因子
     * 平衡因子=node节点左子树-node节点右子树
     * <p>
     * 或者
     * <p>
     * 平衡因子=node节点右子树-node节点左子树
     *
     * 判断一个树是否是平衡二叉树 就是判断Math.abs(balancefactor) > 1 平衡因子的绝对值是否大于1
     * @param node
     * @return
     */
    private int getBalancefactor(Node node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
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
            //新创建的节点的高度维护 我们已经在构造函数中维护了 所以这里的逻辑没问题
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

        //经过上面的递归我们递归的向上返回我们添加新节点后的根节点
        //所以在返回之前我们要维护node的高度
        //每个节点的高度是由左右孩子最高的高度+1得到的
        // 这里的1可以理解代表节点本身高度1 +左右节点的高度
        //如果是修改的值的话 本身左右子树的高度没有变化 那么根据每个节点的计算公式 root节点的高度就会保持原高度不变
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        int balanceFactor = getBalancefactor(root);
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("unbalanced : " + balanceFactor);
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
        }

        System.out.println();
    }
}
