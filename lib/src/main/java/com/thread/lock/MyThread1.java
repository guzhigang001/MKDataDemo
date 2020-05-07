package com.thread.lock;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/5/8 0:50
 * @UpdateUser:
 * @UpdateDate: 2020/5/8 0:50
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyThread1 extends Thread {

    MyService1 service;

    public MyThread1(MyService1 service) {
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.await();
    }
}
