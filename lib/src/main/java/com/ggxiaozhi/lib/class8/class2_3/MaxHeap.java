package com.ggxiaozhi.lib.class8.class2_3;

import com.example.lib.lib.class2.Array;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    // 返回堆中的元素个数
    public int size(){
        return data.getSize();
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index){
        if (index<0||index>=data.getSize()){
            throw new IndexOutOfBoundsException("下标越界");
        }

        return (index-1)/2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index){
        if (index<0||index>=data.getSize()){
            throw new IndexOutOfBoundsException("下标越界");
        }
        return 2*index+1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index){
        if (index<0||index>=data.getSize()){
            throw new IndexOutOfBoundsException("下标越界");
        }
        return 2*index+2;
    }

    // 向堆中添加元素
    public void add(E e){
        data.addLast(e);

        siftUp(data.getSize()-1);
    }

    /**
     *
     * @param k 新添加元素的下标
     */
    private void siftUp(int k){

    }
}
