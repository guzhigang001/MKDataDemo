package com.ggxiaozhi.lib.class9.class1_4;

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

        buildSegmentTree(0, 0, arr.length - 1);

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

        //先找到左右孩子 在tree中要在什么位置  在确定左右孩子的区间(或者说确定左右孩子的值要放在tree中什么位置)
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


    /**
     * 对传入区间的值求和
     * 返回区间[queryL, queryR]的值
     *
     * @param queryL 区间左边界的索引
     * @param queryR 区间右边界的索引
     * @return
     */
    public E query(int queryL, int queryR) {

        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IndexOutOfBoundsException("Index is illegal.");
        }

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
     *
     * @param treeIndex 递归中的根节点
     * @param l         每次递归所查询数组区间的左边界
     * @param r         每次递归所查询数组区间的右边界
     * @param queryL    要查询数组区间的左边界
     * @param queryR    要查询数组区间的右边界
     * @return 返回[queryL, queryR]区间的值
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {

        if (l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        //如果不在 treeIndex所对应的区间 那么就向treeIndex的左右孩子找
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
        int mid = l + (r - l) / 2;

        if (queryL >= mid + 1) {//说明一定不在左子树上 那么我们应该向右子树去查找
            //同时要返回查找的值
            return query(rightChildIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {//说明一定在左子树上 那么我们应该向左子树去查找
            //同时要返回查找的值
            return query(leftChildIndex, l, mid, queryL, queryR);
        }
        //否者 就是这个区间 一部分在左子树 一部分在右子树
        //那么我们以mid为界限 查询区间的左边部分区间为 [queryL,mid] 右区间为 [mid+1,queryR]

        E leftResult = query(leftChildIndex, l, mid, queryL, mid);
        E rightResult = query(rightChildIndex, mid + 1, r, mid + 1, queryR);

        return merger.merge(leftResult, rightResult);
    }

    /**
     * 将index位置的值，更新为e 在data数组中
     * 这个操作 分为2步一步是要修改维护的data的中的值
     * 这也是这个方法的功能所在
     * 另一个就是修改完data数组
     * 也要同时维护好tree数组
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    /**
     * @param treeIndex 根节点 在tree中的index
     * @param l         查找data的左区间
     * @param r         查找data的右区间
     * @param index     要插入data数组中的index
     * @param e         修改的元素
     */
    private void set(int treeIndex, int l, int r, int index, E e) {

        if (l == r) {//已经递归到数组中只有一个元素了
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        //在这个方法里 index和mid都是针对data数组而言的 这个要注意

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        if (index >= mid + 1) {//那么一定在右子树上
            set(rightChildIndex, mid + 1, r, index, e);
        } else if (index <= mid) {
            set(leftChildIndex, 0, mid, index, e);
        }


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

        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(2, 5));
        System.out.println(segTree.query(0, 5));
    }
}


