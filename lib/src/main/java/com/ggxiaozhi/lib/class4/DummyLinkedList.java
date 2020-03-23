package com.ggxiaozhi.lib.class4;

public class DummyLinkedList<E> {


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


    private Node dummyHead;

    private int size;

    public DummyLinkedList(E e) {
        dummyHead = new Node(e, null);
        size = 0;
    }

    public DummyLinkedList() {
        this(null);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void add(int index, E e) {
        if (index < 0 || index > size) {//index > size 这里 index==size 成立  表示在链表的尾部添加数据
            throw new IllegalArgumentException("下标越界");
        }


        Node prev = dummyHead;
        //不是节点 要找出 插入index的前一个节点
        for (int i = 0; i < index; i++) {//这个index - 1 还是要多思考为什么
            prev = prev.next;
        }

//            Node node = new Node(e);
//            node.next = prev.next;
//            prev.next = node;

        prev.next = new Node(e, prev.next);
        size++;

    }

    public Node addNodeX(E e) {

        return add(this.dummyHead.next, e);
    }

    private Node add(Node head, E e) {

        if (head == null) {
            size++;
            return new Node(e);
        }
        head.next = add(head.next, e);
        return head;
    }

    public void addFirst(E e) {

//        Node node = new Node(e);
//        node.next = head;
//        head = node;

        //等同于上面三行代码
//        head = new Node(e, head);
//        size++;
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {//index > size 这里 index==size 不成立
            throw new IllegalArgumentException("下标越界");
        }

        Node cur = dummyHead.next;

        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    // 获得链表的最后一个元素
    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {

        if (index < 0 || index >= size) {//index > size 这里 index==size 不成立
            throw new IllegalArgumentException("下标越界");
        }

        Node cur = dummyHead.next;

        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        cur.e = e;
    }

    public boolean contains(E e) {

        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


    public E remove(int index) {
        if (index < 0 || index >= size) {//index > size 这里 index==size 不成立
            throw new IllegalArgumentException("下标越界");
        }

        Node prev = dummyHead;

        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node delNode = prev.next;

        prev.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;

    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

//        Node cur = dummyHead.next;
//        while (cur != null) {
//            builder.append(cur).append("->");
//            cur = cur.next;
//        }

        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            builder.append(cur).append("->");
        }
        builder.append("NULL");
        return builder.toString();
    }
}
