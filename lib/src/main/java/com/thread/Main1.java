package com.thread;

import java.util.concurrent.locks.Lock;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/5/7 22:06
 * @UpdateUser:
 * @UpdateDate: 2020/5/7 22:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Main1 {
    public static void main(String[] args) {

        try {
            MyThread thread = new MyThread();
            thread.setDaemon(false);
            thread.start();
            System.out.println(thread.getState());

            Thread.sleep(1000);
            System.out.println(thread.getState());
            thread.interrupt();
            Thread.currentThread().interrupt();
            System.out.println("是否停止 1？= " + Thread.interrupted());
            System.out.println("是否停止 2？= " + Thread.interrupted());

        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("a");
    }


}
