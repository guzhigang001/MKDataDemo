package com.ggxiaozhi.dataandcode.class3;

import com.ggxiaozhi.dataandcode.class2.SortTestHelper;
import com.sun.javafx.collections.SortHelper;

import java.util.Arrays;

/**
 * Create by ggxz
 * 2020/4/1
 * description:  快速排序
 * <p>
 * 优化前的快速排序 存在一个很大的缺点
 * 当数组近乎 有序的时候 会导致 取得基准值  永远 在开头 那么会导致 左边分配得非常小 右边非常大
 * 最后 分组会退化成一个链表   同时 当数据非常大 同时有序性很高 会出现StackOverflowError 因为 树得深度 越来越深 达到最大得深度了
 */
@SuppressWarnings("unchecked")
public class QuickSort {


    public static void sort(Comparable[] arr) {

        int n = arr.length - 1;
        quickSort(arr, 0, n );
    }

    //对arr[l...r]进行快速排序
    private static void quickSort(Comparable[] arr, int l, int r) {

        if (l >= r) {//当l=r的时候说明只有一个元素  那么他就是有序的
            return;
        }
        /**
         * TODO 优化1 : 上面边界的优化  对于小规模数组, 使用插入排序
         *
         *
         *  if( r - l <= 15 ){
         *      InsertionSort.sort(arr, l, r);
         *      return;
         *  }
         *
         *
         */

        int p = partition(arr, l, r);

        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, r);


    }

    /**
     * 对arr[l....r]进行划分
     * 返回p 使得arr[l+1...p-1]<arr[p]; arr[p+1....r]>arr[p]
     * <p>
     * 也就是
     * [A,B,C,p,D,E,F]
     * A下标l
     * F下标r
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    private static int partition(Comparable[] arr, int l, int r) {


        // TODO 最重要的优化：  随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        // 这样我们和l交换 这样退化成一个链表 也就是O(n*n)的概率是非常低的
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        //我们找到这个数组中第一个位置 也就是l的位置 为基准数
        Comparable v = arr[l];

        //我们对arr[l+1...j]<p arr[j+1....i) >p 这里注意i的区间
        //同时我们先找到 分组 最后分完后 我们将p插入到指定的位置

        int j = l;//

        for (int i = l + 1; i <= r; i++) {

            if (arr[i].compareTo(v) > 0) {
                //这种情况我们什么都不做 直接让i++就好
            }

            //这里如果等于 我们划分到左边 直接不处理 让元素不动
            if (arr[i].compareTo(v) < 0) {
                //这个时候让右边的元素放到左边
                swap(arr, j + 1, i);
                j++;
            }
        }
        swap(arr, j, l);

        return j;
    }

    //TODO 这个交换不能像之前的那样覆盖去交换
    private static void swap(Comparable[] arr, int c1, int c2) {

        Comparable temp = arr[c1];
        arr[c1] = arr[c2];
        arr[c2] = temp;

    }

    // 测试 QuickSort
    public static void main(String[] args) {

        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
//        int N = 1000000;
//        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(N, 10);
//        Integer[] integers = Arrays.copyOf(arr, arr.length);
//        SortTestHelper.testSort("com.ggxiaozhi.dataandcode.class3.QuickSort", "sort", arr);
//
//
//        SortTestHelper.testSort("com.ggxiaozhi.dataandcode.class3.MergeSort", "sort", integers);
        Comparable[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
        sort(array);
        SortTestHelper.printArray(array);
    }

}
