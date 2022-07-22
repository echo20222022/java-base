package com.base.ds.line;

import com.sun.jdi.InternalException;

/**
 * 基于链式存储实现的栈.
 * */
public class LinkedStack<T> {

    private Node<T> dummyHead;
    private int size;

    public LinkedStack() {
        dummyHead = new Node<>();
        this.size = 0;
    }

    //将新增节点插入到头节点的位置
    public void push(T e) {
        Node<T> newNode = new Node(e);
        newNode.next = dummyHead.next;
        dummyHead.next = newNode;
        size ++;
    }

    //从头节点弹出数据
    public T pop() {
        if (size == 0) {
            throw new InternalException("stack is empty");
        }
        Node<T> delNode = dummyHead.next;
        dummyHead.next = delNode.next;
        delNode.next = null;
        size --;
        return delNode.data;
    }

    public T peek() {
        if (size == 0) {
            throw new InternalException("stack is empty");
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
                sb.append(" -> ");
            }
            cur = cur.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    private static class Node<T> {
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
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
