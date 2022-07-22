package com.base.ds.line;

import com.sun.jdi.InternalException;

/**
 * 用数组实现一个固定长度的队列
 * 空：front == end
 * 满：(end + 1) % length = front
 * 入队：data[(end + 1) % length] = element
 * 出队：data[(front + 1) % length]
 * */
public class ArrayRQueue<T> {

    private T[] data;
    private int size;

    private int front;
    private int end;

    public ArrayRQueue(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;

        front = 0;
        end = 0;
    }

    public void enqueue(T e) {
        //整个数组中，空出来一位，用于区分空和满
        if ((end + 1) % data.length == front) {
            throw new InternalException("queue is full.");
        }
        data[end] = e;
        end = (end + 1) % data.length;
        size ++;
    }

    public T dequeue() {
        if (end == front) {
            throw new InternalException("queue is empty.");
        }
        T dequeueEle = data[front];
        front = (front + 1) % data.length;
        size --;
        return dequeueEle;
    }

    public boolean isEmpty() {
        return front == end;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = front;
              i != end; i = (i + 1) % data.length) {
            sb.append(data[i]);
            sb.append(" <- ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        /**

         [ 1 <- 2 <- 3 <- ]
         1
         [ 2 <- 3 <- ]
         [ 2 <- 3 <- 10 <- ]

         * */
        ArrayRQueue queue = new ArrayRQueue(4);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue);
        queue.enqueue(10);
        System.out.println(queue);
        System.out.println(queue.isEmpty());
    }

}
