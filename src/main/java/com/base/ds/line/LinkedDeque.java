package com.base.ds.line;

import com.sun.jdi.InternalException;

/**
 * 基于链表实现的双端队列
 * 双端队列：两边都能够进行入队和出兑
 * 实现：用双向链表实现
 *     持有两个指针 dummyHead  dummyTail
 *     由于是双向链表，所以在头尾操作的时间复杂度都是O(1)
 * 操作：头插、头出
 *      尾插、尾出
 *      遍历打印
 *      判空
 * */
public class LinkedDeque<T> {

    private Node<T> dummyHead;
    private Node<T> dummyTail;
    private int size;

    public LinkedDeque() {
        dummyHead = new Node<>();
        dummyTail = new Node<>();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        size = 0;
    }

    public void addFirst(T e) {
        Node<T> newNode = new Node<>(e);
        newNode.next = dummyHead.next;
        newNode.prev = dummyHead;
        dummyHead.next.prev = newNode;
        dummyHead.next = newNode;
        size ++;
    }

    public T removeFirst() {
        if (dummyHead.next == dummyTail) {
            throw new InternalException("queue is empty");
        }
        Node<T> delNode = dummyHead.next;
        dummyHead.next = delNode.next;
        delNode.next.prev = dummyHead;
        delNode.next = null;
        delNode.prev = null;
        size --;
        return delNode.data;
    }

    public void addLast(T e) {
        Node<T> newNode = new Node<>(e);
        newNode.prev = dummyTail.prev;
        newNode.next = dummyTail;
        dummyTail.prev.next = newNode;
        dummyTail.prev = newNode;
        size ++;
    }

    public T removeLast() {
        if (dummyHead.next == dummyTail) {
            throw new InternalException("queue is empty");
        }
        Node<T> delNode = dummyTail.prev;
        dummyTail.prev = delNode;
        delNode.prev.next = delNode.next;
        delNode.prev = null;
        delNode.next = null;
        size --;
        return delNode.data;
    }

    public boolean isEmpty() {
        return dummyHead.next == dummyTail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> cur = dummyHead.next;
        while (cur != dummyTail) {
            sb.append(cur.data);
            if (cur.next != dummyTail) {
                sb.append(" <- ");
            }
            cur = cur.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    private class Node<T> {
        private T data;
        private Node<T> prev;
        private Node<T> next;

        public Node() {
            this(null);
        }

        public Node(T data) {
            this(data, null, null);
        }
        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        /**

         [ 2 <- 1 <- -1 ]
         2
         [ 1 <- -1 ]
         -1
         [ 1 ]
         1

         * */
        LinkedDeque<Integer> deque = new LinkedDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addLast(-1);
        System.out.println(deque);
        System.out.println(deque.removeFirst());
        System.out.println(deque);
        System.out.println(deque.removeLast());
        System.out.println(deque);
        System.out.println(deque.removeFirst());
        System.out.println(deque.isEmpty());
    }
}
