package com.ggxiaozhi.libreview.class2;

import java.util.Stack;

/**
 * @Description: 面试题 03.02. 栈的最小值
 * @Author: ggxz
 * @CreateDate: 2020/3/30 22:38
 * @UpdateUser:
 * @UpdateDate: 2020/3/30 22:38
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MinStack {

    Stack<Integer> stackA, stackB;

    /**
     * initialize your data structure here.
     */
    public MinStack() {

        stackA = new Stack<>();
        stackB = new Stack<>();
    }

    public void push(int x) {

        stackA.push(x);

        if (stackB.isEmpty() || stackB.peek() >= x) {
            stackB.add(x);
        }
    }

    public void pop() {

        Integer pop = stackA.pop();

        if (stackB.peek().equals(pop)) {
            stackB.pop();
        }
    }

    public int top() {

        return stackA.peek();
    }

    public int min() {

        return stackB.peek();
    }
}
