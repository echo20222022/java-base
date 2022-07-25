package com.base.ds.tree;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import sun.tools.jconsole.inspector.XNodeInfo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二分搜索树：左节点小于根节点，右节点大于根节点
 * 叶子节点、兄弟节点、父节点、树的深度
 * 元素的特点：具有可比较小
 * 操作：
 *    初始化、新增、删除、查询
 *    遍历：前序遍历、中序遍历、后续遍历、层序遍历
 *
 *
 *    - 添加
 *    - 判断是否包含
 *    - 查找最大值
 *    - 查找最小值
 *    - 删除最大值
 *    - 删除最小值
 *    - 删除任意值
 *    - 前序遍历
 *    - 中序遍历
 *    - 后续遍历
 *    - 层序遍历
 *
 * */
public class BSTree<E extends Comparable<E>> {

    private Node<E> root;
    private int size;

    public BSTree() {
        root = null;
        size = 0;
    }

    /**
     * 添加数据.
     * */
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
        return node;
    }

    /**
     * 判断释放包含某个数据
     * */
    public boolean contains(E e) {
        if (isEmpty()) {
            throw new InternalException("bst is empty.");
        }
        return contains(e, root);
    }

    //利用递归来判断释放包含
    private boolean contains(E e, Node<E> node) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.data) == 0) {
            return true;
        } else if (e.compareTo(node.data) < 0) {
            return contains(e, node.left);
        } else {
            return contains(e, node.right);
        }
    }

    //找到最小的数据
    public E min() {
        if (isEmpty()) {
            throw new InternalException("bst is empty.");
        }
        return minNode(root).data;
    }

    private Node<E> minNode(Node<E> node) {
        if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }

    //找到最大的数据
    public E max() {
        if (isEmpty()) {
            throw new InternalException("bst is empty.");
        }
        return maxNode(root).data;
    }


    private Node<E> maxNode(Node<E> node) {
        if (node.right == null) {
            return node;
        }
        return maxNode(node.right);
    }

    //删除最小值
    public E removeMin() {
        E ret = min();
        root = removeMin(root);
        return ret;
    }

    //用递归的方式实现
    private Node<E> removeMin(Node<E> node) {
        if (node.left == null) {
            Node<E> right = node.right;
            node.right = null;
            size --;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    //删除最大值
    public E removeMax() {
        E ret = max();
        root = removeMax(root);
        return ret;
    }

    private Node<E> removeMax(Node<E> node) {
        if (node.right == null) {
            Node<E> left = node.left;
            node.left = null;
            size --;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    //移除给定值的节点
    public void remove(E e){
        root = remove(e, root);
    }

    private Node<E> remove(E e, Node<E> node) {
        //如果没有找到目标节点就直接范围null
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.data) < 0) {
            node.left = remove(e, node.left);
            return node;
        } else if (e.compareTo(node.data) > 0) {
            node.right = remove(e, node.right);
            return node;
        } else {
            //找到了要删除的节点
            /**
             * 没有左子节点
             * 没有右子节点
             * 即有左子节点也有右子节点
             * */
            if (node.left == null) {
                Node<E> right = node.right;
                node.right = null;
                size --;
                return right;
            }

            if (node.right == null) {
                Node<E> left = node.left;
                node.left = null;
                size --;
                return left;
            }

            //思路是：用左子树中的最大节点 或 用右子树中的最小节点来替换删除的节点，这样可以
            //保证二叉搜索树的性质

            //找到又子树中的最小节点
            Node<E> rightMinNode = minNode(node.right);
            //将右子树中的最小节点删除，并把删除后的结果挂在 上面得到的Node的右侧分支
            rightMinNode.right = removeMin(node.right);
            //将当前待删除节点的left挂到rightMinNode上
            rightMinNode.left = node.left;

            //help GC
            node.right = null;
            node.left = null;

            return rightMinNode;
        }
    }

    /**
     * 前序遍历：父节点、左节点、右节点
     * 中序遍历：左节点、父节点、右节点
     * 后续遍历：左节点、右节点、父节点
     * 层序遍历：一层一层遍历
     * */

    //前
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.data);
        preOrder(node.left);
        preOrder(node.right);
    }

    //中
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }

    //后
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data);
    }

    //层序遍历，借助队列
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty() && root != null) {
            Node<E> cur = queue.remove();
            System.out.println(cur.data);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private static class Node<E extends Comparable<E>>{

        private E data;
        private Node<E> left;
        private Node<E> right;

        public Node(E elem) {
            this(elem, null, null);
        }

        public Node(E elem, Node<E> left, Node<E> right) {
            this.left = left;
            this.right = right;
            this.data = elem;
        }
    }

    public static void main(String[] args) {
        BSTree<Integer> bsTree = new BSTree<>();
        bsTree.add(5);
        bsTree.add(3);
        bsTree.add(8);
        bsTree.add(2);
        bsTree.add(4);
        bsTree.add(1);
        bsTree.add(7);
        bsTree.add(10);
        bsTree.add(9);
        bsTree.add(15);

        //bsTree.postOrder();
        bsTree.levelOrder();
    }
}
