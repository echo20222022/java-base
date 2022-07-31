package com.base.ds.csdn.line;


/**
 * 用数组实现一个固定长度的队列
 * 空：front == end
 * 满：(end + 1) % length = front
 * 入队：data[(end + 1) % length] = element
 * 出队：data[(front + 1) % length]
 * */
public class EArrayRQueue<E> {

    private E[] data;
    private int size;

    private int front;
    private int end;

    //初始化
    public EArrayRQueue(int capacity) {
        data = (E[]) new Object[capacity];
        front = 0;
        end = 0;
    }

    public void enqueue(E e) {
        if ((end + 1) % data.length == front) {
            throw new IllegalArgumentException("queue is full");
        }
        data[end] = e;
        end = (end + 1) % data.length;
        size ++;
    }

    public E dequeue() {
        if (end == front) {
            throw new IllegalArgumentException("queue is empty.");
        }
        E dequeueEle = data[front];
        front = (front + 1) % data.length;
        size --;
        return dequeueEle;
    }

    public boolean isEmpty() {
        return front == end;
    }

    public int getSize() {
        return size;
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
        EArrayRQueue<Integer> queue = new EArrayRQueue(4);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue);
        queue.enqueue(10);
        System.out.println(queue);
        System.out.println(queue.isEmpty()  );
    }
}
