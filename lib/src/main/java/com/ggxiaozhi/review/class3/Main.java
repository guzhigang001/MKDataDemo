package com.ggxiaozhi.review.class3;

/**
 * @Description:
 * @Author: ggxz
 * @CreateDate: 2020/3/16 20:51
 * @UpdateUser:
 * @UpdateDate: 2020/3/16 20:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();

        for(int i = 0 ; i < 5 ; i ++){
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);

    }
}
