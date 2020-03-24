package com.ggxiaozhi.lib.class8;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * leetcode 347  https://leetcode-cn.com/problems/top-k-frequent-elements/
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * <p>
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * <p>
 * 说明：
 * <p>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * <p>
 * <p>
 * 解题思路： 首先 我们要将数组中的元素 放到map中
 * 存入元素的值和出现的频率 O(n)
 * 然后我们 取出map中的前k个元素 装到我们的最大堆中
 * 这个时候可能队中的元素不是符合要求的
 * 所以我们要遍历map中k下一个元素 看这个元素是否比队中的最小的那个元素的k要大
 * 如果大 那么就和队中的最小值进行替换
 * 这个算法的时间复杂度是nlogK  对比其他排序算法nlogn要优化
 * <p>
 * 注意： 这里我们虽然用的是最大堆 但是 什么是最大 什么是最小 我们可以通过 Comparable这个接口自己去定义
 * 所以这里我们虽然用的是最大堆 但是我们可以在选择堆的顶点是k元素出现次数最少的那个节点 也就是最小值
 *
 * //TODO 这里使用TreeMap 和HashMap 在效率是差异很大  使用ArrayList与LinkedList差异无明显变化
 *
 * @see Solution2 代码精简
 */
public class Solution {

    /**
     * frequency 元素+频数
     */
    private class Freq implements Comparable<Freq> {
        int e, freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        /**
         * 在这里定义什么是大 什么是小
         *
         * @param another 传入的比较值 和这个值比较
         * @return
         */
        @Override
        public int compareTo(Freq another) {

            if (this.freq < another.freq) {//如果传来的freq 大 那么我们让小的 在堆顶 也就是说 让小的freq频率在堆中大的
                return 1;
            } else if (this.freq > another.freq) {
                return -1;
            } else {//相等
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        //<数组中的元素,这个元素出现的频数>
        //完成这个后 map中放的就是数组中 无重复每个数和出现的频数
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            int keyNum = nums[i];
            if (treeMap.containsKey(keyNum)) {
                treeMap.put(keyNum, treeMap.get(keyNum) + 1);
            } else {
                treeMap.put(keyNum, 1);
            }
        }

        PriorityQueue<Freq> priorityQueue = new PriorityQueue<>();

        for (Integer key : treeMap.keySet()) { //O(n)
            if (priorityQueue.getSize() >= k) {//说明队列中的元素已经满了 取到了k个元素
                Freq freq = priorityQueue.getFront();
                Freq another = new Freq(key, treeMap.get(key));
                if (freq.compareTo(another) > 0) {// logk 所以整个算法是O(nlogk)
                    priorityQueue.dequeue();
                    priorityQueue.enqueue(another);
                }
            } else {
                Freq freq = new Freq(key, treeMap.get(key));
                priorityQueue.enqueue(freq);
            }
        }

        List<Integer> list = new ArrayList<>();

        while (!priorityQueue.isEmpty())
            list.add(priorityQueue.dequeue().e);
        return list;
    }

    private class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

        private MaxHeap<E> maxHeap;

        public PriorityQueue() {
            maxHeap = new MaxHeap<>();
        }

        @Override
        public void enqueue(E e) {
            maxHeap.add(e);
        }

        @Override
        public E dequeue() {
            return maxHeap.extractMax();
        }

        @Override
        public boolean isEmpty() {
            return maxHeap.isEmpty();
        }

        @Override
        public E getFront() {
            return maxHeap.findMax();
        }

        @Override
        public int getSize() {
            return maxHeap.size();
        }
    }

    public class MaxHeap<E extends Comparable<E>> {

        private Array<E> data;

        public MaxHeap(int capacity) {
            data = new Array<>(capacity);
        }

        public MaxHeap() {
            data = new Array<>();
        }

        public MaxHeap(E[] arr) {
            this.data = new Array<>(arr);
            heapify(this.data);
        }

