package com.base.ds.csdn.line;

/**
 * 单链表实现的循环链表.
 * */
public class ERecycleSingleLinkedList<E> {

    private Node<E> last;
    private int size;

    public ERecycleSingleLinkedList() {
        this.size = 0;
    }

    /**
     * 在头节点添加
     * @param e
     * */
    public void addLast(E e) {
        if (last == null) {
            addToEmpty(e);
            return;
        }

        Node<E> newNode = new Node<>(e);
        newNode.next = last.next;
        last.next = newNode;
        last = newNode;
        size ++;
    }

    /**
     * 在尾节点添加
     * @param e
     * */
    public void addFirst(E e) {
        if (last == null) {
            addToEmpty(e);
            return;
        }
        Node<E> newNode = new Node<>(e);
        newNode.next = last.next;
        last.next = newNode;
        //不对last指针进行后移
        size ++;
    }

    /**
     * 在指定的位置添加.
     * @param index
     * @param e
     * */
    public void add(E e, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index error.");
        }
        if (last == null) {
            addToEmpty(e);
            return;
        }

        //找到index之前的那个位置
        Node<E> prev = getPrevNode(index);
        Node<E> newNode = new Node<>(e);
        newNode.next = prev.next;
        prev.next = newNode;
        size ++;
    }

    /**
     * 删除指定的数据
     * @param data
     * */
    public void remove(E data) {
        //如果链表为空，直接范围
        if (size == 0) {
            return;
        }
        Node<E> head = last.next;
        Node<E> cur = last.next;
        Node<E> pre = last;

        //如果链表只有一个节点，单独处理
        if (data.equals(cur.data)
                && last == head) {
            last = null;
            size --;
            return;
        }

        //从头开始遍历整个节点
        for (int i = 0;i < size;i ++ ) {
            //如果找到了待删除的节点
            if (cur.data.equals(data)) {
                Node<E> delNode = cur;
                pre.next = cur.next;
                //无用，让理解上更连贯
                cur = cur.next;
                delNode.next = null;
                size --;
                //如果此时pre.next = 头结点，说明删除的是尾节点，需要更新last指针为pre
                if (pre.next == head) {
                    last = pre;
                }
                return;
            }
            pre = cur;
            cur = cur.next;
        }
    }

    private Node<E> getPrevNode(int index) {
        Node<E> cur = last;
        for (int i = 0;i < index;i ++) {
            cur = cur.next;
        }
        return cur;
    }

    private void addToEmpty(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = newNode;
        size ++;
        last = newNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        if (size > 0) {
            Node<E> cur = last.next;
            for (int i = 0; i < size ; i++) {
                sb.append(cur.data);
                if (i != size - 1) {
                    sb.append(",");
                }
                cur = cur.next;
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    private static class Node<E> {

        E data;
        Node<E> next;

        public Node() {
            this(null);
        }
        public Node(E data) {
            this(data, null);
        }
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ERecycleSingleLinkedList<Integer> list = new ERecycleSingleLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        System.out.println(list);
        list.add(4, 1);
        System.out.println(list);
    }

}
