package com.ggxiaozhi.lib.class6.class6_9;


import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        int[] arr = {5, 3, 6, 4, 2};
//        int[] arr = {12, 3, 6, 9, 5, 2, 8, 15};
        BST<Integer> bst = new BST<>();
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }
        System.out.println(bst);

//        bst.preOrder();
//        System.out.println();
//        System.out.println();
//        bst.inOrder();
//        System.out.println();
//        System.out.println();
//        bst.postOrder();
//
//        bst.printByStackPre();
//        System.out.println(bst.maxDepthX());
//        System.out.println(bst.maxDepth());
    }


    public static int maxDepthX(TreeNode root) {

        if (root == null) {
            return 0;
        }
        TreeNode cur;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int depth = 1;
        while (!stack.isEmpty()) {

            depth = Math.max(depth, stack.size());

            cur = stack.pop();
            if (cur.right != null) {
                depth++;
                stack.push(cur.right);
            }

            if (cur.left != null) {
                depth++;
                stack.push(cur.left);
            }
        }

        return depth;

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
