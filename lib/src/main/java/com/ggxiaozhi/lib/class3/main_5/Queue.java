package com.ggxiaozhi.lib.class3.main_5;

public interface Queue<E> {

    void enqueue(E e);

    E dequeue();

    boolean isEmpty();

    E getFront();

    int getSize();
}
