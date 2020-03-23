package com.ggxiaozhi.lib.class7.class7_9;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/3/22 22:26
 * @UpdateUser:
 * @UpdateDate: 2020/3/22 22:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface Map<K, V> {

    void add(K key, V value);

    V remove(K key);

    void set(K key, V newValue);

    V get(K key);

    boolean contains(K key);

    int getSize();

    boolean isEmpty();

}
