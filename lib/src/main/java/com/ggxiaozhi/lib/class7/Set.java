package com.ggxiaozhi.lib.class7;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/3/22 17:58
 * @UpdateUser:
 * @UpdateDate: 2020/3/22 17:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface Set<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    boolean isEmpty();

    int getSize();
}
