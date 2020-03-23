package com.ggxiaozhi.lib.class3.main_2;

import com.ggxiaozhi.lib.class2.Array;

public class ArrayStack<E> implements Stack<E> {
    private Array<E> array;

    public ArrayStack(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayStack() {
        this(10);
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
        return array.get(array.getSize() - 1);
    }

    @Override
    public boolean isEmpty() {
        return array.getSize() == 0;
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Stack [");

        for (int i = 0; i < array.getSize(); i++) {

            builder.append(array.get(i));
            if (i != array.getSize() - 1) {
                builder.append(",");
            }
        }

        builder.append("] top");
        return builder.toString();
    }
}
