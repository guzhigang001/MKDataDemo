package com.ggxiaozhi.lib.class3.Main_6;

import com.ggxiaozhi.lib.class3.main_5.ArrayQueue;

public class Main {

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue.toString());

            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue.toString());
            }
        }

        System.out.println("====================================");
        System.out.println("====================================");
        System.out.println("====================================");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();

        for (int i = 0; i < 10; i++) {
            loopQueue.enqueue(i);
            System.out.println(loopQueue.toString());

            if (i % 3 == 2) {
                loopQueue.dequeue();
                System.out.println(loopQueue.toString());
            }
        }
    }
}
