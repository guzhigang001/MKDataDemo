package com.ggxiaozhi.lib.class13;

import java.util.ArrayList;

/**
 * Create by ggxz
 * 2020/3/27
 * description:红黑树  具体来说 左倾红黑树和一般的红黑树是一样的 重点在于 一般红黑树的root的左右节点都可以是红色的
 * //但是我们这里只能是2-3中融合节点的左侧
 * <p>
 * //红黑树的添加逻辑
 * // 空节点添加元素 要保持红黑树的性质：根节点是黑节点 所以 新创建的节点默认是红色我们要手动改成黑色：如下：
 * //
 * //
 * //
 * // 1     2 add z     3    add y       4   左旋转y       5     右旋转x             6    翻转颜色
 * //xR--->xB----->    xB   ------>     xB  -------->    xB    ---------->         yB    ------->     yR
 * //                 /      z<y<x     /                /     旋转后要            /  \               /  \        最后我返回的根节点是红色的 这样我们向上回溯递归再返回接入的时候依然满足新接入的节点是红色的
 * //                zR               zR               yR     y颜色=x.color      zR  xR            zB   xB       root.left = add(root.left, key, value);
 * //            满足性质不变            \             /       x.color=RED                                       root.right = add(root.right, key, value);
 * //                                    yR          zR                                                         比如add()方法中的上面2行代码 保证接入添加回溯的节点一定是红色的
 * //                                                                                                           这样我们可以递归去进入我们处理新的红色节点的方法
 * //
 * //
 * //这里123456 的顺序是不一定的 我们可以一次判断 当添加新的节点(一定是红色的) 满足哪种情况进入哪种分支
 * //
 * //
 * //
 * //
 * <p>
 * //TODO 第二遍的理解 这部分还是要再细品
 * // TODO 关于红黑树 13-7中说明了红黑树和2-3树之间的联系 其实在红黑树中添加元素 就是在2-3树中添加元素
 * 关键在于我们要维护红黑树的性质 而出现维护性质的时候就是在新节点要插入3节点的时候(其实插入2节点中右子树也是需要变化的 但是因为我们是左倾的红黑树
 * 所以要维护左倾的性质 所以才旋转，一般的红黑树是左右孩子都可以为红色的)
 * <p>
 * 解释迷惑，其实这个是左倾红黑树 规定就是再3节点中
 * 左面的元素是红色的 同时新添加的节点是红色的原因是，
 * 一个新添加的节点一定是和一个已经存在的节点融合
 * ，融合后先取保持红黑树的平衡 再来维护颜色那么添加的颜色定成红色，
 * 1.是因为融合的原因。 2.主要还是方便处理。
 * <p>
 * <p>
 * //TODO 红黑树与AVL树 稍微 优势的点在于 AVL树 某个节点出现不平衡后 通过旋转保持平衡 然后向上回溯 可能还是不平衡的
 * // 最坏的情况 可能一直旋转到更节点  而红黑树首先他放松了对平衡因子这要求 那么必然旋转次数也会减少 同时 他最坏为2logn 和AVL logn也是在同一个数量级的
 * // 所以在插入和删除这种需要大量旋转的操作中 红黑树统计上要比AVL优越
 */
@SuppressWarnings("SuspiciousNameCombination")
public class RBTree<K extends Comparable<K>, V> {

    //新创建的节点 默认是红色的
    //TODO 默认是红色的原因是因为 在红黑树中 新添加的节点经过2-3树的转化 一定是红色的
    private static final boolean RED = true;
    private static final boolean BLACK = false;

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
     * 红黑树的左旋转 左旋转的前提就是 新插入的红色节点在root右侧的时候
     * 我们才需要对root进行左旋转
     * <p>
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

        //这里相当于定义 原来的传入的根节点node 的颜色是什么
        //我们旋转后的新的 根节点x 也要保持这个颜色
        //同时 定义旋转后的根节点x的左子树是红色的节点
        //TODO 这里注意 可能原来node节点就是红色的 导致 x 和node都是红色 这个我们不管 也返回x
        //这里我们不做平衡 只旋转 同时维护旋转本身的颜色
        x.color = y.color;
        y.color = RED;
        return x;
    }

    //向红黑树中添加元素  并保持根节点为黑色
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
        y.left = x.right;
        x.right = y;

        x.color = y.color;
        y.color = RED;

        return x;

    }

    /**
     * 翻转颜色
     * //TODO 复习中，对翻转颜色 新的理解： 对于翻转颜色 首先满足下面的情况
     * 同时翻转的目的是 对于根节点 在回溯向上递归的时候 相当于 向上融合 也就是根节点再向父节点融合，
     * 也就相当于向父节点添加一个元素 所以根据我们定好的逻辑 新添加节点的颜色是红色的也满足
     *
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
            //新添加的节点 一定是红色的
            // TODO 也就是返回的节点是红色的
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(root.key) > 0) {//右子树
            root.right = add(root.right, key, value);
        } else if (key.compareTo(root.key) < 0) {
            root.left = add(root.left, key, value);
        } else {
            root.value = value;
        }

        //处理节点位置再这里
        //TODO 其实这种情况对应两种 1：当添加的节点等价与2-3树的三节点时 也就是4情况的时候 我们要左旋转，
        // 同时当添加的节点为2-3树中的2节点的时候 如下：
        // 也就是根据我们的定义(我们实现的是左倾红黑树) 红色节点只能添加到左侧
        // B
        //  \
        //   R
        // 这种情况我们也要进行做旋转 所以我们可以直接判断一个节点的右子树是不是红色 如果是 就进行左旋转
        //

        if (isRed(root.right) && !isRed(root.left)) {//4的情况 root=x
            root = leftRotate(root);
        }

        if (isRed(root.left) && isRed(root.left.left)) {//5中的情况 root=x
            root = rightRotate(root);
        }

        if (isRed(root.left) && isRed(root.right)) {//6中的情况 root=y
            flipColors(root);
        }

        //上面新建节点是红色的 那我们向上回溯 也要返回根节点是红色的这样再进入循环的时候我们对于
        //新传来的节点是红色的那么我就可以再次走入上的逻辑去处理
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

            RBTree<String, Integer> map = new RBTree<>();
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
