package com.ggxiaozhi.lib.class3.Main_6;

class MyCircularQueue {


    private int[] data;
    private int front, tail;

    /**
     * Initialize your data structure here. Set the size of the queue to be k.
     */
    public MyCircularQueue(int k) {
        data = new int[k + 1];
        front = 0;
        tail = 0;

    }

    /**
     * Insert an element into the circular queue. Return true if the operation is successful.
     */
    public boolean enQueue(int value) {

        if (isFull())
            return false;
        data[tail] = value;
        tail = (tail + 1) % data.length;

        return true;
    }

    /**
     * Delete an element from the circular queue. Return true if the operation is successful.
     */
    public boolean deQueue() {

        if (isEmpty())
            return false;

        data[front] = 0;
        front = (front + 1) % data.length;
        return true;
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        if (!isEmpty())
            return data[front];
        else
            return -1;
    }

    /**
     * Get the last item from the queue.
     */
    public int Rear() {
        if (!isEmpty())
            return data[(tail - 1 + data.length) % data.length];
        else
            return -1;
    }

    /**
     * Checks whether the circular queue is empty or not.
     */
    public boolean isEmpty() {

        return tail == front;
    }

    /**
     * Checks whether the circular queue is full or not.
     */
    public boolean isFull() {

        return (tail + 1) % data.length == front;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3

        System.out.println(circularQueue.enQueue(1));// 返回 true
        System.out.println(circularQueue.enQueue(2));  // 返回 true
        System.out.println(circularQueue.enQueue(3));  // 返回 true
        System.out.println(circularQueue.enQueue(4));  // 返回 false，队列已满
        System.out.println(circularQueue.Rear());  // 返回 3
        System.out.println(circularQueue.isFull());  // 返回 true
        System.out.println(circularQueue.deQueue()); // 返回 true
        System.out.println(circularQueue.enQueue(4));  // 返回 true
        System.out.println(circularQueue.Rear());  // 返回 4


    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
