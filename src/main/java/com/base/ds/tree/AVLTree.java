package com.base.ds.tree;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 平衡二叉树，避免二叉搜索树退化为链表，影响相关操作的性能.
 * 定义：左子树的高度和右子树的高度不能相差 1
 *
 *  过程：标注节点高度、计算平衡因子（左子树的高度 - 右子树的高度）
 * */
public class AVLTree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    //添加
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node<K, V> add(Node<K, V> node, K key, V value) {
        if (node == null) {
            size ++;
            return new Node<>(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            //key相同，就更新以下value值
            node.value = value;
        }

        //更新高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算当前节点的平衡因子，看是否需要旋转
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {  //左右高度差 > 1，就需要进行旋转

            // LL RR LR RL
            //向左倾斜，进行右旋
            /**

                  y
                x   t4
              z  t3
            t1 t2

            顺序是 t1 < z  < t2 < x < t3 < y < t4
            由于在递归添加的时候，是从下到上回溯，此时发现y的平衡因子为2，也就是向左倾斜（左高-右高）
            保持二叉树的顺序不变，围绕x进行旋转
                     x
                  z      y
                t1  t2  t3 t4
             旋转后的顺序让人是  t1 z t2 x t3 y t4
             即：
                 x.right = y
                 y.left = t3

             * */
            //LL
            if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
                //右旋转后，返回新的根
                return rightRotate(node);
            }

            /**
            同理，左旋转，处理向右倾斜的情况
                  y
                t4   x
                   t3  z
                     t2 t1

              >>>>>>

                      x
                  y       z
                t4  t3  t2  t1

                x.left = y
                y.right = t3

             * */
            //RR
            if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
                //向右倾斜，进行左旋转
                return leftRotate(node);
            }

            /**

             在左侧的右侧添加 或 在右侧的左侧添加 导致的不平衡就不能直接进行右旋转 或 左旋转了
             因为这会破坏二叉树的结构，比如
                12
             8
               10
             如果进行又旋转，变成
                8
             10  12
             这就不是一颗二叉搜索树了，所以，需要先对 8 和 10进行左旋转，然后再对10进行右旋转
             同理右侧相反

             * */
            //LR


            //LR
            if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
                //左旋转
                node.left = leftRotate(node.left);
                //右旋转
                return rightRotate(node);
            }

            //RL
            if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
                //右旋转
                node.right = rightRotate(node.right);
                //左旋转
                return leftRotate(node);
            }
        }
        return node;
    }

    //右旋转
    /**
          y
        x   t4
      z  t3
     t1 t2
             ->>>>>
          x
       z      y
     t1  t2  t3 t4

     * */
    private Node<K, V> rightRotate(Node y) {
        Node<K, V> x = y.left;
        Node<K, V> t3 = x.right;

        x.right = y;
        y.left = t3;

        //更新y和x的高度，注意先更新y值的高度，再更新x值的高度
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }
    /**
     同理，左旋转，处理向右倾斜的情况
        y
     t4   x
        t3  z
          t2 t1

     >>>>>>

           x
       y       z
     t4  t3  t2  t1

     x.left = y
     y.right = t3

     * */

    private Node<K, V> leftRotate(Node<K, V> y) {
        Node<K, V> x = y.right;
        Node<K, V> t3 = x.left;

        x.left = y;
        y.right = t3;

        //更新y和x的高度，注意先更新y值的高度，再更新x值的高度
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    //获取当前节点的高度值
    private int getHeight(Node<K, V> node) {
        if(node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    //判断二分搜索树是否平衡
    private boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node<K, V> node) {
        //到底了
        if (node == null) {
            return true;
        }
        //计算当前节点的平和因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //判断当前树结构是不是一个二分搜索树
    private boolean isBst() {
        List<K> list = new ArrayList<>(size);
        inOrder(root, list);
        //遍历list并判断有序性
        for (int i = 0;i < list.size() - 1;i ++) {
            if (list.get(i).compareTo(list.get(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }
    //利用二分搜索树的一个性质：中序遍历是从大到小排列的
    private void inOrder(Node<K, V> node, List<K> list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node.key);
        inOrder(node.right, list);
    }

    public boolean contains(K key) {
        if (isEmpty()) {
            throw new InternalException("avl tree is empty.");
        }
        return contains(key, root);
    }

    private boolean contains(K key, Node<K, V> node) {
        if (node == null) {
            return false;
        }
        if (key.compareTo(node.key) == 0) {
            return true;
        } else if (key.compareTo(node.key) < 0) {
            return contains(key, node.left);
        } else {
            return contains(key, node.right);
        }
    }

    //最小的key
    public K minKey() {
        if (isEmpty()) {
            throw new InternalException("bst is empty");
        }
        return minNode(root).key;
    }

    private Node<K, V> minNode(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }

    //最大的key
    public K maxKey() {
        if (isEmpty()) {
            throw new InternalException("bst is empty");
        }
        return maxNode(root).key;
    }

    private Node<K, V> maxNode(Node<K, V> node) {
        if (node.right == null) {
            return node;
        }
        return maxNode(node.right);
    }

    //删除最小key
    public K removeMinKey() {
        K minKey = minKey();
        removeMinKeyNode(root);
        return minKey;
    }

    private Node<K, V> removeMinKeyNode(Node<K, V> node) {
        if (node.left == null) {
            Node<K, V> right = node.right;
            node.right = null;
            size --;
            return right;
        }
        node.left = removeMinKeyNode(node.left);
        return node;
    }

    //删除最大key
    public K removeMaxKey() {
        K maxKey = maxKey();
        removeMaxKeyNode(root);
        return maxKey;
    }

    private Node<K, V> removeMaxKeyNode(Node<K, V> node) {
        if (node.right == null) {
            Node<K, V> left = node.left;
            node.left = null;
            size --;
            return left;
        }
        node.right = removeMaxKeyNode(node.right);
        return node;
    }

    public void remove(K key) {
        root = remove(key, root);
    }

    private Node<K, V> remove(K key, Node<K,V> node) {
        //没有找到
        if (node == null) {
            return null;
        }

        Node<K, V> retNode ;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(key, node.left);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(key, node.right);
            retNode = node;
        } else {
            //找到了,先处理没有左子节点或没有右子节点的情况
            if (node.left == null) {
                Node<K, V> right = node.right;
                node.right = null;
                size --;
                retNode = right;
            } else if (node.right == null) {
                Node<K, V> left = node.left;
                node.left = node;
                size --;
                retNode = left;
            } else {
                //即有左子节点又有右子节点
                //找到右子树中的最小节点
                Node<K, V> rightMinKeyNode = minNode(node.right);
                //将右子树中最小节点删除后的子树根节点 挂在原右子树中的最小节点
                //rightMinKeyNode.right = removeMinKeyNode(node.right);
                //由于removeMin中没有维护平衡，所以这里替换成remove方法
                rightMinKeyNode.right = remove(rightMinKeyNode.key, node);
                rightMinKeyNode.left = node.left;

                //help GC
                node.left = null;
                node.right = null;
                retNode = rightMinKeyNode;
            }
        }

        //删除叶子节点时，不用维护平衡
        if (retNode == null) {
            return null;
        }

        //更新高度
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        //计算当前节点的平衡因子，看是否需要旋转
        int balanceFactor = getBalanceFactor(retNode);
        if (Math.abs(balanceFactor) > 1) {  //左右高度差 > 1，就需要进行旋转
            //LL
            if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
                //右旋转后，返回新的根
                return rightRotate(retNode);
            }

            //RR
            if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
                //向右倾斜，进行左旋转
                return leftRotate(retNode);
            }

            //LR
            if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
                //左旋转
                retNode.left = leftRotate(retNode.left);
                //右旋转
                return rightRotate(retNode);
            }

            //RL
            if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
                //右旋转
                retNode.right = rightRotate(retNode.right);
                //左旋转
                return leftRotate(retNode);
            }
        }
        return retNode;
    }

    //前序遍历
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node<K, V> node) {
        if (node == null) {
            return;
        }
        System.out.println("[ key = " + node.key + ", value = " + node.value + " ]");
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }
    private void inOrder(Node<K, V> node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println("[ key = " + node.key + ", value = " + node.value + " ]");
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<K, V> node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println("[ key = " + node.key + ", value = " + node.value + " ]");
    }

    public void levelOrder() {
        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty() && root != null) {
            Node<K, V> node = queue.remove();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    //定义节点结构
    private static class Node<K extends Comparable<K>, V> {

        public K key;
        public V value;
        public Node<K, V> left;
        public Node<K, V> right;

        //记录当前节点的高度值
        private int height;

        public Node(K key, V value) {
            this(key, value, null, null);
        }

        public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            //节点的高度默认是1
            this.height = 1;
        }
    }
}
