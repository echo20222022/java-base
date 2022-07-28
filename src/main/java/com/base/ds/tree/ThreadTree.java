package com.base.ds.tree;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

/**
 * 线索二叉树.
 * 利用空的指针域来存储 某个遍历序列 的前驱和后继
 * left 指向前驱，right 指向后继
 *
 * 先序序列线索化和遍历
 * 中序序列线索化和遍历
 * 后序序列线索化和遍历
 * */
public class ThreadTree<E extends Comparable<E>> {


    private Node<E> root;
    private int size;

    public ThreadTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * 添加节点
     * @param e
     * */
    public void add(E e) {
        root = add(e, root);
    }

    private Node<E> add(E e, Node<E> node) {
        if (node == null) {
            size ++;
            return new Node<>(e);
        }
        if (e.compareTo(node.data) < 0) {
            node.left = add(e, node.left);
        } else if (e.compareTo(node.data) > 0) {
            node.right = add(e, node.right);
        }
        return node;
    }

    /**
     * ① 前序遍历
     * ② 前序线索化
     * ③ 基于前序序列线索化的遍历
     * */

    //①
    public void preOrder() {
        preOrder(root);
    }
    private void preOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    //②
    //临时前序指针
    private Node<E> preOrderThreadPrev;
    public void preThreadTree() {
        preThreadTree(root);
        if (preOrderThreadPrev != null) {
            preOrderThreadPrev.rtag = 1;
        }
    }
    private void preThreadTree(Node<E> node) {
        if (node == null) {
            return;
        }
        preThreadTreeVisit(node);
        if (node.ltag == 0) {
            preThreadTree(node.left);
        }
        if (node.rtag == 0) {
            preThreadTree(node.right);
        }
    }

    private void preThreadTreeVisit(Node<E> node) {
        if (node.left == null) {
            node.left = preOrderThreadPrev;
            node.ltag = 1;
        }

        //如果前驱的right是空，就让他指向node节点
        if (preOrderThreadPrev != null &&
                preOrderThreadPrev.right == null) {
            preOrderThreadPrev.right = node;
            preOrderThreadPrev.rtag = 1;
        }
        preOrderThreadPrev = node;
    }

    /**
     * ③由于先序遍历线索树中的节点无法找到它的前驱节点（因为前驱是向前指，而节点的当前两个子节点都是向后只的）
     *   所以只能从前往后遍历
     * */
    private Node<E> preOrderSequenceFirstNode(Node<E> node) {
        //以node为根节点的子树的先序遍历的第一个节点就是当前节点
        return node;
    }
    private Node<E> preOrderSequenceNextNode(Node<E> node) {
        //如果right被线索化了，就直接返回right
        if (node.rtag == 1) {
            return node.right;
        }
        //如果没有，先序遍历的后继节点 只可能是它的左 / 右子节点
        if (node.left != null) {
            return node.left;
        } else if (node.right != null) {
            return node.right;
        }
        return null;
    }
    public void preSequenceOrder() {
        for (Node<E> cur = preOrderSequenceFirstNode(root); cur != null; cur = preOrderSequenceNextNode(cur)) {
            System.out.print(cur.data + " ");
        }
        System.out.println();
    }


    /**
     * ①中序遍历
     * ②中序序列线索化
     * ③中序序列线索二叉树的正向遍历
     * ④中序序列线索二叉树的反向遍历
     * */

    //①中序遍历
    public void inOrder() {
        inOrder(root);
    }
    private void inOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    private Node<E> inOrderThreadPrev;
    //②中序序列线索化
    public void inThreadTree() {
        inThreadTree(root);
        if (inOrderThreadPrev != null) {
            inOrderThreadPrev.rtag = 1;
        }
    }
    private void inThreadTree(Node<E> node) {
        if (node == null) {
            return;
        }
        inThreadTree(node.left);
        inThreadTreeVisit(node);
        if (node.rtag == 0) {
            inThreadTree(node.right);
        }
    }

    private void inThreadTreeVisit(Node<E> node) {
        if (node.left == null) {
            node.left = inOrderThreadPrev;
            node.ltag = 1;
        }

        if (inOrderThreadPrev != null &&
                inOrderThreadPrev.right == null) {
            inOrderThreadPrev.right = node;
            inOrderThreadPrev.rtag = 1;
        }
        inOrderThreadPrev = node;
    }

    //③中序线索化二叉是正向遍历
    //中序遍历序列中的第一个节点
    private Node<E> inOrderSequenceFirstNode(Node<E> node) {
        Node<E> cur = node;
        while (cur.ltag == 0) {
            cur = cur.left;
        }
        return cur;
    }
    //中序遍历序列中的下一个节点
    private Node<E> inOrderSequenceNextNode(Node<E> node) {
        if (node.rtag == 1) {
            return node.right;
        }

        return inOrderSequenceFirstNode(node.right);
    }

