package com.ggxiaozhi.lib.class12.class2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/3/25 21:21
 * @UpdateUser:
 * @UpdateDate: 2020/3/25 21:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * <p>
 * 首先 在二分搜索树中
 * <p>
 * 刚开始是平衡的 比如
 * ---A
 * 一个节点或者null都是平衡二叉树， 当时下面这种情况
 * TODO 这个情况是我们考虑的是往左子树去添加 同时最后是通过右旋转去达到平衡 右子树添加 使用左旋转达到平衡原理相同
 * -----A
 * ----/
 * ---B
 * --/
 * -C
 * <p>
 * -----A
 * ----/-\
 * ---B---C
 * --/-\
 * -C---F
 * /
 * E
 * 那么A节点就是不平衡的 但是当添加B 节点的时候是平衡的
 * 也就是说 导致二分搜索树不平衡的原因有2点：
 * 1. 一定是出现了添加操作
 * 2. 出现不平衡的位置 一定是出现不平衡的第一个节点的左侧的左侧(这里是A节点出现不平衡 平衡因子为2 是由于添加了C导致的)
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
     * <p>
     * 判断一个树是否是平衡二叉树 就是判断Math.abs(balanceFactor) > 1 平衡因子的绝对值是否大于1
     *
     * @param node
     * @return 这里返回的可能是正数 也可能是0 也可能是负数
     * 正数 说明 左子树的高度>0 且左子树比右子树高度要高
     * 0 说明 左右子树高度相等 >=0
     * 负数 说明 右子树的高度>0 且右子树比左子树高度要高
     */
    private int getBalanceFactor(Node node) {
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
        // 这里的1可以理解 代表节点本身高度1 +左右节点的高度
        //如果是修改的值的话 本身左右子树的高度没有变化 那么根据每个节点的计算公式 root节点的高度就会保持原高度不变
        root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

        int balanceFactor = getBalanceFactor(root);
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("unbalanced : " + balanceFactor);
        }

        //平衡二叉树 代码要写在这个位置
        //因为 在递归中 我们是下面的 return root; 一层一层向上返回
        //我们需要在向上返回值之前判断平衡因子 是否会出现添加节点后导致二分搜索树不平衡
        //如果出现不平衡 就要在这里判断后解决

        /**
         * 我们要清楚这个条件的含义
         * 首先 balanceFactor > 1 说明这个节点的平衡因子破环了 出现了不平衡
         * 根据getBalanceFactor() 方法的含义 说明左子树的高度比右子树高 那么破坏的地方一定在左子树
         * getBalanceFactor(root.left) >= 0 是判断这个节点的平衡因子被打破是左子树打破的还是右子树打破的
         *
         * 这里的getBalanceFactor(root.left) >= 0 其实可以写成>1 因为在添加的时候是在节点左边的左边
         * 那么节点嘴边的BalanceFactor值一定大于0  但是如果remove的时候会出现=0的情况 这里是为了统一代码
         * 如果remove方法用到这里就会有问题 加上也没出出错
         * 集体参考 https://coding.imooc.com/learn/questiondetail/59846.html
         */
        if (balanceFactor > 1 && getBalanceFactor(root.left) >= 0) {
            return rightRotate(root);
        }
        /**
         * 首先 balanceFactor < -1 说明右子树的高度比左子树高 那么破坏的地方一定在右子树
         * 其他逻辑与上面相同
         */

        if (balanceFactor < -1 && getBalanceFactor(root.right) <= 0) {

        }

        return root;
    }


    /**
     * 原来的结构满足二分搜索树的性质：T1<z<T2<x<T3<y<T4
     * <p>
     * 向右旋转y：
     * <p>
     * -              y                  x
     * -            / \               /    \
     * -           x   T4            z      y
     * -         / \               /  \    / \
     * -        z  T3       ----->T1  T2  T3  T4
     * -       / \
     * -      T1 T2
     * <p>
     * 对于x节点来说 将j进行了右旋转
     * 这个过程是:
     * 1. 先将T3取出来 用临时变量存起来
     * 2. 然后将j节点整个节点放到x的右子树上
     * 3. 将T3放到y的左子树上
     * <p>
     * //1.
     * Node childRightNode = root.left.right;
     * //2.
     * root.left.right = root;
     * //3.
     * root.left = childRightNode;
     * <p>
     * 在这里由于z节点没有变化 高度不用更新
     * 但是x，y的节点都发生了变化 所以要更新x和y的高度
     * TODO 细节注意 这里要先更新y的高度 在更新x的高度
     * 以为x高度是用过y的高度+1得到的
     *
     * @param y
     * @return 右旋转的结果
     */
    @SuppressWarnings("SuspiciousNameCombination")
    private Node rightRotate(Node y) {
        Node x = y.left;
        //1.
        Node T3 = x.right;
        //2.
        x.right = y;
        //3.
        y.left = T3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right));
        x.height = Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    /**
     * 左旋转的结果 逻辑与右旋转相同
     *
     * @param y
     * @return
     */
    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node lefttRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right));
        x.height = Math.max(getHeight(x.left), getHeight(x.right));
        return x;
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
     * 判断一个二叉树的节点是否是平衡二叉树
     * BST的特点是一个节点的左子树小于父节点右子树大于父节点
     * 中序遍历是升序的
     *
     * @return
     */
    public boolean isBST() {

        if (root == null)
            throw new IllegalArgumentException("node is null");
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

    /**
     * 中序遍历 将遍历结果方法list中
     *
     * @param node
     * @param list
     */
    private void inOrder(Node node, List<K> list) {

        if (node == null) {
            return;
        }

        inOrder(node.left, list);
        list.add(node.key);
        inOrder(node.right, list);
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

            System.out.println("is BST " + map.isBST());
            System.out.println("is Balance " + map.isBalance());
        }

        System.out.println();
    }
}
