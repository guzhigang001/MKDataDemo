package com.ggxiaozhi.lib.class3.main_2;

public interface Stack<E> {

    void push(E e);

    E pop();

    E peek();

    boolean isEmpty();

    int getSize();
}
