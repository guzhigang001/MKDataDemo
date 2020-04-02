package com.ggxiaozhi.dataandcode.class2;

import java.util.Arrays;

/**
 * Create by ggxz
 * 2020/3/31
 * description: 希尔排序
 * 像这样逐步分组进行粗调，再进行直接插入排序的思想，就是希尔排序，
 * <p>
 * 使用的分组跨度（4，2，1），被称为希尔排序的增量，增量的选择可以有很多种，我们在示例中所用的逐步折半的增量方法
 * 是Donald Shell在发明希尔排序时提出的一种朴素方法，被称为希尔增量。
 * 希尔排序是插入排序的升级优化
 */
public class ShellSort {

    //5 8 6 3 9 2 1 7  这个是降序
    public static void sort(int[] array) {//希尔排序的增量

        int d = array.length;
        while (d > 1) {// 这里不能写>0 d再循环中最小值只能是2 这样2/2=1 相当于插入排序
            d = d / 2;
            for (int x = 0; x < d; x++) {//每次循环要有多少组按照插入排序来循环
                for (int i = x + d; i < array.length; i++) {
                    int temp = array[i];
                    int j;

                    for (j = i - d; j >= 0 && array[j] > temp; j = j - d) {
                        array[j + d] = array[j];
                    }
                    array[j + d] = temp;
                }
            }
        }
    }

    /**
     * 优化后的插入排序
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {

        int n = arr.length;
        for (int i = 1; i < n; i++) {

            int temp = arr[i];
            int j;
            for (j = i; j > 0 && arr[j - 1] > temp; j--) {
                arr[j] = arr[j - 1];
            }

            //j  是上面循环最后一次的值
            arr[j] = temp;
        }
    }

    public static int[] shellSort(int[] arr) {

        int n = arr.length;

        for (int gap = n / 2; gap > 0; gap = gap / 2) {

            for (int i = gap; i < n; i++) {

                int temp = arr[i];
                int j;

                for (j = i - gap; j >= 0 && arr[j] > temp; j = j - gap) {

                    arr[j + gap] = arr[j];
                }

                arr[j+gap] = temp;
            }

        }
        return arr;
    }

    public static void main(String[] args) {
        int[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
//        int[] array = {5, 8, 6, 3, 9, 2, 1, 7};
        System.out.println(Arrays.toString(shellSort(array)));
    }

}