    public void inSequenceOrder() {
        for (Node<E> cur = inOrderSequenceFirstNode(root); cur != null; cur = inOrderSequenceNextNode(cur)) {
            System.out.print(cur.data + " ");
        }
        System.out.println();
    }

    /**
     * 中序遍历序列的逆序遍历
     * */
    private Node<E> inOrderSequenceLastNode(Node<E> node) {
        Node<E> cur = node;
        while (cur.rtag == 0) {
            cur = cur.right;
        }
        return cur;
    }
    private Node<E> inOrderSequencePrevNode(Node<E> node) {
        if (node.ltag == 1) {
            return node.left;
        }
        return inOrderSequenceLastNode(node.left);
    }
    public void inSequenceOrderReverse(){
        for (Node<E> cur = inOrderSequenceLastNode(root); cur != null; cur = inOrderSequencePrevNode(cur)) {
            System.out.print(cur.data + " ");
        }
        System.out.println();
    }


    /**
     * ①后续遍历
     * ②后续序列化索引二叉树
     * ③遍历后续序列索引二叉树
     * */

    //①后续遍历
    public void postOrder() {
        postOrder(root);
        System.out.println();
    }
    private void postOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    //②后续序列化索引二叉树
    private Node<E> postOrderThreadPrev;
    public void postThreadTree() {
        postThreadTree(root);

        //注意，由于后续遍历的最后一个节点不是叶子节点，所以不能同先序遍历和
        //中序遍历那样，存在下面的逻辑
        /*if (postOrderThreadPrev != null) {
            postOrderThreadPrev.rtag = 1;
        }*/
    }
    private void postThreadTree(Node<E> node) {
        if (node == null) {
            return;
        }
        //if (node.ltag == 0) {
            postThreadTree(node.left);
        //}
        //if (node.rtag == 0) {
            postThreadTree(node.right);
        //}
        postThreadTreeVisit(node);

    }
    private void postThreadTreeVisit(Node<E> node) {
        if (node.left == null) {
            node.left = postOrderThreadPrev;
            node.ltag = 1;
        }

        if (postOrderThreadPrev != null
                && postOrderThreadPrev.right == null) {
            postOrderThreadPrev.right = node;
            postOrderThreadPrev.rtag = 1;
        }
        postOrderThreadPrev = node;
    }
    /**
     * ③遍历
     * 由于后序线索二叉树找不到后继节点，所以只能从后往前遍历
     * */
    private Node<E> postOrderSequenceLastNode(Node<E> node) {
        return node;
    }

    private Node<E> postOrderSequencePreNode(Node<E> node) {
        if (node.ltag == 1) {
            return node.left;
        }

        if (node.right != null && node.rtag != 1) {
            return node.right;
        }
        return node.left;
    }

    public void postSequenceOrderReverse() {
        for (Node<E> cur = postOrderSequenceLastNode(root); cur != null; cur = postOrderSequencePreNode(cur)) {
            System.out.print(cur.data + " ");
        }
        System.out.println();
    }



    private static class Node<E extends Comparable<E>> {
        E data;
        Node<E> left;
        Node<E> right;

        int ltag;
        int rtag;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node<E> left, Node<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.ltag = 0;
            this.rtag = 0;
        }
    }

    public static void main(String[] args) {
        ThreadTree<Integer> tree = new ThreadTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(8);
        tree.add(2);
        tree.add(4);
        tree.add(1);
        tree.add(7);
        tree.add(10);
        tree.add(9);
        tree.add(15);

        /**

                  5
               3      8
            2    4  7    10
         1              9   15

         1,2,3,4,5,7.,8,9,10,15
         * */

        //5 3 2 1 4 8 7 10 9 15
        //tree.preOrder();
        //System.out.println();
        //tree.preThreadTree();
        //5 3 2 1 4 8 7 10 9 15
        //tree.preSequenceOrder();
        System.out.println("===========");
        //1 2 3 4 5 7 8 9 10 15
        //tree.inOrder();
        //System.out.println();
        //tree.inThreadTree();
        //1 2 3 4 5 7 8 9 10 15
        //tree.inSequenceOrder();
        //15 10 9 8 7 5 4 3 2 1
        //tree.inSequenceOrderReverse();

        //1 2 4 3 7 9 15 10 8 5
        tree.postOrder();
        tree.postThreadTree();
        //5 8 10 15 9 7 3 4 2 1
        tree.postSequenceOrderReverse();
    }
}