        /**
         * 给定一个数组转成一个堆
         * 首先找到二叉树 从右到左的第一个非叶子节点 也就是数组中的最后一个元素的父节点
         * 然后下沉 然后下次循环 i--也就是找到左节点 或是上一层的最右边的非叶子节点
         * 然后开始下沉
         */
        private void heapify(Array<E> array) {

            for (int i = parent(data.getSize() - 1); i >= 0; i--) {
                siftDown(i);
            }
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

        public E findMax() {

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
            //将数组中的最后一个元素 也就是完全二叉树最后一个元素 方法堆的最顶端 覆盖掉原来的根
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

        /**
         * 取出堆中最大的元素 并替换成e
         *
         * @param e
         * @return
         */
        public E replace(E e) {

            //找出最大的元素 用于返回
            E max = findMax();

            //数组的头是 根节点 所以新的元素要覆盖掉根节点
            data.set(0, e);

            //这个时候可能破环了最大二叉堆的xingzhi
            siftDown(0);
            return max;
        }
    }

    public class Array<E> {

        private E[] data;
        private int size;

        // 构造函数，传入数组的容量capacity构造Array
        public Array(int capacity) {
            data = (E[]) new Object[capacity];
            size = 0;
        }

        // 无参数的构造函数，默认数组的容量capacity=10
        public Array() {
            this(10);
        }

        public Array(E[] arr) {

            data = (E[]) new Object[arr.length];
            for (int i = 0; i < arr.length; i++) {
                data[i] = arr[i];
            }
            size = arr.length;
        }

        // 获取数组的容量
        public int getCapacity() {
            return data.length;
        }

        // 获取数组中的元素个数
        public int getSize() {
            return size;
        }

        // 返回数组是否为空
        public boolean isEmpty() {
            return size == 0;
        }

        // 在index索引的位置插入一个新元素e
        public void add(int index, E e) {

            if (index < 0 || index > size)
                throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

            if (size == data.length)
                resize(2 * data.length);

            for (int i = size - 1; i >= index; i--)
                data[i + 1] = data[i];

            data[index] = e;

            size++;
        }

        // 向所有元素后添加一个新元素
        public void addLast(E e) {
            add(size, e);
        }

        // 在所有元素前添加一个新元素
        public void addFirst(E e) {
            add(0, e);
        }

        // 获取index索引位置的元素
        public E get(int index) {
            if (index < 0 || index >= size)
                throw new IllegalArgumentException("Get failed. Index is illegal.");
            return data[index];
        }

        // 修改index索引位置的元素为e
        public void set(int index, E e) {
            if (index < 0 || index >= size)
                throw new IllegalArgumentException("Set failed. Index is illegal.");
            data[index] = e;
        }

        // 查找数组中是否有元素e
        public boolean contains(E e) {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(e))
                    return true;
            }
            return false;
        }

        // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
        public int find(E e) {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(e))
                    return i;
            }
            return -1;
        }

        // 从数组中删除index位置的元素, 返回删除的元素
        public E remove(int index) {
            if (index < 0 || index >= size)
                throw new IllegalArgumentException("Remove failed. Index is illegal.");

            E ret = data[index];
            for (int i = index + 1; i < size; i++)
                data[i - 1] = data[i];
            size--;
            data[size] = null; // loitering objects != memory leak

            if (size == data.length / 4 && data.length / 2 != 0)
                resize(data.length / 2);
            return ret;
        }

        // 从数组中删除第一个元素, 返回删除的元素
        public E removeFirst() {
            return remove(0);
        }

        // 从数组中删除最后一个元素, 返回删除的元素
        public E removeLast() {
            return remove(size - 1);
        }

        // 从数组中删除元素e
        public void removeElement(E e) {
            int index = find(e);
            if (index != -1)
                remove(index);
        }

        @Override
        public String toString() {

            StringBuilder res = new StringBuilder();
            res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
            res.append('[');
            for (int i = 0; i < size; i++) {
                res.append(data[i]);
                if (i != size - 1)
                    res.append(", ");
            }
            res.append(']');
            return res.toString();
        }

        // 将数组空间的容量变成newCapacity大小
        private void resize(int newCapacity) {

            E[] newData = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++)
                newData[i] = data[i];
            data = newData;
        }

        public void swap(int i, int j) {
            if (i < 0 || i >= size || j < 0 || j >= size) {
                throw new IllegalArgumentException("下标不合法");
            }

            E temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
    }

    public interface Queue<E> {

        void enqueue(E e);

        E dequeue();

        boolean isEmpty();

        E getFront();

        int getSize();
    }


}
