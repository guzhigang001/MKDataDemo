package com.ggxiaozhi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javafx.util.Pair;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/4/6 15:31
 * @UpdateUser:
 * @UpdateDate: 2020/4/6 15:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * <p>
 * 关于字符串算法 可以用
 * <p>
 * 快慢指针 ： 283 27 26 80(88 215)
 * 对撞指针 ： 75 167 (125 344 245 11 )
 * 滑动窗口 ：
 */
@SuppressWarnings({"MismatchedReadAndWriteOfArray", "StatementWithEmptyBody"})
public class Solution {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode(int x, ListNode next) {
            val = x;
            this.next = next;
        }
    }

    //141. 环形链表
    public boolean hasCycle(ListNode head) {


        //龟兔赛跑p1 每次有1个节点 p2每次走2个节点 如果有环 那么p2会追上p1 并接首次相遇p2比p1夺走了一个环
        //如果p2 遍历到了最后一个节点 就表示没有环
        ListNode p1 = head, p2 = head;


        while (p2 != null && p2.next != null) {

            p1 = p1.next;
            p2 = p2.next.next;

            if (p2 == p1) {
                return true;
            }
        }

        //循环中没有返回就说明 没有环
        return false;
    }

    //求环的长度
    public static int cycleLength(ListNode head) {
        if (head == null || head.next == null) {
            return -1;
        }

        //龟兔赛跑p1 每次有1个节点 p2每次走2个节点 如果有环 那么p2会追上p1 并接首次相遇p2比p1夺走了一个环
        //如果p2 遍历到了最后一个节点 就表示没有环
        ListNode p1 = head, p2 = head;

        int n1 = 0, n2 = 0;

        while (p2.next != null && p2.next.next != null) {

            p1 = p1.next;
            n1 = n1 + 1;
            p2 = p2.next.next;
            n2 = n2 + 2;

            if (p2.val == p1.val) {
                return n2 - n1;
            }
        }

        //循环中没有返回就说明 没有环
        return -1;
    }

    //142. 环形链表 II
    public static ListNode detectCycle(ListNode head) {
        ListNode p1 = head, p2 = head;

        boolean isCycle = false;

        while (p2 != null && p2.next != null) {

            p1 = p1.next;
            p2 = p2.next.next;

            if (p2 == p1) {
                isCycle = true;
                break;
            }
        }

        if (isCycle) {
            p1 = head;
            while (p2 != null && p1 != p2) {
                p1 = p1.next;
                p2 = p2.next;
                if (p1 == p2)
                    break;
            }
            return p1;
        } else {
            return null;
        }
    }


    /**
     * 283. 移动零
     */
    public static void moveZeroes(int[] nums) {

        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {

            for (int j = 0; j < n - 1 - i; j++) {

                if (nums[j] == 0) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] nums, int j, int i) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 283. 移动零 视频方法  缺点 占用了O(n)的空间
     */
    public static void moveZeroes2(int[] nums) {

        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {

            if (nums[i] != 0) {
                list.add(nums[i]);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }

        for (int i = list.size(); i < n; i++) {
            nums[i] = 0;
        }


    }

    /**
     * 283. 移动零 视频方法 升级版本 需要额外遍历赋值0
     */
    public static void moveZeroes3(int[] nums) {

        int n = nums.length;
        int k = 0;//表示下一个不为0存放的位置 初始为0 因为是下一个不为0的位置 下面遍历从K开始 如果一个0没有k一直遍历到length
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }
        }

        if (k != n) {
            for (int i = k; i < n; i++) {

                nums[i] = 0;
            }
        }


    }

    /**
     * 283. 移动零 视频方法 升级版本
     */
    public static void moveZeroes4(int[] nums) {

        int n = nums.length;
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                if (i != k) {//防止自己和之际交换 优化
                    swap(nums, i, k);
                }
                k++;

            }
        }
    }

    /**
     * 27. 移除元素  根据题意 可以参考 283题解
     */
    public static int removeElement(int[] nums, int val) {

        int n = nums.length;
        int k = 0;//
        for (int i = 0; i < n; i++) {

            if (nums[i] != val) {
                if (i != k) {
                    swap(nums, i, k);
                }
                k++;
            }
        }
        return k;
    }

    /**
     * 26. 删除排序数组中的重复项n 解题思路：
     * 快慢指针   慢p 快 i
     * 快指针从1开始慢指针指向最后一个有序的数 如果快指针不等于p的元素 p+1和i交换 如果全是不重复元素 交换N此 所以(i - p != 1)优化
     */
    public static int removeDuplicates(int[] nums) {

        int n = nums.length;
        int p = 0;//默认是第一个元素
        for (int i = 1; i < n; i++) {
            if (nums[p] != nums[i]) {
                if (i - p != 1) {

                    swap(nums, i, p + 1);
                }
                p++;
            }
        }
        return p + 1;
    }

    /**
     * 80. 删除排序数组中的重复项 II  这个不符合要求 要求是控件复杂度为1
     */
    public static int removeDuplicates2(int[] nums) {

        int n = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            int key = nums[i];
            if (!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                Integer num = map.get(key);
                if (num < 2) {
                    map.put(key, 2);
                }
            }
        }

        int index = 0;

        for (Integer key : map.keySet()) {
            Integer value = map.get(key);
            if (value == 1) {
                nums[index++] = key;
            } else {
                nums[index++] = key;
                nums[index++] = key;
            }
        }

        return index;
    }


    /**
     * 80. 删除排序数组中的重复项 II 优化
     * 思路还是快慢指针  快指针i 慢指针k 计数count 默认是1 当count=2 时k不动 当i的值和k的值不相等那么交换i和k的位置
     */
    public static int removeDuplicates3(int[] nums) {
        int n = nums.length;
        int count = 1;
        int k = 0;
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int knum = nums[k];
            if (num != knum) {
                swap(nums, i, k + 1);
                count = 1;
                k++;
            } else {
                if (count < 2) {
                    if (i != k) {
                        swap(nums, i, k + 1);
                        count++;
                        k++;
                    }

                }
            }
        }

        return k + 1;
    }

    /**
     * 75. 颜色分类 利用计数排序思想  时间 O(n) 空间 O(1)
     */
    public void sortColors(int[] nums) {

        int[] count = new int[3];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }

        int index = 0;
        for (int i = 0; i < count[0]; i++) {
            nums[index++] = 0;
        }
        for (int i = 0; i < count[1]; i++) {
            nums[index++] = 1;
        }
        for (int i = 0; i < count[2]; i++) {
            nums[index++] = 2;
        }
    }

    /**
     * 75. 颜色分类 利用三路快排
     */
    public static void sortColors2(int[] nums) {

        int n = nums.length;

        quickSort(nums, 0, n - 1);

    }

    private static void quickSort(int[] nums, int l, int r) {


        int z = l - 1;
        int t = r + 1;

        int i = l;

        while (i < t) {
            if (nums[i] == 0) {
                swap(nums, z + 1, i);
                z++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, t - 1, i);
            }

        }

    }


    /**
     * 167. 两数之和 II - 输入有序数组 n^2
     */
    public int[] twoSum(int[] numbers, int target) {

        int n = numbers.length;
        int[] arr = new int[2];
        for (int i = 0; i < n; i++) {

            int prev = numbers[i];
            for (int j = i + 1; j < n; j++) {

                int next = numbers[j];
                if ((prev + next) == target) {
                    arr[0] = i + 1;
                    arr[1] = j + 1;
                }

            }
        }
        return arr;
    }

    /**
     * 167. 两数之和 II - 输入有序数组  优化  利用二分查找法 nlogn
     */
    public int[] twoSum2(int[] numbers, int target) {

        int n = numbers.length;
        int r = n - 1;
        int[] arr = new int[2];
        for (int i = 0; i < n; i++) {
            int temp = numbers[i];
            int l = i + 1;
            int tag = target - temp;
            while (l <= r) {
                int mid = l + (r - l) / 2;
                int number = numbers[mid];
                if (tag > number) {
                    l = mid + 1;
                } else if (tag < number) {
                    r = mid - 1;
                } else {
                    arr[0] = i + 1;
                    arr[1] = mid + 1;
                    break;
                }
            }
        }
        return arr;
    }

    /**
     * 167. 两数之和 II - 输入有序数组  优化  利用二分查找法 n 双指针(对撞指针) i j
     * <p>
     * 如果 nums[i]+nums[j]>tag j-- 反之 i++ 这个不好理解 就动笔把大部分情况试一遍
     */
    public int[] twoSum3(int[] numbers, int target) {

        int n = numbers.length;
        int j = n - 1;
        int i = 0;
        int[] arr = new int[2];
        while (i < j) {//注意这里不能用等于 比如是8 那么等于就相当于 nums[i]+nums[j]=tag 如果i,j=4 那么一个元素用了2次 不符合题意

            int left = numbers[i];
            int right = numbers[j];

            if (left + right == target) {
                arr[0] = i + 1;
                arr[1] = j + 1;
                break;
            } else if (left + right > target) {
                j--;
            } else {
                i++;
            }
        }
        return arr;
    }


    /**
     * 209. 长度最小的子数组  暴力解法 遍历所有的子数组 是对的 但是超出时间限制 n^3
     */
    public static int minSubArrayLen(int s, int[] nums) {

        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        //pair key=长度 vale->key 左边界 value又边界

        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                if (sum(nums, i, j) >= s) {
                    minLen = Math.min(minLen, j - i);
                }
            }
        }
        if (minLen == Integer.MAX_VALUE)
            return 0;
        return minLen + 1;
    }

    /**
     * 209. 长度最小的子数组  暴力解法 遍历所有的子数组 是对的 但是超出时间限制
     * 优化 先计算出 和 n+n^2=n^2
     */
    public static int minSubArrayLen2(int s, int[] nums) {

        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        //pair key=长度 vale->key 左边界 value又边界

        int[] sums = new int[n + 1];//留出第一个元素位置 为0 方便累加
        sums[0] = 0;
        for (int i = 1; i < n + 1; i++) {//sums[1...i-1] 表示nums 0-1 之间的和
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                if (sums[j + 1] - sums[i] >= s) {
                    minLen = Math.min(minLen, j - i);//这个和的运算是 当前的位置减去 计算位置的前一个位置 等于 i到j 那笔用例子试验一下就知道了 理解的话不太好理解
                }
            }
        }
        if (minLen == Integer.MAX_VALUE)
            return 0;
        return minLen + 1;
    }

    /**
     * 209. 长度最小的子数组  优化 利用滑动窗口  滑动窗口的思想 ：  231243
     * 在一个数组中找到两个索引的范围，这个范围一直找到满足要求 这里是找到满足>=7 比如这里第一次是2到2 len=4 左右边界i=0 j=3
     * 然后缩小 i++ 这时不满足>=7 j++ 记录len 一直下去  这里注意 窗口的大小(len)是不一定的一直相等的 因为窗口一直移动 所以称为滑动窗口
     * 这里不好理解 可以拿这组数据用笔纸画一画
     */
    public static int minSubArrayLen3(int s, int[] nums) {

        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;

        int[] sums = new int[n + 1];//留出第一个元素位置 为0 方便累加
        sums[0] = 0;
        for (int i = 1; i < n + 1; i++) {//sums[1...i-1] 表示nums 0-1 之间的和
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        int minLen = nums.length + 1;

        int r = 0;//l 窗口左边界 r窗口右边界 [l...r]
        for (int l = 0; l < n; l++) {
            int len = minLen;
            while (r < n) {
                if (sums[r + 1] - sums[l] < s) {
                    r++;
                } else {
                    len = r - l + 1;
                    break;
                }
            }
            if (r == n)//如果r已经越界了 那么[l..r-1]之前是满足的那么r要++ 那么r就会越界==n 那么 此时 i就不用再向后判断移动了 因为越往后r-1不变 i++那么[l..r-1]的和越来越小了
                break;


            minLen = Math.min(len, minLen);
        }
        if (minLen == nums.length + 1)
            return 0;
        return minLen;
    }

    public static int sum(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }

        return arr[l] + sum(arr, l + 1, r);
    }


    /**
     * 209. 长度最小的子数组  更简单的优化 去掉统计和的算法
     */
    public static int minSubArrayLen4(int s, int[] nums) {

        int n = nums.length;
        int l = 0, r = -1;//[l...r] 滑动窗口的取值范围  这里初始值的时候让这个范围为一个无效的范围
        int len = n + 1;//最大的范围为n 那么n+1是永远不会达到的
        int sum = 0;//范围内的和
        while (l < n) {

            if (r + 1 < n && sum < s) {
                r++;
                sum = sum + nums[r];
            } else {//下面的判断不能放这里 因为 sum = sum + nums[r]; 这句话是r++后的 此时sum已经变了
                sum = sum - nums[l];
                l++;
            }

            if (sum >= s) {
                len = Math.min(len, r - l + 1);
            }
        }
        if (len == n + 1)
            return 0;
        return len;
    }

    public static void main(String[] args) {

//        ListNode node4 = new ListNode(-4);
//        ListNode node3 = new ListNode(0, node4);
//        ListNode node2 = new ListNode(2, node3);
//        ListNode node1 = new ListNode(3, node2);
//        node4.next = node2;
//
//        System.out.println(detectCycle(node1).val);
        int[] nums = {2, 3, 1, 2, 4, 3};
//        int[] nums = {1, 4, 4};
        System.out.println(minSubArrayLen3(10, nums));


    }
}
