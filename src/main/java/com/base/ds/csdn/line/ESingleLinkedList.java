package com.base.ds.csdn.line;

/**
 * 单链表.
 * 操作：
 * 0. 初始化
 * 1. 插入
 * 2. 删除
 * 3. 查找
 * 4. 修改
 * 5. 遍历
 * */
public class ESingleLinkedList<E> {

    private Node<E> dummyHead;
    private int size;

    public ESingleLinkedList() {
        dummyHead = new Node<>();
        size = 0;
    }

    /**
     * 在链表的末尾添加数据
     * @param e
     * */
    public void add(E e) {
        add(e, size);
    }

    /**
     * 在链表的指定位置添加数据.
     * @param index
     * */
    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index error.");
        }
        //找到index - 1的位置
        Node<E> prev = getPrevNode(index);

        Node<E> newNode = new Node<>(e);
        newNode.next = prev.next;
        prev.next = newNode;
        size ++;
    }

    /**
     * 更新某个位置上的值.
     * @param e
     * @param index
     * @return E
     * */
    public E set(E e, int index) {
        Node<E> node = getNode(index);
        E retValue = node.data;
        node.data = e;
        return retValue;
    }

    /**
     * 获取指定位置上的值.
     * @param index
     * @return E
     * */
    public E get(int index) {
        Node<E> node = getNode(index);
        return node.data;
    }

    /**
     * 删除指定位置上的数据.
     * @param index
     * @return E
     * */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index error.");
        }
        Node<E> prev = getPrevNode(index);
        Node<E> delNode = prev.next;
        prev.next = delNode.next;
        delNode.next = null;
        size --;
        return delNode.data;
    }

    //获取指定位置上的Node
    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index error.");
        }
        Node<E> cur = dummyHead;
        for (int i = 0;i <= index;i ++) {
            cur = cur.next;
        }
        return cur;
    }

    //获取指定位置的前一个Node
    private Node<E> getPrevNode(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index error.");
        }
        Node<E> pre = dummyHead;
        for (int i = 0;i < index;i ++) {
            pre = pre.next;
        }
        return pre;
    }


    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<E> cur = dummyHead.next;
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

    private static class Node<E> {
        E data;
        Node next;

        public Node() {
            this(null);
        }

        public Node(E data) {
            this(data, null);
        }
        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ESingleLinkedList<Integer> list = new ESingleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        //[ 1,2,3 ]
        System.out.println(list);
        list.add(10, 1);
        //[ 1,10,2,3 ]
        System.out.println(list);
        //2
        System.out.println(list.remove(2));
        //[ 1,10,3 ]
        System.out.println(list);
        list.set(40, 2);
        //[ 1,10,40 ]
        System.out.println(list);
    }
}
