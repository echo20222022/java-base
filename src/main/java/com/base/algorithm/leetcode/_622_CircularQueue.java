package com.base.algorithm.leetcode;

//设计一个循环队列
public class _622_CircularQueue {

    private int[] data;
    private int size;

    private int front;
    private int end;

    public _622_CircularQueue(int k) {
        data = new int[k + 1];
        size = 0;

        front = 0;
        end = 0;
    }

    public boolean enQueue(int value) {
        if ((end + 1) % data.length == front) {
            return false;
        }
        data[end] = value;
        end = (end + 1) % data.length;
        size ++;
        return true;
    }

    public boolean deQueue() {
        if (front == end) {
            return false;
        }
        front = (front + 1) % data.length;
        size --;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return data[front];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        if (end == 0) {
            return data[data.length - 1];
        }
        return data[(end - 1) % data.length];
    }

    public boolean isEmpty() {
        return front == end;
    }

    public boolean isFull() {
        return (end + 1) % data.length == front;
    }

}
