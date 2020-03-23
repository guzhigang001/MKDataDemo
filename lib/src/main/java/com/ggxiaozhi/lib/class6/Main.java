package com.ggxiaozhi.lib.class6;

public class Main {

    public static void main(String[] args) {

//        int[] arr = {5, 3, 6, 8, 4,2};
//        int[] arr = {12,3,6,9,5,2,8,15};
        int[] arr = {6,12,19,18,17,21,20};

        BST<Integer> bst = new BST<>();
        for (int i = 0; i < arr.length; i++) {
            bst.add(arr[i]);
        }

        System.out.println(bst);
        System.out.println(bst.maxDepth());


//        bst.preOrder();
//        System.out.println();
//        System.out.println();
//        bst.inOrder();
//        System.out.println();
//        System.out.println();
        bst.postOrder();


    }


}
