package com.ggxiaozhi.lib.class8.class2_3;


import com.ggxiaozhi.lib.class2.Array;

import java.util.Random;

/**
 * 对于数组 存储堆的特点 0元素是 树的头 最后一个元素或是新添加的元素为length=1
 *
 * @param <E>
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    // 返回堆中的元素个数
    public int size() {
        return data.getSize();
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {

        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {

        return 2 * index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        if (index < 0 || index >= data.getSize()) {
            throw new IndexOutOfBoundsException("下标越界");
        }
        return 2 * index + 2;
    }

    // 向堆中添加元素
    public void add(E e) {
        data.addLast(e);

        siftUp(data.getSize() - 1);
    }

    private E findMax() {

        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return data.get(0);
    }

    /**
     * 删除最大值
     *
     * @return 返回最大值
     */
    public E extractMax() {
        //找到最大值用于返回 并且判断异常情况
        E ret = findMax();
        //将数组中的最后一个元素 也就是完全二叉树最后一个元素 方到堆的最顶端 覆盖掉原来的根
        //此时原来的根也就删除了
        data.swap(0, data.getSize() - 1);
        //删除最后的节点
        data.removeLast();
        //此时我们要做的就是判断新的根是否满足堆的性质 不满足 下沉
        siftDown(0);
        return ret;
    }

    /**
     * 下沉
     *
     * @param k 要下沉元素的下标
     */
    private void siftDown(int k) {

        while (leftChild(k) < data.getSize()) {//判断 此时的k 是否有左孩子

            // 1. 取出左孩子的下标
            int j = leftChild(k);

            //2. j + 1 表示右孩子的下标 j + 1 < data.getSize()成立说明 k有左孩子 也有有孩子 同时右孩子大于左孩子
            //那么我们就取出 右孩子
            if (j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = j + 1;
            }

            // 走到这里 循环的条件是k有左孩子 所以 如果k有右孩子 进入上面的if语句后 比较 如果成立说明右孩子比左孩子大 那么j=j+1;
            // 如果不成立 那么说明左孩子的值要大  那么j不变
            // 也就是说走到这里 data[j] 是 leftChild 和 rightChild 中的最大值

            //下面我们就要比较 k与子孩子中的最大值谁大 如果子孩子大那么下沉 否则满足堆的性质
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                //k比子孩子大  循环结束
                break;
            }

            //否则 交换位置

            data.swap(k, j);

            //交换后 k下沉 下标赋值
            k = j;
        }
    }

    /**
     * @param k 要上浮元素的下标
     */
    private void siftUp(int k) {
        //因为我们是先添加的元素 此时如果k==0 那么说明数组中只有一个元素
        //就是我们刚添加的元素 或者是要上浮的元素已经上浮到根节点了 循环停止
        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0) {//k节点的值要大于父节点时 要交换位置
            //交换位置
            data.swap(k, parent(k));
            //交换位置后 上浮节点下标变了 要重新找到要上浮的节点
            k = parent(k);
        }
    }


    /**
     * 插入11
     * 10
     * /  \
     * 8    9
     * /  \  / \
     * 7   5 4  6
     * / \
     * 3  2
     * <p>
     * 10
     * /  \
     * 8    9
     * /  \  / \
     * 7   5 4  6
     * / \  /
     * 3  2 11
     * <p>
     * 10
     * /  \
     * 8    9
     * /  \  / \
     * 7   5 4  6
     * / \  /
     * 3  2 5
     * <p>
     * 10
     * /  \
     * 8    9
     * /  \  / \
     * 7   8 4  6
     * / \  /
     * 3  2 5
     * <p>
     * 10
     * /  \
     * 8    9
     * /  \  / \
     * 7   8 4  6
     * / \  /
     * 3  2 5
     * <p>
     * 10
     * /  \
     * 10    9
     * /  \  / \
     * 7   8 4  6
     * / \  /
     * 3  2 5
     * <p>
     * <p>
     * 新的堆
     * 11
     * /  \
     * 10    9
     * /  \  / \
     * 7   8 4  6
     * / \  /
     * 3  2 5
     * <p>
     * 对比之前的堆
     * 10
     * /  \
     * 8    9
     * /  \  / \
     * 7   5 4  6
     * / \
     * 3  2
     *
     * @param array
     */
    private void upAdjust(E[] array) {
        //完全二叉树叶子节点的最后一个(可能是左节点也可能是右节点)
        int childIndex = array.length - 1;
        //childIndex节点的父亲节点
        int parentIndex = parent(childIndex);

        //temp节点要保存插入节点的值  根据add()方法的逻辑我们是先在数组的结尾添加的元素
        // 那么childIndex位置的节点就是新插入的节点
        E temp = array[childIndex];
        while (childIndex > 0 && temp.compareTo(array[parentIndex]) < 0) {
            //将父节点 放到子节点的位置
            //TODO 特点: 无需真正交换  单向赋值
            array[childIndex] = array[parentIndex];
            //子节点和父节点位置互换
            childIndex = parentIndex;
            //父节点变成子节点 位置变换 相当于childIndex之前的位置
            parentIndex = (parentIndex - 1) / 2;
            //TODO 这次循环结束后 原来的父节点已经换到了子节点的为主 但是子节点只是指向了原父节点的位置 此时原父节点和新的子节点(也就是原父节点交换后的子节点)相同
        }
        array[childIndex] = temp;
    }

    public E replace(E e) {
        E ret = findMax();

        data.set(0, e);

        siftDown(0);
        return ret;
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
