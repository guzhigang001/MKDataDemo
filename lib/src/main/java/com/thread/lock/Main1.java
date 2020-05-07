package com.thread.lock;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/5/8 0:53
 * @UpdateUser:
 * @UpdateDate: 2020/5/8 0:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Main1 {
    public static void main(String[] args) {


        try {
            MyService1 service1 = new MyService1();

            MyThread1 thread1 = new MyThread1(service1);

            thread1.start();
            Thread.sleep(3000);
            service1.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
