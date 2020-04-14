package com.ggxiaozhi.leetcode.class4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.util.Pair;

/**
 * Create by ggxz
 * 2020/4/13
 * description: set和map相关的算法
 * 242 202 290 205 (451)
 * (15 18  16 149)
 */
@SuppressWarnings("SuspiciousMethodCalls")
public class Solution {

    /**
     * 242 暴力解法 时间3n 空间2n
     */
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length())
            return false;

        //<元素，元素出现的次数>
        Map<Character, Integer> maps = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (maps.containsKey(c)) {
                maps.put(c, maps.get(c) + 1);
            } else {
                maps.put(c, 1);
            }
        }

        Map<Character, Integer> mapt = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (maps.containsKey(c)) {
                if (mapt.containsKey(c)) {
                    mapt.put(c, mapt.get(c) + 1);
                } else {
                    mapt.put(c, 1);
                }
            } else {
                return false;
            }
        }
        for (Character character : maps.keySet()) {
            Integer vs = maps.get(character);
            Integer vt = mapt.get(character);
            if (!vs.equals(vt))
                return false;
        }
        return true;
    }

    /**
     * 242 优化 根据题意知道 字符中只包含小写字母  可以使用数组映射 时间n 空间 1
     */
    public static boolean isAnagram2(String s, String t) {

        if (s.length() != t.length())
            return false;
        if (s.isEmpty())
            return true;
        int n = s.length();//因为长度相等 那么取值谁都行
        int[] arr = new int[26];
        for (int i = 0; i < n; i++) {
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 202. 快乐数
     */
    public static boolean isHappy(int n) {
        String value = String.valueOf(n);

        Set<Integer> set = new HashSet<>();
        int res = 0;
        while (true) {

            for (int i = 0; i < value.length(); i++) {
                char c = value.charAt(i);
                res = res + ((int) Math.pow(c - '0', 2));
            }
            if (res == 1)
                return true;
            if (set.contains(res)) {
                return false;
            } else {
                set.add(res);
                value = String.valueOf(res);
                res = 0;
            }
        }
    }


    /**
     * 202. 快乐数 快慢指针 看时间复杂度应该差不多 一个数量级 但是没有辅助空间的消耗
     */
    public static boolean isHappy2(int n) {


        int slow = n, fast = n;
        do {

            slow = sum(slow);
            fast = sum(fast);
            fast = sum(fast);

        } while (slow != fast);

        return slow == 1;
    }

    public static int sum(int n) {
        int sum = 0;
        while (n > 0) {

            int b = n % 10;//个位数
            sum = sum + b * b;
            n = n / 10;//向高位再去值
        }
        return sum;
    }

    /**
     * 290. 单词规律  利用HashMap k存pattern value存str单词
     * 在pattern中出现重复的单词判断上个单词是什么 如果不一致
     * 那么这个就是错的
     * //TODO 要注意 相互11对应  aaaa->baba  baba->aaaa
     */
    public boolean wordPattern(String pattern, String str) {
        Map<String, Character> map = new HashMap<>();

        String[] split = str.split(" ");
        if (split.length != pattern.length()) {
            return false;
        }

        for (int i = 0; i < split.length; i++) {
            String key = split[i];
            if (!map.containsKey(key)) {
                if (map.containsValue(pattern.charAt(i)))//这种情况要判断 相同的value是不是相同的key 这是一一对应的
                    return false;
                map.put(key, pattern.charAt(i));
            } else {
                Character value = map.get(key);

                if (!value.equals(pattern.charAt(i)))
                    return false;
            }
        }
        return true;
    }

    /**
     * 205. 同构字符串 与290一致 这里换一种思路
     */
    public boolean isIsomorphic(String s, String t) {

        int nS = s.length();
        int nT = t.length();
        if (nS != nT)
            return false;

        return wordPatternHelper(s).equals(wordPatternHelper(t));
    }

    public static String wordPatternHelper(String arr) {
        //<单词，代表的数字>
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        int count = 0;

        for (int i = 0; i < arr.length(); i++) {
            Character s = arr.charAt(i);
            if (!map.containsKey(s)) {
                map.put(s, count);
                builder.append(count);
                count++;
            } else {
                map.put(s, map.get(s));
                builder.append(map.get(s));
            }
        }
        return builder.toString();
    }

    /**
     * 1. 两数之和 利用HashMap 遇到一个元素 用target去减掉这个数 然后再剩下的数组中去找是否有这个值
     * 注意 这个和之前的167号问题不同 这个是无序的  那么我们就不可以用对撞指针的思想去处理这个
     */
    public int[] twoSum(int[] nums, int target) {

        //<nums中的值,下标>
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {

            int res = target - nums[i];

            if (map.containsKey(res)) {
                ans[0] = map.get(res);
                ans[1] = i;
                return ans;
            } else {
                map.put(nums[i], i);
            }
        }
        return nums;
    }

    /**
     * 451. 根据字符出现频率排序  优先队列
     */
    public String frequencySort(String s) {


        //<字符，这个字符对应的出现的次数>
        PriorityQueue<Pair<Character, Integer>> queue = new PriorityQueue<>(new Comparator<Pair<Character, Integer>>() {
            @Override
            public int compare(Pair<Character, Integer> p1, Pair<Character, Integer> p2) {
                return p2.getValue() - p1.getValue();
            }
        });

        Map<Character, Integer> map = new TreeMap<>();

        int n = s.length();
        if (n == 0)
            return "";
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        for (Character character : map.keySet()) {
            Pair<Character, Integer> pair = new Pair<>(character, map.get(character));
            queue.add(pair);
        }

        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            Pair<Character, Integer> poll = queue.poll();
            for (Integer integer = 0; integer < poll.getValue(); integer++) {
                builder.append(poll.getKey());
            }
        }
        return builder.toString();
    }

    /**
     * 451. 根据字符出现频率排序  优先队列 优化  少了一些判断 数组要比 map效率还是要高些 尽管 都是O1
     */
    public String frequencySort2(String s) {


        //<字符，这个字符对应的出现的次数>
        PriorityQueue<Pair<Character, Integer>> queue = new PriorityQueue<>(new Comparator<Pair<Character, Integer>>() {
            @Override
            public int compare(Pair<Character, Integer> p1, Pair<Character, Integer> p2) {
                return p2.getValue() - p1.getValue();
            }
        });

        int[] ints = new int[128];

        int n = s.length();
        if (n == 0)
            return "";
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            ints[c]++;
        }


        for (int i = 0; i < ints.length; i++) {
            int anInt = ints[i];
            if (anInt != 0) {
                queue.add(new Pair<>((char) i, anInt));
            }
        }

        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            Pair<Character, Integer> poll = queue.poll();
            for (Integer integer = 0; integer < poll.getValue(); integer++) {
                builder.append(poll.getKey());
            }
        }
        return builder.toString();
    }

    /**
     * 454. 四数相加 II   利用1号问题思想 将C+D的每一种可能 放入一个查找表中
     * 这里的ABCD长度是相等的
     */
    public static int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int N = A.length;
        if (N == 0)
            return 0;

        //<CD数组的和，这个和出现的次数>
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int c = C[i];
            for (int j = 0; j < N; j++) {
                int d = D[j];
                if (map.containsKey(c + d)) {
                    map.put(c + d, map.get(c + d) + 1);
                } else {
                    map.put(c + d, 1);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            int b = B[i];
            for (int j = 0; j < N; j++) {
                int a = A[j];
                if (map.containsKey(0 - (a + b))) {
                    res = res + map.get(0 - (a + b));
                }
            }
        }
        return res;
    }

    /**
     * 49. 字母异位词分组  计数排序  //TODO 最后也没解出来 太难了吧
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    /**
     * 49. 字母异位词分组  字符串拼接
     * 事项就 对于abc来说每个字母只出现一次 那么他拼接的字符串为a1b1c1
     * 那么a1b1c1 对应的为 abc bac cab
     * 这样我们没得到一个字符串先转化字符串 变成a1b1c1这个样子  然后作为key存入map
     * 那么出现字母异位的 a1b1c1格式一定是相同人的
     * <p>
     * //TODO 还有字母排序法  利用  Arrays.sort(); 将所有s排序 然后比较
     */
    public static List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0)
            return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {

            int[] chars = new int[26];
            for (int i = 0; i < s.length(); i++) {
                chars[s.charAt(i) - 'a']++;
            }

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != 0) {
                    builder.append(((char) i + 'a')).append(chars[i]);//a1b2c1 表示abbc bbac cbba abcb... 这个作为key
                }
            }
            String key = builder.toString();
            if (map.containsKey(key)) {
                map.get(key).add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(key, list);
            }

        }
        return new ArrayList<>(map.values());
    }


    /**
     * 447. 回旋镖的数量
     * <p>
     * 根据题意 如果a点 到b点和c点的距离相等 那么 abc，acb 是2组 2*(2-1)
     * 同理 a->bcd  abc acb abd adb acd adc 6组 3*(3-1)
     * a->bcde  abc acb abd adb abe aeb acd adc ace aec ade aed4*(4-1) 12组
     * <p>
     * 这个是求和 2+6+12=20
     */
    public static int numberOfBoomerangs(int[][] points) {


        //<点i到其他所有相同点的距离，这个距离下出现的频次>
        //两点间的距离公司 (x1-x2)^2+(y1-y2)^2结果开根号
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            map.clear();//优化避免重复创建
            //Map<Integer, Integer> map = new HashMap<>();//这里要每次创建map map不能放到外面 否则统计的是2个点的 不是三个点的 //TODO 这里还是没太理解 以后需要多看下
            int[] point = points[i];

            for (int j = 0; j < points.length; j++) {
                if (i != j) {//取的时候 是和其他点比较所以不和自己再比较了
                    int dist = distance(point, points[j]);
                    if (map.containsKey(dist)) {
                        map.put(dist, map.get(dist) + 1);
                    } else {
                        map.put(dist, 1);
                    }
                }
            }
            for (Integer key : map.keySet()) {
                if (map.get(key) > 1) {//这里要求的三个点 所以频率如果为1 那么就说明只有一个点和i点相等 不符合要求
                    Integer value = map.get(key);
                    res = res + value * (value - 1);

                }

            }
        }


        return res;
    }

    /**
     * 避免整型溢出 这里不开根号 2个开根号的距离相同 那么不开根号也是相同的
     */
    private static int distance(int[] x, int[] y) {
//        return (x[/**/0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]);
       return (int) (Math.pow((x[0] - y[0]), 2) + Math.pow((x[1] - y[1]), 2));
    }

    public static void main(String[] args) {
        System.out.println(numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {2, 0}}));
    }
}
