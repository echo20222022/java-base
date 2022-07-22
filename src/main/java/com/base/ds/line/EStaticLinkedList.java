package com.base.ds.line;

/**
 * 静态链表
 * 操作：
 *   初始化、插入、删除、查找、遍历
 * 结构：
 *   Node {
 *       T data;
 *       int idx;
 *   }
 *   Node[]
 * */
public class EStaticLinkedList<T> {

    private Node<T>[] data;
    private int size;

    public EStaticLinkedList(int capacity) {
        this.data = (Node<T>[]) new Object[capacity];
        this.size = 0;
    }

    public void add(T data) {

    }

    public void add(T data, int idx) {

    }

    public void remove(int idx) {

    }

    public void get(int idx) {

    }

    public boolean contains(T data) {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }

    private class Node<T> {
        T data;
        int next;
        public Node(T data, int next) {
            this.data = data;
            this.next = next;
        }
    }

}
