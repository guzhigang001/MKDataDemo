package com.ggxiaozhi.reviewdac;

import java.util.Arrays;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/6/16 22:52
 * @UpdateUser:
 * @UpdateDate: 2020/6/16 22:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Main {

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        if (arr.length <= 0)
            return;

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                int cur = arr[j];
                if (cur > arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }


    }

    public static void selectSort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }


    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {

                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            //1.
            for (int j = 1; j < arr.length - i - 1; j++) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
//        System.out.println("selectSort1:" + Arrays.toString(selectSort1(array)));
//        System.out.println("insertSort1:" + Arrays.toString(insertSort1(array)));
//        System.out.println("insertSort2:" + Arrays.toString(insertSort2(array)));
//        System.out.println("bubbleSort1:" + Arrays.toString(bubbleSort1(array)));
//        System.out.println("bubbleSort2:" + Arrays.toString(bubbleSort2(array)));
//        System.out.println("cocktailSort1:" + Arrays.toString(cocktailSort1(array)));

        bubbleSort(array);

        for (int i : array) {
            System.out.println(i);
        }
    }
}
