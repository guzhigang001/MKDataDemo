package com.ggxiaozhi.lib.class3.main_5;

import com.ggxiaozhi.lib.class2.Array;

public class ArrayQueue<E> implements Queue<E> {

    Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);

    }

    public ArrayQueue() {
        this(10);
    }


    @Override
    public void enqueue(E e) {

        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public E getFront() {
        return array.get(0);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Queue front[");

        for (int i = 0; i < array.getSize(); i++) {

            builder.append(array.get(i));
            if (i != array.getSize() - 1) {
                builder.append(",");
            }
        }

        builder.append("] tail");
        return builder.toString();
    }
}
