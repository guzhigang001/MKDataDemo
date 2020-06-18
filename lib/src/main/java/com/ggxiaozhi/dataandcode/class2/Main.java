package com.ggxiaozhi.dataandcode.class2;

import com.sun.javafx.scene.traversal.Algorithm;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Create by ggxz
 * 2020/3/30
 * description:选择排序
 * 选择排序得核心 是取出i个元素和剩下得所有元素比较 看i是不是最小得
 */
public class Main {


    public static void selectSort(Comparable[] arr) {

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 寻找[i, n)区间里的最小值的索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++)
                // 使用compareTo方法比较两个Comparable对象的大小
                if (arr[j].compareTo(arr[minIndex]) < 0)
                    minIndex = j;

            swap(arr, i, minIndex);
        }

    }

    private static void swap(Comparable[] arr, int minIndex, int i) {
        Comparable temp = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = temp;
    }

    public static void main(String[] args) {


//        Integer[] integers = {3, 5, 4, 8, 2, 1};
//
//        selectSort(integers);

        // 测试排序算法辅助函数
//        int N = 2000;
//        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
//
//        SortTestHelper.testSort("com.ggxiaozhi.dataandcode.class2.Main", "selectSort", arr);
//        SortTestHelper.printArray(arr);


    }


}
