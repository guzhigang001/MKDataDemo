package com.ggxiaozhi.dataandcode.class2;

import com.ggxiaozhi.dataandcode.class2.algo.InsertionSort;
import com.ggxiaozhi.dataandcode.class2.algo.Main;

import java.util.Arrays;

/**
 * Create by ggxz
 * 2020/3/30
 * description:插入排序   核心思想是从前往后遍历 每遍历一个元素和前一个元素比较 知道遍历完前面所有的元素
 * <p>
 * <p>
 * 和选择排序得区别 选择排序是取出一个元素和剩下未排序元素比较
 * 插入是取出一个元素和已排序得比较
 */
public class Main5 {

    // 对arr[l...r]的区间使用InsertionSort排序
    public static void sort(Comparable[] arr, int l, int r){

        for( int i = l + 1 ; i <= r ; i ++ ){
            Comparable e = arr[i];
            int j = i;
            for( ; j > l && arr[j-1].compareTo(e) > 0 ; j--)
                arr[j] = arr[j-1];
            arr[j] = e;
        }
    }

    public static Comparable[] insertSort(Comparable[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {


            for (int j = i; j > 0; j--) {

                if (arr[j].compareTo(arr[j - 1]) < 0) {

                    Comparable temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
//                    swap(arr, j, j - 1);
                } else
                    break;
            }


        }
        return arr;
    }

    /**
     * 插入排序优化 每次不交换位置  当前要插入的元素存起来
     * 然后等到当前元素应该再得位置再赋值  相当于swap少了一次赋值
     *
     * @param arr
     * @return
     */
    public static Comparable[] insertSortUp(Comparable[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Comparable temp = arr[i];
            int j = i;
            for (; j > 0; j--) {
                if (temp.compareTo(arr[j - 1]) < 0) {
                    arr[j] = arr[j - 1];
                } else {
                    break;
                }
            }
            arr[j] = temp;


        }
        return arr;
    }

    public static Comparable[] insertSortUp1(Comparable[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Comparable temp = arr[i];
            int j = i;
            for (; j > 0 && temp.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;


        }
        return arr;
    }

    private static void swap(Comparable[] arr, int minIndex, int i) {
        Comparable min = arr[minIndex];
        arr[minIndex] = arr[i];
        arr[i] = min;
    }

    public static void main(String[] args) {

        Integer[] integers = SortTestHelper.generateRandomArray(20000, 0, 20000);

        Integer[] copy = Arrays.copyOf(integers, integers.length);


        System.out.println();
//        insertSortUp(integers);
//        insertSortUp1(copy);

//        SortTestHelper.printArray(integers);
//        SortTestHelper.printArray(copy);
//        SortTestHelper.testSort("com.ggxiaozhi.dataandcode.class2.Main5", "insertSortUp1", integers);
        SortTestHelper.testSort("com.ggxiaozhi.dataandcode.class2.algo.InsertionSort", "sort", integers);
        SortTestHelper.testSort("com.ggxiaozhi.dataandcode.class2.algo.SelectionSort", "sort", copy);
//        SortTestHelper.testSort("com.ggxiaozhi.dataandcode.class2.Main5", "insertSortUp1", copy);
//        SortTestHelper.testSort("com.ggxiaozhi.dataandcode.class2.Main", "selectSort", copy);

    }
}
