package com.ggxiaozhi.dataandcode.class3;
import com.ggxiaozhi.dataandcode.class2.algo.SortTestHelper;

import java.util.*;
/**
 * Create by ggxz
 * 2020/4/1
 * description:
 */


@SuppressWarnings("unchecked")
public class QuickSort2 {

    // 我们的算法类不允许产生任何实例
    private QuickSort2(){}

    // 对arr[l...r]部分进行partition操作
    // 返回p, 使得arr[l...p-1] < arr[p] ; arr[p+1...r] > arr[p]
    private static int partition(Comparable[] arr, int l, int r){

        Comparable v = arr[l];

        int j = l; // arr[l+1...j] < v ; arr[j+1...i) > v
        for( int i = l + 1 ; i <= r ; i ++ )
            if( arr[i].compareTo(v) < 0 ){
                j ++;
                swap(arr, j, i);
            }

        swap(arr, l, j);

        return j;
    }

    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void sort(Comparable[] arr, int l, int r){

        if( l >= r )
            return;

        int p = partition(arr, l, r);
        sort(arr, l, p-1 );
        sort(arr, p+1, r);
    }

    public static void sort(Comparable[] arr){

        int n = arr.length-1;
        sort(arr, 0, n);
        SortTestHelper.printArray(arr);

    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // 测试 QuickSort
    public static void main(String[] args) {

        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
//        int N = 1000000;
//        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(N, 100);
//        SortTestHelper.testSort("com.ggxiaozhi.dataandcode.class3.QuickSort2", arr);


        Comparable[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
        sort(array);
        return;
    }
}