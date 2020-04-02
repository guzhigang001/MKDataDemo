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


    private Node<E> head;

    private int size;

    private Node<E> pre;

    public LinkedList() {

        head = null;
        size = 0;
        pre = null;
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {

        Node<E> node = new Node<>(e);
        node.next = head;
        head = node;

        size++;

        //head = new Node(e,head)

    }


    public void add(E e, int index) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("下标越界");
        }

        if (index == 0) {
            addFirst(e);
        }
        pre = head;
        int tempIndex = 0;
        for (Node cur = head; cur.next != null; cur = cur.next) {

            if (tempIndex == index) {
                pre.next = new Node(e, cur);
                break;
            }
            pre = cur;
            tempIndex++;
        }
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

        while (cur!= null) {
            Node temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;

        }

        return prev;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Node cur = head; cur != null; cur = cur.next) {
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
        linkedList.add(8, 2);
        System.out.println(linkedList.toString());

        System.out.println(linkedList.reverseList(linkedList.head));
        System.out.println(linkedList);
    }
}
