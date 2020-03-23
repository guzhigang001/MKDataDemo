package com.ggxiaozhi.lib.class7;


/**
 * 利用递归来实现链表的所有操做
 */
public class DummyLinkedList<E> {

    private class Node {

        public E e;

        public Node next;

        public Node(E e) {
            this.e = e;
            next = null;
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }


    private Node dummyHead;
    private int size;

    public DummyLinkedList() {
        dummyHead = new Node(null);
        size = 0;
    }

    public DummyLinkedList(E e) {
        dummyHead = new Node(null);
        dummyHead.next = new Node(e);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {

        if (index < 0 || index > size) {//size指向的是最后一个元素的下一个元素 那么index=size表示向尾部添加元素
            throw new IllegalArgumentException("下标越界");
        }

        add(dummyHead, index, 0, e);
    }

    /**
     * @param head    头节点
     * @param index   用户要插入的index
     * @param current 表示 当前执行到那个index
     * @param e       要插入的元素
     */
    private void add(Node head, int index, int current, E e) {
        if (current == index) {
            size++;
            head.next = new Node(e, head.next);
            return;
        }
        add(head.next, index, current + 1, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size - 1, e);
    }

    public E remove(int index) {

        return remove(dummyHead, index, 0);

    }

    private E remove(Node head, int index, int current) {
        if (current == index) {
            Node delNode = head.next;
            head.next = head.next.next;
            delNode.next = null;
            return delNode.e;
        }

        E e = remove(head.next, index, current + 1);
        return e;
    }

    public void removeElement(E e) {
        if (dummyHead.next == null) {
            throw new IllegalArgumentException("空表");
        }
        Node prev = dummyHead;

        while (prev.next != null) {

            if (prev.next.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node temp = prev.next;
            prev.next = prev.next.next;
            temp.next = null;
            size--;
        }
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void set(int index, E e) {
        set(dummyHead.next, index, 0, e);
    }

    private void set(Node head, int index, int current, E e) {
        if (current == index) {
            head.e = e;
            return;
        }

        set(head.next, index, current + 1, e);
    }

    public boolean contains(E e) {

        return contains(dummyHead.next, e);
    }

    private boolean contains(Node head, E e) {
        if (head == null) {
            return false;
        } else {
            if (head.e.equals(e)) {
                return true;
            }
        }
        return contains(head.next, e);
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node node = dummyHead.next; node != null; node = node.next) {
            builder.append(node.e).append("->");
        }
        builder.append("NULL");
        return builder.toString();
    }

    public static void main(String[] args) {
        DummyLinkedList<Integer> linkedList = new DummyLinkedList<>();
        for (int i = 0; i < 6; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
            System.out.println(linkedList.size);
        }

        System.out.println(linkedList);
        System.out.println(linkedList.size);
        linkedList.removeElement(1);
        System.out.println(linkedList);
        System.out.println(linkedList.size);
        linkedList.addFirst(333);
        System.out.println(linkedList);


        linkedList.add(2, 666);
        System.out.println(linkedList);
        linkedList.remove(2);
        System.out.println(linkedList);

        System.out.println(linkedList.contains(333));
        System.out.println(linkedList.contains(555));
    }
}
