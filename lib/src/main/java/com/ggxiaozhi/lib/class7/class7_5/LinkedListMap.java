package com.ggxiaozhi.lib.class7.class7_5;

import com.ggxiaozhi.lib.class7.DummyLinkedList;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/3/22 22:30
 * @UpdateUser:
 * @UpdateDate: 2020/3/22 22:30
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LinkedListMap<K, V> implements Map<K, V> {


    private class Node {

        private K key;
        private V value;
        private Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public Node(K key) {
            this.key = key;
            this.value = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return String.format("[key = %s,value = %s]", key.toString(), value.toString());
        }
    }

    private Node dummyHead;

    private int size;

    public LinkedListMap() {
        dummyHead = new Node(null);
        size = 0;
    }

    private Node getNode(K key) {

        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }

            cur = cur.next;

        }
        return null;

    }

    /**
     * 这里设计是否可以添加可以重复的key 这里是可以的
     *
     * @param key
     * @param value
     */
    @Override
    public void add(K key, V value) {

        if (dummyHead.next == null) {
            dummyHead.next = new Node(key, value);
            return;
        }

        Node node = getNode(key);
        if (node == null) {
            addFirst(key, value);
        } else {
            node.value = value;
        }

    }

    private void addFirst(K key, V value) {
        dummyHead.next = new Node(key, value, dummyHead.next);
        size++;
    }


    /**
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) {
        //TODO 这个是我算法  这个是不好的因为查找的时候时间是O(n)的 这个应该是2n
//        Node node = getNode(key);
//        if (node == null) {
//            throw new IllegalArgumentException("key is null");
//        }
//
//        remove(dummyHead, key);
//
//        return node.value;
        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.key.equals(key))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }

        return null;
    }

    /**
     *
     * @param key
     * @return
     */
    public V removeUn(K key){

        Node prev = dummyHead;
        while(prev.next != null){
            if(prev.next.key.equals(key))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }

        return null;
    }
    /**
     * 返回删除之后的头结点 也可以返回V
     *
     * @param head
     * @param key
     * @return 是否删除成功
     */
    private boolean remove(Node head, K key) {

        Node prev = head;
        Node cur = head.next;
        V temp = null;
        while (cur != null) {
            if (cur.key.equals(key)) {
                temp = cur.value;
                prev.next = cur.next;
                cur.next = null;
                size--;
                break;
            } else {
                prev = cur;
                cur = cur.next;
            }

        }
//        return head;
        return temp != null;
    }

    /**
     * 返回删除之后的头结点 也可以返回V
     *
     * @param head
     * @param key
     * @return 返回头结点
     */
    private Node removeRe(Node head, K key) {

        if (head == null) {//遍历到最后也没有找到
            return null;
        }

        if (head.key.equals(key)) {

            return head;
        }

        Node newHead = removeRe(head.next, key);
        head.next = newHead.next;
        newHead.next = null;
        size--;
        return head;//把最开始的头结点返回去

    }

    @Override
    public void set(K key, V newValue) {

        Node node = getNode(key);
        if (node == null)
            throw new IllegalArgumentException("key is null");
        node.value = newValue;


    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        if (node == null)
            return null;
        else
            return node.value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }



    public static void main(String[] args) {
        Map<Integer, String> map = new LinkedListMap<>();
        for (int i = 0; i < 5; i++) {
            map.add(i, "value" + i);
        }
        System.out.println(map);

        String remove = map.remove(2);
        System.out.println(remove);
        System.out.println(map);

    }
}
