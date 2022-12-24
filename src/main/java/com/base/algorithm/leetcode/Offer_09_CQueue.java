package com.base.algorithm.leetcode;

import java.util.LinkedList;

//用两个栈实现一个队列
public class Offer_09_CQueue {

    LinkedList<Integer> stack1;
    LinkedList<Integer> stack2;


    public Offer_09_CQueue() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (stack2.isEmpty()) {
            if (stack1.isEmpty()) {
                return -1;
            }
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
