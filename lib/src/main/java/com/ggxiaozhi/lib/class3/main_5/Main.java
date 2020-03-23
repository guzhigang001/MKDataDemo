package com.ggxiaozhi.lib.class3.main_5;

import com.ggxiaozhi.lib.class2.Array;

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
    }
}
