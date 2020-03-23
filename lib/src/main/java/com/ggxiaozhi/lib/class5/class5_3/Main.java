package com.ggxiaozhi.lib.class5.class5_3;


public class Main {

    public static void main(String[] args) {

        Solution solution = new Solution();
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(sum(arr, 0));


        int[] sums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(sums);
        System.out.println(solution.deleteNode(head, 6));
    }

    /**
     * @param arr 就和的数组
     * @param l   左端开始的坐标｛1，2，3，4，5｝= 1+sum(arr,1)
     * @return
     */
    public static int sum(int[] arr, int l) {

        if (l == arr.length) {
            return 0;
        }
        return arr[l] + sum(arr, l + 1);
    }
}
