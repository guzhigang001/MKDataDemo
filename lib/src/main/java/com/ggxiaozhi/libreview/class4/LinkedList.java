package com.ggxiaozhi.libreview.class4;

import java.util.List;

/**
 * Create by ggxz
 * 2020/4/2
 * description:
 */
@SuppressWarnings("unchecked")
public class LinkedList<E> {

    private class Node<E> {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }


    private Node dummyHead;

    private int size;


    public LinkedList() {

        dummyHead = new Node<>(null, null);
        size = 0;
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {

        add(e, 0);

    }

    public void addLast(E e) {
        add(e, size - 1);
    }

    public void add(E e, int index) {
        if (index > size || index < 0) {
            throw new IllegalArgumentException("下标越界");
        }


        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        pre.next = new Node(e, pre.next);

        size++;
    }

    /**
     * 返回翻转得头
     *
     * @param head
     * @return
     */
    public Node reverst(Node head) {
        if (head == null || head.next == null)
            return head;

        Node newHead = reverst(head.next);

        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public Node reverseList(Node head) {

        if (head == null || head.next == null)
            return head;

        Node prev = null;
        Node cur = head;

        while (cur != null) {
            Node temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;

        }

        return prev;
    }


    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("下标越界");
        }
        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    public boolean contains(E e) {
        Node cur = dummyHead.next;
        for (int i = 0; i < size; i++) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }

        return false;
    }

    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("下标越界");
        }
        Node<E> prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node<E> del = prev.next;
        prev.next = prev.next.next;
        del.next = null;
        size--;
        return del.e;
    }

    public void set(int index, E e) {
        Node cur = dummyHead.next;

        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        cur.e = e;

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            builder.append(cur).append("->");
        }
        builder.append("NULL");
        return builder.toString();
    }

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
        }
        System.out.println(linkedList.toString());
        System.out.println(linkedList.get(0));
        System.out.println(linkedList.get(linkedList.size - 1));
        System.out.println(linkedList.contains(0));
        System.out.println(linkedList.contains(4));
        System.out.println(linkedList.contains(8));
        System.out.println(linkedList.contains(9));
        linkedList.add(8, 2);
        linkedList.addLast(7);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.remove(2));
        linkedList.set(2, -1);
        System.out.println(linkedList.toString());


    }
}
