package com.ggxiaozhi.dataandcode.class3;

import com.ggxiaozhi.dataandcode.class2.algo.SortTestHelper;
import com.sun.javafx.collections.SortHelper;

import java.util.Arrays;
import java.util.logging.SocketHandler;

/**
 * Create by ggxz
 * 2020/3/31
 * description: 归并排序非递归写法 自下向上
 */
public class MergeSort3 {

    /**
     * @param arr
     * @param n   数组的长度下标
     */
    public static void sort(Comparable[] arr, int n) {

        for (int sz = 1; sz <= n; sz = sz + sz) {//层级 分几次 logn 也就是2^0 2^1 2^2 2^3 1 2 4 8

            for (int i = 0; i < n; i += sz + sz) {//找出每组的左右边界 i是左边界 每组l的值
                merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
            }
        }


    }


    /**
     * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
     * arr是原始数组 也就是我们最后排好序的数组
     * aux是临时数组 最后我们判断的是aux  输出的arr
     */
    private static void merge(Comparable[] arr, int l, int mid, int r) {


        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);
        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = l;
        int j = mid + 1;

        //这里要注意 元数组是[l...mid]或arr[mid+1...r]  这里的l 和mid+1都可能不是0
        //但是我们添加的时候是从0可是添加的 所以在使用 aux的时候要有偏移
        for (int k = l; k <= r; k++) {

            // 如果左半部分元素已经全部处理完毕
            if (i > mid) {
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) { // 如果右半部分元素已经全部处理完毕
                arr[k] = aux[i - l];
                i++;
            } else //左边<右边  右边大放右边
                if (aux[i - l].compareTo(aux[j - l]) < 0) {// 左半部分所指元素 < 右半部分所指元素

                    //右边大 放左边
                    arr[k] = aux[i - l];
                    i++;

                } else {//左边>右边  左边大 或是左边和右边相等的时候 放左边 这样也可以保证归并排序的有序性
                    arr[k] = aux[j - l];
                    j++;
                }
        }

    }

    public static void main(String[] args) {
//        Comparable[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
        Comparable[] array = {3, 1, 4};
        sort(array, array.length);
    }
}
