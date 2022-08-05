package com.base.ds.tree;

import com.base.ds.csdn.tree.BSTree;

/**
 * 红黑树.
 * */
public class RBTree<E extends Comparable<E>> {

    private Node<E> root;
    private int size;

    public RBTree() {
        this.root = null;
        this.size = 0;
    }
    
    
    public void add(E e) {
        //添加操作完成后，root节点可能发生变化
        root = add(e, root);
    }

    //利用递归，添加节点
    private Node<E> add(E e, Node<E> node) {
        if (node == null) {
            size ++;
            return new Node(e);
        }

        //如果当前数据比当前节点的值小，则向左添加
        if(e.compareTo(node.data) < 0) {
            node.left = add(e, node.left);
        } else if (e.compareTo(node.data) > 0) {
            node.right = add(e, node.right);
        }

        if (isRead(node.right) && !isRead(node.left)) {
            node = leftRotate(node);
        }

        if (isRead(node.left) && isRead(node.left.left)) {
            node = rightRotate(node);
        }

        if (isRead(node.left) && isRead(node.right)) {
            flipColors(node);
        }

        return node;
    }

    //左旋转
    private Node<E> leftRotate(Node<E> node) {
        Node<E> x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = Node.RED;

        return x;
    }

    //右旋转
    private Node<E> rightRotate(Node<E> node) {
        Node<E> x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = Node.RED;
        return x;
    }

    //颜色翻转
    private void flipColors(Node<E> node) {
        node.color = Node.RED;
        node.left.color = Node.BLACK;
        node.right.color = Node.BLACK;
    }

    //判断一个节点是否为红节点
    private boolean isRead(Node<E> node) {
        if (node == null) {
            return Node.BLACK;
        }
        return node.color;
    }

    public int getSize() {
        return size;
    }

    //定义节点
    private static class Node<E extends Comparable<E>> {

        private E data;
        private Node<E> left;
        private Node<E> right;

        //当前节点的颜色，true=红色，false=黑色
        public boolean color;

        //定义两个枚举值
        public static final boolean RED = true;
        public static final boolean BLACK = false;

        public Node(E data) {
            this(data,null,null);
        }

        public Node(E data, Node<E> left, Node<E> right) {
            this.data = data;
            this.right = right;
            this.left = left;
        }
    }
}
