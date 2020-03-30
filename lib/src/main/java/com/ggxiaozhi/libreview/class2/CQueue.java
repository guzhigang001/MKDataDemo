package com.ggxiaozhi.libreview.class2;

import java.util.Stack;

/**
 * @Description:  面试题09. 用两个栈实现队列
 * @Author: ggxz
 * @CreateDate: 2020/3/30 22:24
 * @UpdateUser:
 * @UpdateDate: 2020/3/30 22:24
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CQueue {

    Stack<Integer> stack1, stack2;

    public CQueue() {

        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {

        stack1.push(value);
    }

    public int deleteHead() {

        while (!stack1.isEmpty()) {

            Integer pop = stack1.pop();
            stack2.push(pop);
        }
        if (stack2.isEmpty())
            return -1;
        Integer pop = stack2.pop();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return pop;
    }
}
