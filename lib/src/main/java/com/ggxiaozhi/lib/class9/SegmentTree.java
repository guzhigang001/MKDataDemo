package com.ggxiaozhi.lib.class9;

/**
 * 区间树(或者叫线段树)
 */
@SuppressWarnings("unchecked")
public class SegmentTree<E> {

    //线段树的根节点  也就是整个区间树的长度
    private E[] tree;

    //要操作的区间
    private E[] data;

    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        //重新赋值  直接指向 会存在2个对象对应的是一个内存地址 一改全改
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];

        buildSegmentTree(0, 0, arr.length-1);

    }

    /**
     * 在treeIndex的位置创建表示区间[l...r]的线段树
     * <p>
     * 这个的思想是 向找到每个根节点的左右节点在tree中的位置
     * 然后先设置左右节点 在根据逻辑设置父亲节点 这个逻辑是根据业务走的
     *
     * @param treeIndex 表示每次递归的 父亲节点
     * @param l         每个节点区间在数组中左侧的下标值
     * @param r         每个节点区间在数组中右侧的下标值
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {

        //递归终止的条件 如果区间元素只有一个 那么说明是叶子节点
        if (l == r) {
            //树中的叶子节点的值放入的值 这时放 l r都是一样的
            tree[treeIndex] = data[l];
            return;
        }

        //先找到左右孩子 在tree中要在什么位置  在确定左右孩子的区间(或者说确定左右孩子的值)
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        //左右孩子值的确定就是 将父亲节点的区间划分一半 分别放在左右孩子
        // 也就是 mid=(l+r)/2 但是 可能l+r数值太大 超过int的范围 所以我们用下面的方式

        int mid = l + (r - l) / 2;
        //确定区间的中间坐标mid
        //下面我们分别创建 赋值左右孩子
        buildSegmentTree(leftChildIndex, l, mid);
        buildSegmentTree(rightChildIndex, mid + 1, r);

        //左后创建完成左右子节点  再创建 父亲节点 这个是需要根据业务逻辑的
        //比如 我们这里的逻辑就是 父亲节点为左右自己点的和 那么 tree[treeIndex] = tree[leftChildIndex] + tree[rightChildIndex];
        //因为我们的用的是范型  不一定会有 + 号 这个功能 所以我们要创建一个接口 具体的操作 留给用户去实现

        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    public int leftChild(int index) {
        return 2 * index + 1;
    }

    public int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums,
                new Merger<Integer>() {
                    @Override
                    public Integer merge(Integer a, Integer b) {
                        return a + b;
                    }
                });
        System.out.println(segTree);
    }
}


