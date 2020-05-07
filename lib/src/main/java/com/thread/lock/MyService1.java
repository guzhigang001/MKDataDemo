package com.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/5/8 0:46
 * @UpdateUser:
 * @UpdateDate: 2020/5/8 0:46
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyService1 {
    Lock lock;

    Condition condition;

    public MyService1() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void await() {


        try {
            lock.lock();
            System.out.println("A");
            condition.await();
            System.out.println("B");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void signal() {
        lock.lock();

        condition.signal();
        lock.unlock();
    }
}