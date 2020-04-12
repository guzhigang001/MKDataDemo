package com.ggxiaozhi.dataandcode.class3;

import com.ggxiaozhi.dataandcode.class2.Main5;
import com.ggxiaozhi.dataandcode.class2.SortTestHelper;

import java.util.logging.SocketHandler;

/**
 * Create by ggxz
 * 2020/4/1
 * description: 快速排序 三路排序
 *
 * 三路排序 可以保证当数据 存在大量重复元素的时候 避免 重复元素分到一侧 造成算法的时间复杂度 退变成0(n*n)
 */
@SuppressWarnings("unchecked")
public class QuickSort4 {


    public static void sort(Comparable[] arr) {

        int n = arr.length - 1;
        quickSort(arr, 0, n);
        SortTestHelper.printArray(arr);
    }

    /**
     * 三路排序
     *
     * @param arr
     * @param l
     * @param r
     */
    // 递归使用快速排序,对arr[l...r]的范围进行排序
    private static void quickSort(Comparable[] arr, int l, int r) {

        // 对于小规模数组, 使用插入排序
        if (r - l <= 3) {
            Main5.sort(arr, l, r);
            return;
        }

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        Comparable v = arr[l];

        //TODO 这个边界的取值范围的准则就是 初始时 保持 数组中的元素为空
        // 那么也就是保证 边界不能相等 比如 //[l+1..lt]<v lt=l 而不是lt=l+1 否则就会有 [l+1]这个位置的元素
        //[l+1..lt]<v
        int lt = l;
        //[gt...r] >v
        int gt = r + 1;
        //[lt+1...i-1]=v;
        int i = l + 1;

        while (i < gt) {

            //arr[i]<v
            if (i <= r && arr[i].compareTo(v) < 0) {
                swap(arr, lt + 1, i);
                i++;
                lt++;
            } else if (arr[i].compareTo(v) > 0) {
                swap(arr, i, gt - 1);
                gt--;
            } else {
                i++;
            }
        }

        //TODO 这里注意arr[l]=v 但是lt是指向<v的最后一个元素 交换之后lt指向了 ==v的第一个元素
        // 那么 在下次循环的时候就要lt-1
        swap(arr, lt, l);

        quickSort(arr, l, lt - 1);
        quickSort(arr, gt, r);

    }

    //TODO 这个交换不能像之前的那样覆盖去交换
    private static void swap(Comparable[] arr, int c1, int c2) {

        Comparable temp = arr[c1];
        arr[c1] = arr[c2];
        arr[c2] = temp;

    }



    public static void main(String[] args) {
        Comparable[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
        sort(array);
    }
}
