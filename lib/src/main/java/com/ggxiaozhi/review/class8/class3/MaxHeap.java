package com.ggxiaozhi.review.class8.class3;


import com.ggxiaozhi.lib.class2.Array;

import java.util.Random;

/**
 * @Description: 从数组的0的位置开始添加元素
 * TODO 这里注意 从数组的0的位置添加和从1位置添加 只是 计算节点的位置不同 思想相同
 * @Author: ggxz
 * @CreateDate: 2020/4/5 18:50
 * @UpdateUser:
 * @UpdateDate: 2020/4/5 18:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MaxHeap<E extends Comparable<E>> {


    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(data.getSize()-1); i>=0; i--) {
            //Heapify 给定一个数组 返回一个最大堆
            siftDown(i);
        }
    }

    //求一个节点的左孩子
    public int leftChlid(int index) {

        return index * 2 + 1;
    }

    public int rightChild(int index) {
        return index * 2 + 2;
    }

    public int parent(int index) {
        return (index - 1) / 2;
    }

    public void add(E e) {
        data.addLast(e);

        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {

        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    public E extractMax() {

        E max = findMax();

        data.swap(0, data.getSize() - 1);
        data.removeLast();

        siftDown(0);

        return max;

    }

    private void siftDown(int k) {

        while (leftChlid(k) < data.getSize()) {

            int j = leftChlid(k);

            if (rightChild(k) < data.getSize() && data.get(rightChild(k)).compareTo(data.get(j)) > 0) {
                j = rightChild(k);
            }

            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }
            data.swap(k, j);
            k = j;
        }
    }

    private E findMax() {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("size==0");
        }

        return data.get(0);
    }



    public static void main(String[] args) {

        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++)
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = maxHeap.extractMax();

        for (int i = 1; i < n; i++)
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MaxHeap completed.");
    }
}
