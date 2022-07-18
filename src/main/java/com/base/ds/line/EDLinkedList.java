package com.base.ds.line;


/**
 * @author
 * 双向链表
 * 操作：增删改查 遍历
 * */
public class EDLinkedList<T> {

    private Node<T> dummyHead;
    private int size;

    public EDLinkedList() {
        dummyHead = new Node<>();
        size = 0;
    }

    //在尾部添加数据
    public void add(T data) {
        add(data, size);
    }

    //在指定的位置添加数据
    public void add(T data, int idx) {
        if (idx < 0 || idx > size) {
            throw new IllegalArgumentException("illegal index");
        }
        Node<T> prev = dummyHead;
        for (int i = 0;i < idx;i ++) {
            prev = prev.next;
        }

        Node<T> newNode = new Node<>(data);
        newNode.next = prev.next;
        newNode.prev = prev;
        if (prev.next != null) {
            prev.next.prev = newNode;
        }
        prev.next = newNode;
        size ++;
    }

    //修改
    public void set(T data, int idx) {
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException("illegal index");
        }
        Node<T> cur = dummyHead;
        for (int i = 0;i <= idx;i ++) {
            cur = cur.next;
        }
        cur.data = data;
    }

    //删除指定位置上的数据
    /**
     * 找到待删除的节点
     * 调整当前节点的前驱节点的后继节点为当前节点的后继节点
     * 调整当前节点的后继节点的前驱节点为当前节点的前驱节点
     * 将当前节点的前驱节点和后继节点置空
     * */
    public void remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IllegalArgumentException("illegal index");
        }
        Node<T> cur = dummyHead;
        for (int i = 0;i <= idx;i ++) {
            cur = cur.next;
        }
        cur.prev.next = cur.next;
        if (cur.next != null) {
            cur.next.prev = cur.prev;
        }
        cur.next = null;
        cur.prev = null;
        size --;
    }

    //查询
    public boolean contains(T data) {
        if (size == 0) {
            return false;
        }
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
        Node<T> cur = dummyHead.next;
        while (cur != null) {
            sb.append(cur.data);
            if (cur.next != null) {
                sb.append(",");
            }
            cur = cur.next;
        }
        return sb.toString();
    }

    private class Node<T> {
        private T data;
        private Node<T> prev;
        private Node<T> next;

        public Node() {
            this(null, null, null);
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
        EDLinkedList<Integer> list = new EDLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(10, 1);
        System.out.println(list);
        list.set(100, 0);
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        System.out.println(list.contains(3));
    }
}
