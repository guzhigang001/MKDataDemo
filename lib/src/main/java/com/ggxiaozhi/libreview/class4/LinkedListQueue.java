package com.ggxiaozhi.libreview.class4;

import com.ggxiaozhi.lib.class3.main_5.Queue;

/**
 * @Description: 有尾指针的 链表  再尾部添加  在头删除
 * @Author: ggxz
 * @CreateDate: 2020/4/2 22:20
 * @UpdateUser:
 * @UpdateDate: 2020/4/2 22:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressWarnings("unchecked")
public class LinkedListQueue<E> implements Queue<E> {

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

    private E e;
    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {

        if (tail == null) {
            tail = new Node(e);
            head = tail;
            size++;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
            size++;
        }
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }

        Node reNode = head;
        head = head.next;
        reNode.next = null;
        size--;
        if (head == null)
            tail =null;

        return (E) reNode.e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E getFront() {
        return (E) head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while(cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args){

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
