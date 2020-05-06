package com.text.interview;

import java.io.Serializable;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/5/6 22:38
 * @UpdateUser:
 * @UpdateDate: 2020/5/6 22:38
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MainTest {

    static class ArrayAlg {
        public static <T extends Comparable & Serializable> T getMiddle(T... a) {
            return a[a.length / 2];

        }
    }

    public static void main(String[] args) {
        Number middle = ArrayAlg.getMiddle(3.14, 1729, 0);

        System.out.println(middle);
    }
}
