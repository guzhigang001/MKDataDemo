package com.ggxiaozhi.review.class3;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/3/16 20:53
 * @UpdateUser:
 * @UpdateDate: 2020/3/16 20:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface Stack<E> {

    void push(E e);

    E pop();

    E peek();

    boolean isEmpty();

    int getSize();


}
