package com.ggxiaozhi.review.class3;

import com.ggxiaozhi.review.class2.main8.Array;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/3/16 20:55
 * @UpdateUser:
 * @UpdateDate: 2020/3/16 20:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> array;


    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        array = new Array<>();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("Stack: [");
        for (int i = 0; i < getSize(); i++) {
            builder.append(i);
            if (i != getSize() - 1) {
                builder.append(", ");
            }

        }

        builder.append("] top");
        return builder.toString();
    }
}
