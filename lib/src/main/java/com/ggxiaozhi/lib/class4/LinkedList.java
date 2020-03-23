package com.ggxiaozhi.lib.class4;

public class LinkedList<E> {


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


    private Node head;

    private int size;

    public LinkedList(E e) {
        head = new Node(e, null);
        size = 0;
    }

    public LinkedList() {
        this(null);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void addFirst(E e) {

//        Node node = new Node(e);
//        node.next = head;
//        head = node;

        //等同于上面三行代码
        head = new Node(e, head);
        size++;
    }


    public void add(int index, E e) {
        if (index < 0 || index > size) {//index > size 这里 index==size 成立  表示在链表的尾部添加数据
            throw new IllegalArgumentException("下标越界");
        }

        if (index == 0) {//头结点 要特殊处理
            addFirst(e);
        } else {
            Node prev = head;
            //不是节点 要找出 插入index的前一个节点
            for (int i = 0; i < index - 1; i++) {//这个index - 1 还是要多思考为什么
                prev = prev.next;
            }

//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;

            prev.next = new Node(e, prev.next);
            size++;
        }
    }

    public void addLast(E e) {
        add(size, e);
    }
}
