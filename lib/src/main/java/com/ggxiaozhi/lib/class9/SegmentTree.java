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

    public SegmentTree(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
    }

    public int leftChild(int index) {
        return 2 * index + 1;
    }

    public int rightChild(int index) {
        return 2 * index + 2;
    }
}
