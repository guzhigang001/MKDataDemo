package com.ggxiaozhi.lib.class4;

public class Main {

    public static void main(String[] args) {

        DummyLinkedList<Integer> linkedList = new DummyLinkedList<>();
        for(int i = 0 ; i < 3 ; i ++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.addNodeX(333);
        System.out.println(linkedList);


        linkedList.add(2, 666);
        System.out.println(linkedList);
        linkedList.remove(2);
        System.out.println(linkedList);

    }
}