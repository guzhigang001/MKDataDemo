package com.ggxiaozhi.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

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
 * 滑动窗口 ： 209 3 219 (217 438 76Hard)
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
     * 209. 长度最小的子数组  更简单的优化 去掉统计和的算法 利用滑动窗口思想
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

    /**
     * 3. 无重复字符的最长子串
     * 利用滑动窗口思想 l r 范围内最长 无重复字串
     * 当[l...r]中无重复字串 r++ 一直到r得位置和[l...r-1]中(此时得r的位置和前面的重复了)的‘某一个元素重复了’
     * 那么l移动到这个重复元素的位置+1 此时[l...r]中还是无重复元素
     * 那么如何快速判断新增的字符和[l...r]中的字符那个元素重复？
     * //todo 利用ASCII 码 开辟127个数组 然后每个字符的位置存这个字符出现的频率 如果为为1再出现就是重复的了
     */
    public static int lengthOfLongestSubstring(String s) {

        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int[] acsii = new int[127];
        int l = 0, r = -1;
        int maxLen = 1;//无重复的至少为1

        while (l < n) {//TODO 这里理解难点是是当出现重复元素
            //     l移动到前一个重复元素的+1比如abcb 当r=b时 去掉a,b
            //     在acsii数组中的值变成0 那么就会进入(r+1 < n && acsii[s.charAt(r + 1)] == 0这个循环
            //     笔和纸画一下  l++ r不变 什么时候b又==0了呢？就在  l移动到第一个b acsii[s.charAt(l)]--;

            if (r + 1 < n && acsii[s.charAt(r + 1)] == 0) {
                acsii[s.charAt(r + 1)]++;
                r++;
                maxLen = Math.max(r - l + 1, maxLen);//视频优化 测试但不太明显
            } else {
                acsii[s.charAt(l)]--;
                l = l + 1;

            }
        }
        return maxLen;
    }

    /**
     * 3. 无重复字符的最长子串
     * 上面的思想 优化：
     * 1. l++时不去比较 在r++时比较 因为l++的时候时要移动到前一个重复元素的后边
     * 2. acsii存的时上一个出现元素的下标
     */
    public static int lengthOfLongestSubstring2(String s) {

        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int[] acsii = new int[127];
        Arrays.fill(acsii, -1);
        int l = 0, r = 0;//这里用的时0
        int maxLen = 1;//无重复的至少为1
        //这种方式相当于快慢指针 r永远在l前面 比如 bb l=0 r=1
        // 最后l = acsii[s.charAt(r)] + 1=1 还在范围内 但是此时r=-1了 因为acsii[s.charAt(r)] = -1;
        //但是r++后循环结束 l永远不会大于r 因为r在范围内 那么l一点在范围内
        while (r < n) {

            if (acsii[s.charAt(r)] == -1) {
                acsii[s.charAt(r)] = r;
                maxLen = Math.max(r - l + 1, maxLen);
                r++;
            } else {
                if (acsii[s.charAt(r)] >= l) {//这里要注意 acsii[s.charAt(r)]当获取到上一个重复元素的位置可能比当前的l小
                    // 这是我们就不处理  比如abba 最后l=2 r=3  a1bba2 此时a重复了 但是l已经=2了就不去处理a1=0的情况
                    l = acsii[s.charAt(r)] + 1;
                }
                acsii[s.charAt(r)] = -1;

            }
        }
        return maxLen;
    }

    /**
     * 3. 无重复字符的最长子串 利用Map 思路和lengthOfLongestSubstring2一致
     */
    public static int lengthOfLongestSubstring3(String s) {

        int n = s.length();

        //Map<当前遍历到的元素，此元素最新的位置>
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0;
        int maxLen = 0;
        while (r < n) {
            char c = s.charAt(r);
            if (map.containsKey(c)) {
                l = Math.max(l, map.get(c));
            }
            maxLen = Math.max(maxLen, r - l + 1);
            map.put(c, r);//这里+1其中 key 值为字符，value 值为字符位置 +1，加 1 表示从字符位置后一个才开始不重复 和上面思想一样
            r++;
        }
        return maxLen;
    }

    /**
     * 219. 存在重复元素 II  类似问题217  和上面3号问题做对比
     * 利用map
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        //<出现的元素，这个元素的的下标> 这里注意 key存的是最近一次出现的下标 比如例子中的1231231 当最后一个1的时候是和中间的前一个1比较
        // 不能是第一个 所以遇见了重复的元素要更新这个元素的下标  所以也不用绝对值了
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];
            if (!map.containsKey(num)) {
                map.put(num, i);
            } else {
                if (i - map.get(num) <= k) {
                    return true;
                }
                map.put(num, i);
            }
        }
        return false;
    }


    /**
     * 219. 存在重复元素 II [1,2,3,1,2,3] 2
     * set+滑动窗口思想  和上面3号问题做对比 3号问题的滑动窗口不是固定的 这个滑动窗口的长度是固定的 也就set 的size一直是 k+1 1231 k=3 true
     */
    public static boolean containsNearbyDuplicate2(int[] nums, int k) {


        //遍历nums中范围的值 这里用list 删除元素会错乱
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
            if (set.size() == k + 1) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 220. 存在重复元素 III
     * <p>
     * 这个用map存不行 有重复元素会覆盖  如果创建一个实体 查找匹配就是On 整体On^2
     * <p>
     * 这里适合用有序set 因为 num-t<= x <=num+t 这个条件和适合 ceil 和floor
     *
     * //t不能为负数 否则没意义
     * 可以看问答区自己的提问 https://coding.imooc.com/learn/questiondetail/182144.html
     */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        if (nums.length == 0)
            return false;

        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            long num = nums[i];

            //num-t<= x <=num+t 查看是否存在这个x   找到大于 num-t的最小值 同时 这个值在[num-t,num+t]这个范围内就是成功的
            Long ceiling = set.ceiling((long)num - t);

            if (ceiling != null && ceiling <=(long) num + t)
                return true;

            set.add(num);
            if (set.size() == k + 1) {
                set.remove((long)nums[i - k]);
            }
        }
        return false;
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
        System.out.println(containsNearbyAlmostDuplicate(new int[]{1, 0, 1, -1}, 2,-2));

        TreeSet<Integer> integers = new TreeSet<>();
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            integers.add(1);
            hashSet.add(1);
        }

        System.out.println(integers);
        System.out.println(hashSet);
    }
}
