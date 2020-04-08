package com.ggxiaozhi.review.class13;

import com.ggxiaozhi.lib.class13.AVLTree;
import com.ggxiaozhi.lib.class13.BST;
import com.ggxiaozhi.lib.class13.FileOperation;

import java.util.ArrayList;

/**
 * Create by ggxz
 * 2020/4/8
 * description:
 */
public class Main {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

//            Collections.sort(words);

            // Test BST
            long startTime = System.nanoTime();

            com.ggxiaozhi.lib.class13.RBTree<String, Integer> bst = new com.ggxiaozhi.lib.class13.RBTree<>();
            for (String word : words) {
                if (bst.contains(word))
                    bst.set(word, bst.get(word) + 1);
                else
                    bst.add(word, 1);
            }

            for (String word : words)
                bst.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree lib: " + time + " s");


            // Test AVL Tree
            startTime = System.nanoTime();

            RBTree<String, Integer> avl = new RBTree<>();
            for (String word : words) {
                if (avl.contains(word))
                    avl.set(word, avl.get(word) + 1);
                else
                    avl.add(word, 1);
            }

            for (String word : words)
                avl.contains(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree review: " + time + " s");
        }

        System.out.println();
    }
}
