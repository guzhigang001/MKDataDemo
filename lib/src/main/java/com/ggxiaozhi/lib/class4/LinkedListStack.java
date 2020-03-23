package com.ggxiaozhi.lib.class4;

import com.ggxiaozhi.lib.class3.main_2.Stack;

public class LinkedListStack<E> implements Stack<E> {

    private DummyLinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new DummyLinkedList<>();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }
}
