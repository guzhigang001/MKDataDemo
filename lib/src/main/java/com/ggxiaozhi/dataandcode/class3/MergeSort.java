package com.ggxiaozhi.dataandcode.class3;


import com.ggxiaozhi.dataandcode.class2.algo.SortTestHelper;

import java.util.Arrays;

/**
 * Create by ggxz
 * 2020/3/31
 * description: 归并排序
 * https://www.cnblogs.com/chengxiao/p/6194356.html
 * <p>
 * 这递归的思想就是分-治  先把数据拆分成最小单元
 * 然后再从最小单元去归并回去  再归并的规程中去排序
 * <p>
 * 注意 这里再注意边界的限定
 * <p>
 * l 每个分组的左边界
 * r 每个分组的右边界
 * i 左边组的下标
 * j 右边组的下标
 * k 原数组中待插入的位置下标
 * mid 每次组分成更小的组的中间位置  一直分到l=r 也就是左边元素和右边元素是一个元素
 */
@SuppressWarnings("unchecked")
public class MergeSort {

    /**
     * 我们可以使用递归来实现归并排序 这个算法也可以用非递归(TODO 需要以后了解)
     *
     * @param arr
     */
    public static void sort(Comparable[] arr) {

        int n = arr.length;
        sort(arr, 0, n - 1);
//        SortTestHelper.printArray(arr);
    }

    // 递归使用归并排序,对arr[l...r]的范围进行排序
    private static void sort(Comparable[] arr, int l, int r) {
        //边界的判定 说明分到了只有一个元素
        if (l >= r) {
            return;
        }

        /**
         * TODO 优化2 : 上面边界的优化  对于小规模数组, 使用插入排序
         *
         *
         *  if( r - l <= 15 ){
         *      InsertionSort.sort(arr, l, r);
         *      return;
         *  }
         *
         *
         */
        int mid = l + (r - l) / 2;


        // 其实也就是将元数组拆分  具体的排序 在拆分后的返回过程中
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        //merge方法才是将上面拆分后的最小单元进行排序和归并
        if (arr[mid].compareTo(arr[mid + 1]) > 0)//TODO 优化1 : 这个 语句是优化 也就是 在排序的时候 如果已经是有序的 直接越过排序 直接进行下一次归并
            merge(arr, l, mid, r);


    }

    // 对arr[l...r]的区间使用InsertionSort排序
    public static void sortInsert(Comparable[] arr, int l, int r) {

        for (int i = l + 1; i <= r; i++) {
            Comparable e = arr[i];
            int j = i;
            for (; j > l && arr[j - 1].compareTo(e) > 0; j--)
                arr[j] = arr[j - 1];
            arr[j] = e;
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
                if (aux[i - l].compareTo(aux[j - l]) <= 0) {// 左半部分所指元素 < 右半部分所指元素

                    //右边大 放左边 或是左边和右边相等的时候 放左边 这样也可以保证归并排序的有序性-
                    arr[k] = aux[i - l];
                    i++;

                } else {//左边>右边  左边大
                    arr[k] = aux[j - l];
                    j++;
                }
        }

    }

    // 测试MergeSort
    public static void main(String[] args) {

        // Merge Sort是我们学习的第一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        // 注意：不要轻易尝试使用SelectionSort, InsertionSort或者BubbleSort处理100万级的数据
        // 否则，你就见识了O(n^2)的算法和O(nlogn)算法的本质差异：）
//        int N = 100000;
//        Integer[] arr = SortTestHelper.generateNearlyOrderedArray(N, 100);
//
//
//        SortTestHelper.testSort("com.ggxiaozhi.dataandcode.class3.MergeSort", arr);
        Comparable[] array = {5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10};
        sort(array);

        Person person = new Person("a", 4);
        Person person1 = new Person("b", 2);
        Person person2 = new Person("c", 1);
        Person person3 = new Person("d", 4);
        Comparable[] arr = {person, person1, person2, person3};
        sort(arr);
        SortTestHelper.printArray(arr);
        return;


    }

    static class Person implements Comparable<Person> {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(Person anOther) {
            if (this.age > anOther.age) {
                return 1;
            } else if (this.age < anOther.age) {
                return -1;
            } else
                return 0;
        }
    }
}
