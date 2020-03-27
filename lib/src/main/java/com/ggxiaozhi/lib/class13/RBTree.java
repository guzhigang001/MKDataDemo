package com.ggxiaozhi.lib.class13;

import java.util.ArrayList;

/**
 * Create by ggxz
 * 2020/3/27
 * description:红黑树
 */
@SuppressWarnings("SuspiciousNameCombination")
public class RBTree<K extends Comparable<K>, V> {

    //新创建的节点 默认是红色的
    //TODO 默认是红色的原因是因为 在红黑树中 新添加的节点经过2-3树的转化 一定是红色的
    private static final boolean RED = true;
    private static final boolean BLACK = true;

    private class Node {

        private K key;
        private V value;
        public Node left, right;
        private boolean color;

        public Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            color = RED;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            color = RED;
        }

        public Node() {
            this.key = null;
            this.value = null;
            this.left = null;
            this.right = null;
            color = RED;

        }

        @Override
        public String toString() {
            return String.format("[key = %s,value = %s]", key.toString(), value.toString());
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    private boolean isRed(Node node) {
        if (node == null)
            return false;
        return node.color;
    }

    /**
     * 将传入的节点的右子树向左旋转
     * 这里的左旋转与AVL中的左旋转原理相同
     * 但是我们要注意 旋转后我们要维护node节点和right节点的color
     * <p>
     * TODO 这里颜色维护的原则是 元node是什么颜色旋转后返回的根节点就是什么颜色 同时旋转后的元node节点为红色
     * <p>
     * // 对节点y进行向左旋转操作，返回旋转后新的根节点x
     * //    y                             x
     * //  /  \                          /   \
     * // T1   x      向左旋转 (y)       y     T3
     * //     / \   - - - - - - - ->   / \
     * //   T2  T3                    T1  T2
     * <p>
     * 如上 如果传入的节点是y黑色 那么旋转后x.color=y.color  同时y.color=RED 最后y红色 x黑色
     * 也有可能原来y红色 那么旋转后x.color=y.color  也就是x也是红色 那么x，y就是都是RED 破坏了红黑树的性质：
     * 1.根节点是黑色
     * 2.如果一个节点是黑色，那么他的所有孩子节点是黑色
     * <p>
     * TODO 但是注意 在这个方法中 我们只是单纯的进行了左旋转，并不维护红黑树的性质
     * 因为在向上递归中我们返回去最后会递归上去 在添加完后如果根节点是红色的我们会
     * 在add方法中调用 root.color=BLACK;
     *
     * @param y 要进行左旋转的根节点
     * @return 返回左旋转后的根节点
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        y.right = x.left;
        x.left = y;

        x.color = y.color;
        y.color = RED;
        return x;
    }

    public void add(K key, V value) {

        root = add(root, key, value);
        root.color = BLACK;
    }

    /**
     * //   42--node    37--x
     * //
     * //                                                                             37.color=42.color
     * //          42(B)y   添加12(R)              42By      右翻转             37Rx       42.color=RED            37B
     * //        /         ------------>        /           ----------->      /    \     ------------->          /   \
     * //      37(R)x                          37Rx                        12Rz    42By     翻转颜色             12R   42R
     * //                                    /
     * //                                   12Rz
     * //                                  (这个是传入node)
     *
     * @param y
     * @return
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T1 = x.right;
        y.left = T1;
        x.right = y;

        x.color = y.color;
        y.color = RED;

        flipColors(x);
        return x;

    }

    /**
     * 翻转颜色
     * <p>
     * //
     * //
     * //          42(B)   添加66(R)              42B      翻转颜色           48R
     * //        /         ------------>        /   \    ----------->      /    \
     * //      37(R)                          37R   66R                  37R    66R
     * //
     * 因为节点的位置没有发生变化
     * 所以不用返回
     *
     * @param node 传入的节点就已经保证了是上面的形状和颜色  调用处判断
     */
    private void flipColors(Node node) {

        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;

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
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
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
