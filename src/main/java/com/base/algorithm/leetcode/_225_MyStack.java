package com.base.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class _225_MyStack {

    Queue<Integer> queue1 ;
    Queue<Integer> queue2;

    public _225_MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue1.add(x);
    }

    public int pop() {
        while (queue1.size() > 1) {
            queue2.add(queue1.remove());
        }
        int n = queue1.remove();
        while (!queue2.isEmpty()) {
            queue1.add(queue2.remove());
        }
        return n;
    }

    public int top() {
        int n = 0;
        while (!queue1.isEmpty()) {
            n = queue1.remove();
        }
        return n;
    }

    public boolean empty() {
        return false;
    }
}
