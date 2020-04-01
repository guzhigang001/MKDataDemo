package com.ggxiaozhi.dataandcode.class2;

import java.util.Arrays;

/**
 * @Description: 主要对之前学习的排序算法回顾 和复习
 * @Author: ggxz
 * @CreateDate: 2020/4/1 22:01
 * @UpdateUser:
 * @UpdateDate: 2020/4/1 22:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ReViewSort {
    public static void main(String[] args) {
        int[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
//        System.out.println("selectSort1:" + Arrays.toString(selectSort1(array)));
//        System.out.println("insertSort1:" + Arrays.toString(insertSort1(array)));
//        System.out.println("insertSort2:" + Arrays.toString(insertSort2(array)));
//        System.out.println("bubbleSort1:" + Arrays.toString(bubbleSort1(array)));
//        System.out.println("bubbleSort2:" + Arrays.toString(bubbleSort2(array)));
//        System.out.println("cocktailSort1:" + Arrays.toString(cocktailSort1(array)));
        System.out.println("shellSort:" + Arrays.toString(shellSort(array)));
    }

    /**
     * 选择排序基础版
     */
    public static int[] selectSort1(int[] arr) {

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            swap(arr, minIndex, i);
        }
        return arr;
    }

    private static void swap(int[] arr, int minIndex, int i) {
        int temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }

    /**
     * 插入排序
     */
    public static int[] insertSort1(int[] arr) {

        int n = arr.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                } else
                    break;
            }
        }

        return arr;
    }


    /**
     * 插入排序  覆盖 减少一次赋值  循环修改
     */
    public static int[] insertSort2(int[] arr) {

        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int j;
            for (j = i; j > 0 && arr[j] < temp; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }

        return arr;
    }

    /**
     * 冒泡排序
     */
    public static int[] bubbleSort1(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }

        }
        return arr;
    }


    /**
     * 冒泡排序  检查最后左边的元素是不是有序的
     */
    public static int[] bubbleSort2(int[] arr) {
        int n = arr.length;
        boolean isSort = true;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    isSort = false;//如果排序了 那就是无序的
                }
            }

            if (isSort) {
                break;
            }
        }
        return arr;
    }


    /**
     * 冒泡排序  检查最后左边的元素是不是有序的  同时扩大有序区的范围
     */
    public static int[] bubbleSort3(int[] arr) {
        int n = arr.length;
        boolean isSort = true;
        int lastOrderIndex = n - 1;//记录上次遍历最后有序的位置
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < lastOrderIndex; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    isSort = false;//如果排序了 那就是无序的
                    lastOrderIndex = j;
                }
            }

            if (isSort) {
                break;
            }
        }
        return arr;
    }

    /**
     * 鸡尾酒排序
     */
    public static int[] cocktailSort1(int[] arr) {

        int n = arr.length;
        // 记录右侧最后一次交换的位置
        int lastRightExchangeIndex = 0;
        // 记录左侧最后一次交换的位置
        int lastLeftExchangeIndex = 0;
        // 无序数列的右边界，每次比较只需要比到这里为止
        int rightSortBorder = n - 1;
        // 无序数列的左边界，每次比较只需要比到这里为止
        int leftSortBorder = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean isSort = true;
            if (i % 2 == 0) {//偶数 从左往右

                for (int j = leftSortBorder; j < rightSortBorder; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                        isSort = false;
                        lastRightExchangeIndex = j;
                    }
                }
            } else {
                for (int j = rightSortBorder; j >leftSortBorder; j--) {
                    if (arr[j] < arr[j - 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j - 1];
                        arr[j - 1] = temp;
                        isSort = false;
                        lastLeftExchangeIndex = j;
                    }
                }
            }
            leftSortBorder = lastLeftExchangeIndex;
            rightSortBorder = lastRightExchangeIndex;
            if (isSort) {
                break;
            }
        }
        return arr;
    }

    public static int[] cockTailSort(int[] origin) {
        if (origin == null || origin.length == 0) {
            return new int[]{};
        }
        // 记录右侧最后一次交换的位置
        int lastRightExchangeIndex = 0;
        // 记录左侧最后一次交换的位置
        int lastLeftExchangeIndex = 0;
        // 无序数列的右边界，每次比较只需要比到这里为止
        int rightSortBorder = origin.length - 1;
        // 无序数列的左边界，每次比较只需要比到这里为止
        int leftSortBorder = 0;
        System.out.println("origin--->" + Arrays.toString(origin));
        int index = 0;
        for (int i = 0; i < origin.length - 1; i++) {
            boolean isSorted = true;
            if (i % 2 == 0) {
                System.out.print("从 " + leftSortBorder + " 比较到 " + rightSortBorder + ",");
                for (int j = leftSortBorder; j < rightSortBorder; j++) {
                    if (origin[j] > origin[j + 1]) {
                        int temp = origin[j];
                        origin[j] = origin[j + 1];
                        origin[j + 1] = temp;
                        isSorted = false;
                        lastRightExchangeIndex = j;
                    }
                    index++;
                }
            } else {
                System.out.print("从 " + rightSortBorder + " 比较到 " + leftSortBorder + ",");
                for (int j = rightSortBorder; j > leftSortBorder; j--) {
                    if (origin[j] < origin[j - 1]) {
                        int temp = origin[j];
                        origin[j] = origin[j - 1];
                        origin[j - 1] = temp;
                        isSorted = false;
                        lastLeftExchangeIndex = j;
                    }
                    index++;
                }
            }
            leftSortBorder = lastLeftExchangeIndex;
            rightSortBorder = lastRightExchangeIndex;
            System.out.println("第" + (i + 1) + "次比较后" + "--->" + Arrays.toString(origin) + ",isSorted--->" + isSorted);
            if (isSorted) {
                break;
            }
        }
        System.out.println("index--->" + index);
        System.out.println("sortedArray--->" + Arrays.toString(origin));
        return origin;
    }


    /**
     * 希尔排序 https://www.cnblogs.com/chengxiao/p/6104371.html
     */
    public static int[] shellSort(int[] arr) {
        int n = arr.length;
        //increment 设置增量
        for (int increment = n / 2; increment > 0; increment = increment / 2) {

            for (int i = increment; i < n; i++) {

                int j = i;

                while (j - increment >= 0 && arr[j] < arr[j - increment]) {
                    swap(arr, j, j - increment);
                    j -=  increment;
                }
            }
        }
        return arr;
    }
}
