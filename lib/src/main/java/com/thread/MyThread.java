package com.thread;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/5/7 22:09
 * @UpdateUser:
 * @UpdateDate: 2020/5/7 22:09
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MyThread extends Thread {
    public MyThread() {
        super();
        System.out.println(getState());
    }

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 50; i++) {

            System.out.println("i->" + i);
            if (i == 20) {
                try {
                    Thread.sleep(1000);
                    System.out.println(getState());

                } catch (InterruptedException e) {
                    System.out.println("MyThread InterruptedException");
                    e.printStackTrace();
                }
            }
        }

    }
}

