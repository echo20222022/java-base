package com.base.algorithm.leetcode;


/**

 最小栈.

 双栈、单栈

 * */
public class _155_MinStack {

    private Node head;
    public void push(int x) {
        if (head == null) {
            head = new Node(x, x);
        } else {
            head = new Node(x, Math.min(x, head.min), head);
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        int val;
        int min;
        Node next;

        private Node(int val, int min) {
            this(val, min, null);
        }

        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }



   /* Stack<Integer> data;
    Stack<Integer> minData;

    public _155_MinStack() {
        data = new Stack<>();
        minData = new Stack<>();
    }

    public void push(int val) {
        data.push(val);
        if (minData.isEmpty() || val <= minData.peek()) {
            minData.push(val);
        }
    }

    public void pop() {
        if (data.peek() == minData.peek()) {
            minData.pop();
        }
        data.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return minData.peek();
    }*/



}
