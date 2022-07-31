package com.base.ds.csdn.line;

/**
 * 双向链表.
 * 操作：
 * 0. 初始化
 * 1. 新增
 * 2. 修改
 * 3. 删除
 * 4. 遍历
 * */
public class EDoubleLinkedList <E>{

    private Node<E> dummyHead;
    private int size;


    public EDoubleLinkedList() {
        this.dummyHead = new Node<>();
        this.size = 0;
    }

    /**
     * 在末尾添加.
     * @param e
     * */
    public void add(E e) {
        add(e, size);
    }

    /**
     * 在指定位置添加.
     * @param e
     * @param index
     * */
    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index error");
        }
        Node<E> prev = getPrevNode(index);
        Node<E> newNode = new Node<>(e);
        newNode.next = prev.next;
        newNode.prev = prev;
        if (prev.next != null)
            prev.next.prev = newNode;
        prev.next = newNode;
        size ++;
    }

    /**
     * 修改某个索引位置上行的值.
     * @param e
     * @param index
     * @return E
     * */
    public E set(E e, int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index error");
        }
        Node<E> node = getNode(index);
        E retValue = node.data;
        node.data = e;
        return retValue;
    }

    /**
     * 获取某个位置上的值.
     * @param index
     * @return E
     * */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index error");
        }
        Node<E> node = getNode(index);
        return node.data;
    }

    /**
     * 删除某个位置上的值.
     * @param index
     * @return E
     * */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index error");
        }

        Node<E> prev = getPrevNode(index);
        Node<E> delNode = prev.next;
        prev.next = delNode.next;
        if (delNode.next != null)
            delNode.next.prev = prev;
        delNode.next = delNode.prev = null;
        size --;
        return delNode.data;
    }

    //获取index位置上的node
    private Node<E> getNode(int index) {
        Node<E> cur = dummyHead;
        int i = 0;
        while (i <= index) {
            cur = cur.next;
            i ++;
        }
        return cur;
    }

    //获取index的前一个位置的node
    private Node<E> getPrevNode(int index) {
        Node<E> cur = dummyHead;
        int i = 0;
        while (i < index) {
            cur = cur.next;
            i ++;
        }
        return cur;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<E> cur = dummyHead.next;
        for (int i = 0;i < size;i ++) {
            sb.append(cur.data);
            if (i != size -1) {
                sb.append(",");
            }
            cur = cur.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    private class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;
        public Node() {
            this(null);
        }
        public Node(E data) {
            this(data, null,null);
        }
        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        EDoubleLinkedList<Integer> list = new EDoubleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        //[ 1,2,3,4 ]
        System.out.println(list);
        list.add(10, 2);
        //[ 1,2,10,3,4 ]
        System.out.println(list);
        list.set(100, 0);
        //[ 100,2,10,3,4 ]
        System.out.println(list);
        //4
        System.out.println(list.remove(4));
        //[ 100,2,10,3 ]
        System.out.println(list);
    }

}
