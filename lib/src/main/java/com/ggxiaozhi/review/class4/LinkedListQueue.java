package com.ggxiaozhi.review.class4;

import com.ggxiaozhi.lib.class3.main_5.Queue;
import com.ggxiaozhi.lib.class4.DummyLinkedList;

/**
 * @Description: 队列 head端为队首 因为添加和删除都是O(1)  tail 端为队尾因为 添加元素O(1) 删除元素O(n)
 * @Author: ggxz
 * @CreateDate: 2020/3/18 13:32
 * @UpdateUser:
 * @UpdateDate: 2020/3/18 13:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LinkedListQueue<E> implements Queue<E> {


    @Override
    public void enqueue(E e) {

        if (isEmpty()) {
            head = new Node(e);
            tail = head;
        } else {
            Node node = new Node(e);
            tail.next = node;
            tail = node;
        }

        size++;
    }

    @Override
    public E dequeue() {

//        if (isEmpty()) {
        if (tail == head) {
            throw new IllegalArgumentException("链表为NULL");
        }

        Node reNode = head;
        head = head.next;
        reNode.next = null;

        //如果节点中只有一个元素 那么head.next
        //就是tail 所以也要维护tail
        if (head == null) {
            tail = null;
        }
        size--;
        return reNode.e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("链表为NULL");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    private class Node {

        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node() {
            this(null, null);
        }

        public Node(E e) {
            this(e, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head, tail;

    private int size;


    public LinkedListQueue() {

        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args) {

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
