package com.ggxiaozhi.dataandcode.class3;

import com.ggxiaozhi.dataandcode.class2.SortTestHelper;

/**
 * Create by ggxz
 * 2020/3/31
 * description: 这个归并排序好理解一些
 * 归并排序 联系 一个非常好的讲解
 * https://www.cnblogs.com/chengxiao/p/6194356.html
 * 但是这个算法要更消耗时间
 * 4nlogn 同时多了一次 最后的赋值
 */
@SuppressWarnings({"LoopConditionNotUpdatedInsideLoop", "unchecked"})
public class MergeSort2 {


    public static void main(String[] args) {
        Comparable[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
        sort(array);
    }

    public static void sort(Comparable[] array) {

        Comparable[] temp = new Comparable[array.length];
        sort(array, 0, array.length - 1, temp);
        SortTestHelper.printArray(array);
    }

    private static void sort(Comparable[] arr, int l, int r, Comparable[] temp) {

        if (l < r) {
            int mid = l + (r - l) / 2;
            sort(arr, l, mid, temp);
            sort(arr, mid + 1, r, temp);
            merge(arr, l, mid, r, temp);
        }
    }

    private static void merge(Comparable[] arr, int l, int mid, int r, Comparable[] temp) {

        int i = l;//左边边界下标
        int j = mid + 1;//右边边界下标
        int t = 0;//temp数组要存放元素的位置

        while (i <= mid && j <= r) {//左右分组都没有遍历结束

            //左<=右 保持稳定性
            if (arr[i].compareTo(arr[j]) <= 0) {

                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }

        while (i <= mid) {//左边还有元素
            temp[t++] = arr[i++];
        }
        while (j <= r) {//右边还有元素
            temp[t++] = arr[j++];
        }

        t = 0;
        while (l <= r) {
            arr[l++] = temp[t++];
        }
    }
}
