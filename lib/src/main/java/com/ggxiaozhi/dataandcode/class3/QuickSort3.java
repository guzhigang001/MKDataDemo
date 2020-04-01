package com.ggxiaozhi.dataandcode.class3;

import com.ggxiaozhi.dataandcode.class2.Main5;
import com.ggxiaozhi.dataandcode.class2.SortTestHelper;
import com.ggxiaozhi.dataandcode.class2.algo.InsertionSort;

import java.util.Arrays;

/**
 * Create by ggxz
 * 2020/4/1
 * description: 快速排序    双路排序
 * <p>
 *  避免 数据相同非常多的时候 数据差非常小的时候的出现问题
 */
@SuppressWarnings("unchecked")
public class QuickSort3 {


    public static void sort(Comparable[] arr) {

        int n = arr.length - 1;
        quickSort(arr, 0, n);
    }

    //对arr[l...r]进行快速排序
    private static void quickSort(Comparable[] arr, int l, int r) {

//        if (l >= r) {//当l=r的时候说明只有一个元素  那么他就是有序的
//            return;
//        }
        if (r - l <= 15) {
            Main5.sort(arr, l, r);
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
     * 这个的特点就是  当出现了值相等的情况 我们不具体像第一版代码 给他划分到某一端  而是取分布在两端了
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    // 双路快速排序的partition
    // 返回p, 使得arr[l...p-1] <= arr[p] ; arr[p+1...r] >= arr[p]
    // 双路快排处理的元素正好等于arr[p]的时候要注意，详见下面的注释：）
    private static int partition(Comparable[] arr, int l, int r) {


        // TODO 最重要的优化：  随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot
        // 这样我们和l交换 这样退化成一个链表 也就是O(n*n)的概率是非常低的
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        //我们找到这个数组中第一个位置 也就是l的位置 为基准数
        Comparable v = arr[l];

        // arr[l+1...i) <= v; arr(j...r] >= v
        int i = l + 1;
        int j = r;

        while (true) {
            // 注意这里的边界, arr[i].compareTo(v) < 0, 不能是arr[i].compareTo(v) <= 0
            // 思考一下为什么?
            //TODO 因为 如果相等 就违背了我们这么设计的初衷
            // 如果我们存在很多相同的元素 原来的排序就会导致 相同的元素永远在一侧 ，如果这里加上等于号
            // [1,1,1,,1,1,1,2] 按照之前的逻辑 v我们取1  那么就会导致 一侧是全数据
            // 另一侧只有2  如过不加等于 那么 根据我们的逻辑会分成[111] [1112] 分布均匀


            while (i <= r && arr[i].compareTo(v) < 0) {
                i++;
            }
            // 注意这里的边界, arr[j].compareTo(v) > 0, 不能是arr[j].compareTo(v) >= 0
            // 思考一下为什么?
            while (j >= l + 1 && arr[j].compareTo(v) > 0) {
                j--;
            }
            // 对于上面的两个边界的设定, 有的同学在课程的问答区有很好的回答:)
            // 大家可以参考: https://www.jianshu.com/p/e0364a3166f9
            if (i > j) {
                break;
            }

            swap(arr, i, j);
            i++;
            j--;
        }

        swap(arr, l, j);
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
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(N, 10);
        Integer[] integers = Arrays.copyOf(arr, arr.length);
        SortTestHelper.testSort("com.ggxiaozhi.dataandcode.class3.QuickSort3", "sort", arr);


        SortTestHelper.testSort("com.ggxiaozhi.dataandcode.class3.MergeSort", "sort", integers);


    }

}
