package com.ggxiaozhi.review.class7;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/4/5 16:08
 * @UpdateUser:
 * @UpdateDate: 2020/4/5 16:08
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface Map<K, V> {

    void add(K key, V value);

    V remove(K key);

    void set(K key, V value);

    boolean contains(K key);

    V get(K key);

    boolean isEmpty();

    int getSize();
}
