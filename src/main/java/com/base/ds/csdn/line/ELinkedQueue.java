package com.base.ds.csdn.line;

import com.base.ds.line.LinkedRQueue;
import com.sun.jdi.InternalException;

/**
 * 基于链表实现的队列
 * 操作：入队、出队、遍历、打印
 * 思路：
 *  定义一个dummyHead
 *  定义一个tail，指向最后一个元素
 *  出队从链表头出
 *  入队从链表尾入，这样时间复杂度能够保证都是O(1)
 * */
public class ELinkedQueue<T> {

    private Node<T> dummyHead;
    private Node<T> tail;
    private int size;

    public ELinkedQueue() {

        /**
         初始状态
         dummyHead
             ^
            last

         * */
        dummyHead = new Node<>();
        tail = dummyHead;
        //dummyHead.next = tail;
        size = 0;
    }

    /**
     * 入队.
     * @param e
     * */
    public void enqueue(T e) {
        //利用last指针添加到尾部
        Node<T> newNode = new Node<>(e);
        newNode.next = tail.next;
        tail.next = newNode;
        tail = newNode;
        size ++;
    }

    /**
     * 出队.
     * @return T
     * */
    public T dequeue() {
        if (size == 0) {
            throw new InternalException("queue is empty.");
        }
        //出队，利用dummyHead直接移除头节点
        Node<T> delNode = dummyHead.next;
        dummyHead.next = delNode.next;
        delNode.next = null;
        size -- ;
        return delNode.data;
    }

    public boolean isEmpty() {
        return dummyHead.next == null;
    }

    public T peek() {
        if (size == 0) {
            throw new InternalException("queue is empty.");
        }
        return dummyHead.next.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> cur = dummyHead.next;
        while (cur != null) {
            sb.append(cur.data);
            if (cur.next != null) {
                sb.append(" <- ");
            }
            cur = cur.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    private class Node<T> {
        T data;
        Node<T> next;

        public Node() {
            this(null);
        }

        public Node(T data) {
            this(data, null);
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        /**

         [ 1 <- 2 <- 3 ]
         1
         2
         [ 2 <- 3 ]
         [ 2 <- 3 <- 100 ]
         2
         3
         100
         true

         * */

        LinkedRQueue<Integer> queue = new LinkedRQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue.peek());
        System.out.println(queue);
        queue.enqueue(100);
        System.out.println(queue);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.isEmpty());
    }
}
