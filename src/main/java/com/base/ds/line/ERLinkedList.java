package com.base.ds.line;

/**
 * @author
 * 循环单链表
 *  初始化、插入、删除、判空、判断头结点、判断尾节点
 *
 *  内部持有一个尾结点，这样能够保证在头和尾插入的时间复杂度都为O(1)
 *
 *  添加：
 *  1. 项空链表中添加
 *  2. 向头节点添加
 *  3. 向尾结点添加
 *  4. 向中间节点添加
 *
 *  删除：
 *  删除指定的元素
 *    先找到带删除节点的前驱节点 再删除
 *  在指定位置删除
 *    先找到指定位置的前一个节点，再删除
 * */
public class ERLinkedList<T> {

//    private Node<T> dummyHead;
//    private int size;
    private Node<T> last;
    private int size;

    public ERLinkedList() {
        size = 0;
    }

    //在头部添加
    public void addFirst(T data) {
        //向空链表中添加
        if (last == null) {
            Node<T> newNode = new Node<>(data);
            newNode.next = newNode;
            last = newNode;
            size ++ ;
            return;
        }

        //向非空链表中添加
        Node<T> newNode = new Node<>(data);
        newNode.next = last.next;
        last.next = newNode;
        size ++;
    }

    //在尾部添加
    public void addLast(T data) {
        //向空链表中添加
        if (last == null) {
            Node<T> newNode = new Node<>(data);
            newNode.next = newNode;
            last = newNode;
            size ++ ;
            return;
        }

        //向非空链表中添加
        Node<T> newNode = new Node<>(data);
        newNode.next = last.next;
        last.next = newNode;
        last = newNode;
        size ++;
    }

    //在指定的位置添加
    public void add(T data, int idx) {
        //add(data, size);
        if (idx < 0 || idx > size) {
            throw new IllegalArgumentException("illegal index");
        }
        //向空链表中添加
        if (last == null) {
            Node<T> newNode = new Node<>(data);
            newNode.next = newNode;
            last = newNode;
            size ++ ;
            return;
        }
        //从last开始，将指针遍历的idx-1的位置
        Node<T> cur = last;
        for (int i = 0;i < idx;i ++) {
            cur = cur.next;
        }
        Node<T> newNode = new Node<>(data);
        newNode.next = cur.next;
        cur.next = newNode;
        size ++;
    }

    public T remove(int idx){
        return null;
    }

    public void remove(T data) {

    }

    public void set(T data, int idx) {

    }

    public boolean contains(T data) {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (size > 0) {
            Node<T> cur = last.next;
            for (int i = 0;i < size;i ++) {
                sb.append(cur.data);
                cur = cur.next;
                if (i != size - 1) {
                    sb.append(",");
                }
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;
        public Node() {
            this(null,null);
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
        ERLinkedList<Integer> list = new ERLinkedList<>();
        list.addFirst(1);
        list.addLast(2);
        list.addFirst(3);
        System.out.println(list);
        list.add(4,0);
        System.out.println(list);
        list.add(5,1);
        System.out.println(list);
    }
}
