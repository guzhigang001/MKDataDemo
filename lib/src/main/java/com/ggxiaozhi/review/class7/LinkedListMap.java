package com.ggxiaozhi.review.class7;

import com.ggxiaozhi.lib.class12.class2.FileOperation;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/4/5 16:13
 * @UpdateUser:
 * @UpdateDate: 2020/4/5 16:13
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

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
            return key.toString() + ":" + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node(null);
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);

        if (node == null) {
            Node temp = dummyHead.next;
            dummyHead.next = new Node(key, value);
            dummyHead.next.next = temp;
            size++;
        } else {
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                prev.next = cur.next;
                cur.next = null;
                size--;
                return cur.value;
            } else {
                prev = cur;
                cur = cur.next;
            }

        }
        return null;
    }

    @Override
    public void set(K key, V value) {

        Node node = getNode(key);
        if (node != null) {
            node.value = value;
        } else {
            throw new IllegalArgumentException("no value");
        }
    }

    @Override
    public boolean contains(K key) {

        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
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

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }


    public static void main(String[] args) {
//        Map<Integer, String> map = new LinkedListMap<>();
//        for (int i = 0; i < 5; i++) {
//            map.add(i, "value" + i);
//        }
//        System.out.println(map);
//
//        String remove = map.remove(2);
//        System.out.println(remove);
//        System.out.println(map);

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            LinkedListMap<String, Integer> map = new LinkedListMap<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();

    }
}
