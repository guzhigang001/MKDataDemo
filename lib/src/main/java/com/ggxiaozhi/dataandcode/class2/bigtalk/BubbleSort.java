package com.ggxiaozhi.dataandcode.class2.bigtalk;

import com.ggxiaozhi.dataandcode.class2.SortTestHelper;

import java.util.logging.SocketHandler;

/**
 * Create by ggxz
 * 2020/3/30
 * description:书中冒泡排序三种方式
 * <p>
 * 冒泡排序还有一个特点 内测循环一次 冒泡出一个值  一个有n个值就要循环n次 外层的i内层没有用到引用
 */
@SuppressWarnings("unchecked")
public class BubbleSort {

    public static void sort1(Comparable[] arr) {

        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {//这里为什么是-1得呢？ 因为 我们遍历的时候是i 和 i+1比较 这样i+1会越界

            for (int j = 0; j < length - 1 - i; j++) {//length-1 道理与上面相同 -i是因为 循环i次 就有排好序i个 所以再数组从后开始算有i个排好序了

                if (arr[j].compareTo(arr[j + 1]) > 0) {//升序
                    Comparable temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序优化，如果遍历到数组是有序的 就跳出循环
     * <p>
     * <p>
     * 这个优化和下面的优化在于当遍历到最后判断剩下的元素是否有序 有序直接break
     *
     * @param arr
     */
    public static void sort2(Comparable[] arr) {

        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {

            boolean isSort = true;
            for (int j = 0; j < n - 1 - i; j++) {

                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    Comparable temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSort = false;//这个表示位的作用是在于 如果从j=0遍历到最后都是有序的  那么这个数组就是有序的
                    //直接再下面跳出循环
                }


            }

            if (isSort) {
                break;
            }
        }

    }

    public static void sort3(Comparable[] arr) {

        int n = arr.length;
        //记录最后一次交换位置的 下标  这样下次我们直接遍历到这里 因为lastIndex之后的都是有序的了
        int lastIndex = 0;
        //无序数组的边界 下次遍历直接就遍历到这里就可以了
        int sortBorder = n - 1;
        for (int i = 0; i < n - 1; i++) {

            boolean isSort = true;
            for (int j = 0; j < sortBorder; j++) {

                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    Comparable temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSort = false;//这个表示位的作用是在于 如果从j=0遍历到最后都是有序的  那么这个数组就是有序的
                    //直接再下面跳出循环
                    sortBorder = j;
                }


            }

            if (isSort) {
                break;
            }
        }

    }

    private static void swap(Comparable[] arr, int minIndex, int i) {
        Comparable min = arr[minIndex];
        arr[minIndex] = arr[i];
        arr[i] = min;
    }

    public static void main(String[] args) {
        Integer[] integers = {3, 5, 4, 8, 2, 1};
        SortTestHelper.printArray(integers);
        sort2(integers);
        SortTestHelper.printArray(integers);
    }
}
