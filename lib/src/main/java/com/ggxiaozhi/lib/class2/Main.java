
package com.ggxiaozhi.lib.class2;

public class Main {

    public static void main(String[] args) {


        Array<Integer> arr = new Array<>();
        for (int i = 0; i < 10; i++)
            arr.addLast(i);
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        for (int i = 0; i < 4; i++) {
            arr.removeFirst();
            System.out.println(arr);
        }


        System.out.println("=====================================");
        System.out.println("=====================================");
        System.out.println("=====================================");

        ArrayCopy<Integer> arrayCopy = new ArrayCopy<>();
        for (int i = 0; i < 10; i++)
            arrayCopy.addLast(i);
        System.out.println(arrayCopy);

        arrayCopy.add(1, 100);
        System.out.println(arrayCopy);

        arrayCopy.addFirst(-1);
        System.out.println(arrayCopy);

        arrayCopy.remove(2);
        System.out.println(arrayCopy);

        arrayCopy.removeElement(4);
        System.out.println(arrayCopy);

        arrayCopy.removeFirst();
        System.out.println(arrayCopy);

        for (int i = 0; i < 4; i++) {
            arrayCopy.removeFirst();
            System.out.println(arrayCopy);
        }
    }
}


