package com.ggxiaozhi.lib.class4.main7;

import com.ggxiaozhi.lib.class3.main_5.Queue;

public class LinkedListQueue<E> implements Queue<E> {


    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);

        }

        @Override
        public String toString() {
            return e.toString();
        }
    }


    private Node head, tail;
    private int size;


    public LinkedListQueue() {
        this.head = null;
        this.head = null;
        this.size = 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);//tail 说明是空链表  那么直接添加一个节点，并将head指向新Node
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("空链表不能取出元素");
        }
        Node ret = head;
        head = head.next;
        ret.next = null;
        if (head == null) {
            tail = null;
        }
        size--;
        return ret.e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E getFront() {
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }
}
