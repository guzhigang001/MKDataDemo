package com.ggxiaozhi.review.class2.main1_7;


/**
 * 待完善 findAll  removeAllElement
 * <p>
 * 提示： findAll 可以内部添加个数组 然后找到一个值后不返回直接将找到的值添加到数组中 直到循环结束
 * 删除相同
 */
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

    // 向所有元素后添加一个新元素
    public void addLast(E e) {
        add(size, e);
    }

    // 在所有元素前添加一个新元素
    public void addFirst(E e) {

        add(0, e);
    }

    // 在index索引的位置插入一个新元素e
    //插入元素的逻辑是 如果这个元素的位置index==size 那么可以直接添加  如果不是那么就需要将index位置以后的位置都向后移动一位
    //然后将index的位置的覆盖掉 完成插入
    public void add(int index, E e) {

        if (size == data.length)
            throw new IllegalArgumentException("Add failed. Array is full.");

        //{1,2,3,size的位置,null,null}
        //index > size  这个条件是因为 当index=size时 语义是向数组中的最后一个位置添加元素 size指向数组最后一个为null的位置
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");


        for (int i = size - 1; i >= index; i--) {
            //从数组的最后一个元素开始 所有后一个元素指向前一个元素
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;

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
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;

    }

    // 从数组中删除index位置的元素, 返回删除的元素

    public E remove(int index) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("参数不合法");
        }
        //删除元素 就是把index之后的所有位置的值向前移动一位 同时size维护也向前一位 此时 size移动后指向原来数组中的
        //最后一位 但是这时候是没有影响的 因为size所指向的值用户是永远访问不到的

        E e = data[index];
        //TODO 这个问题 就是一个典型的是思考问题 我写的代码 for (int i = index; i < size; i++)  data[i] = data[i+1]; 当size 正好装满就会出错  但是换到下面的思路就是没问题的 而且少循环一遍


//        for (int i = index + 1; i < size; i++) {//或者下面
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null;

        return e;
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

}
