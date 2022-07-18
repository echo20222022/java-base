package com.base.ds.line;

/**
 * 动态数组
 * @author
 * 用单链表实现顺序存储结构.
 * 操作：增删改查
 * */
public class ELinkedList<T> {

    //定义一个内部的头节点，用来统一指针的操作逻辑
    private Node<T> dummyHead;
    private int size;

    //初始化dummyHead和链表的长度
    public ELinkedList() {
        dummyHead = new Node<>();
        size = 0;
    }

    //添加到链表的末尾
    public void add(T data) {
        add(data, size);
    }

    //在指定位置添加
    /**
     * 找到idx的前一个位置
     * 调整指针
     * */
    public void add(T data, int idx) {
        //校验idx的合法性
        if (idx < 0 || idx > size) {
            throw new IllegalArgumentException("illegal index.");
        }
        Node<T> pre = dummyHead;
        for (int i = 0;i < idx;i ++) {
            pre = pre.next;
        }

        //调整指着
        Node<T> newNode = new Node<>(data);
        newNode.next = pre.next;
        pre.next = newNode;
        size ++;
    }

    //修改
    public void set(T data, int idx) {
        //校验idx的合法性
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException("illegal index.");
        }

        Node<T> cur = dummyHead;
        for (int i = 0;i <= idx;i ++) {
            cur = cur.next;
        }
        cur.data = data;
    }

    /**
     * 查指定的元素
     * 查指定位置上的元素
     * */
    public T get(int idx) {
        //校验idx的合法性
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException("illegal index.");
        }
        Node<T> cur = dummyHead;
        for (int i = 0;i <= idx;i ++) {
            cur = cur.next;
        }
        return cur.data;
    }

    public boolean contains(T data) {
        Node<T> cur = dummyHead.next;
        while (cur != null) {
            if (cur.data.equals(data)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> cur = dummyHead.next;
        while (cur != null) {
            sb.append(cur.data);
            if (cur.next != null) {
                sb.append(",");
            }
            cur = cur.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    //定义链表节点的结构
    private static class Node<T> {
        public T data;
        public Node<T> next;

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
        ELinkedList<Integer> list = new ELinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);
        list.set(10, 1);
        System.out.println(list);
        System.out.println(list.get(2));
        System.out.println(list.contains(10));
    }
}
