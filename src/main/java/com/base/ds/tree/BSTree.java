package com.base.ds.tree;

import com.sun.security.auth.UnixNumericUserPrincipal;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import sun.tools.jconsole.inspector.XNodeInfo;

import java.util.*;

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

    //通过前序序列 + 中序序列重建一个二叉树
    /**
     * @param preOrder 前序序列
     * @param inOrder 中序序列
     * */
    public Node<E> buildTree1(E[] preOrder, E[] inOrder) {
        if (preOrder == null || inOrder == null
            || preOrder.length == 0 || inOrder.length == 0) {
            return null;
        }
        List<E> preList = new ArrayList<>(preOrder.length);
        for (int i = 0;i < preOrder.length;i ++) {
            preList.add(preOrder[i]);
        }
        return help1(preList, inOrder);
    }

    private Node<E> help1(List<E> preList, E[] inOrder) {
        if (preList.size() == 0 || inOrder == null || inOrder.length == 0) {
            return null;
        }

        E value = preList.remove(0);
        //根节点
        Node<E> node = new Node<>(value);

        E[] leftTree = null;
        E[] rightTree = null;
        //遍历中序序列
        for (int i = 0;i < inOrder.length;i ++) {
            //找到中序序列中的根节点，进而缺点左右节点
            if (inOrder[i].compareTo(value) == 0) {
                //从中序序列中截出左子树的节点
                leftTree = i == 0 ?  //判断左子树是否为null
                        null : Arrays.copyOfRange(inOrder, 0, i);
                rightTree = i == (inOrder.length - 1) ?  //判断有没有右子树
                        null : Arrays.copyOfRange(inOrder, i, inOrder.length);
            }
        }

        node.left = help1(preList, leftTree);
        node.right = help1(preList, rightTree);
        return node;
    }

    //通过后续序列 + 中序序列重建一个二叉树
    public Node<E> buildTree2(E[] postOrder, E[] inOrder) {
        if (postOrder == null || inOrder == null
            || postOrder.length == 0 || inOrder.length == 0) {
            return null;
        }
        List<E> postList = new ArrayList<>(postOrder.length);
        for (int i = 0;i < postOrder.length;i ++) {
            postList.add(postOrder[i]);
        }
        return help2(postList, inOrder);
    }

    public Node<E> help2(List<E> postList, E[] inOrder) {
        if (postList.size() == 0 ||
                inOrder == null || inOrder.length == 0) {
            return null;
        }
        E value = postList.remove(postList.size() - 1);
        //构造根节点
        Node<E> node = new Node<>(value);
        E[] leftTree = null;
        E[] rightTree = null;
        for (int i = 0;i < inOrder.length;i ++) {
            //从中序遍历中找到根节点
            if (value.compareTo(inOrder[i]) == 0) {
                leftTree = i == 0 ? null : Arrays.copyOfRange(inOrder, 0, i);
                rightTree = i == inOrder.length - 1 ? null : Arrays.copyOfRange(inOrder, i , inOrder.length);
            }
        }

        node.left = help2(postList, leftTree);
        node.right = help2(postList, rightTree);

        return node;
    }


    /**
     * 用土办法 找中序遍历序列中 某个节点的前驱节点
     * */

    private Node<E> pre = null;  //前驱
    private boolean findFlag = false;    //当前节点
    private Node<E> q = null;    //要招的节点

    private Node<E> findNode(E e) {
        return findNode(e, root);
    }

    private Node<E> findNode(E e, Node<E> node) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.data) < 0) {
            return findNode(e, node.left);
        } else if (e.compareTo(node.data) > 0) {
            return findNode(e, node.right);
        }
        return node;
    }

    public E findInOrderPre(E e) {
        q = findNode(e);
        //p = root;
        inOrderForFindPre(root);
        return pre.data;
    }

    private void inOrderForFindPre(Node<E> node) {
        if (node == null) {
            return;
        }
        inOrderForFindPre(node.left);
        visit(node);
        inOrderForFindPre(node.right);
    }

    private void visit(Node<E> node) {
        if (!findFlag) {
            if (node.data.compareTo(q.data) == 0) {
                findFlag = true;
                System.out.println("目标节点的前驱节点为：" + pre);
            } else {
                pre = node;
            }
        }
    }

    /**
     对二叉树进行前、中、后序的线索化
     * */
    //线索化过程中，用到的前驱
    Node<E> threadIn = null;

    /**
     * 前序遍历的线索化过程：
     * ①pre 记录当前节点的前驱节点
     * ②如果当前节点的左子节点为空，就将这个左子节点的位置指针指向pre，并且ltag = 1
     * ④如果pre节点的右子节点为空，就将pre的右子节点指向cur，并且rtag = 1
     * ③然后pre = cur
     * */

    public void threadInOrder() {
        threadInOrder(root);
        //最终，这个pre会指向二叉树的最大的一个节点上
        if (threadIn != null) {
            threadIn.rtag = 1;
        }
    }

    private void threadInOrder(Node<E> node) {
        if (node == null) {
            return;
        }
        threadInOrder(node.left);
        inOrderThread(node);
        threadInOrder(node.right);
    }

    //这里的操作很类似于双向链表
    private void inOrderThread(Node<E> node) {
        //当前节点的左子节点为空，让其指向前驱节点
        if (node.left == null) {
            node.left = threadIn;
            node.ltag = 1;
        }

        //前驱节点的右子树为空，让其指向后继节点
        if (threadIn != null && threadIn.right == null) {
            threadIn.right = node;
            threadIn.rtag = 1;
        }
        threadIn = node;
    }

    /**
     * 遍历 中序遍历线索化 的数
     * */
    //求中序遍线索树中，节点node的后继，就是求当前节点右子树中的最小节点
    private Node<E> next1(Node<E> node) {
        if (node.rtag == 0) {
            node = firstNode11(node.right);
            return node;
        }
        return node.right;
    }
    private Node<E> firstNode11(Node<E> node) {
        while (node.ltag == 0) {
            node = node.left;
        }
        return node;
    }
    private void loop11() {
        for (Node<E> cur = firstNode11(root); cur != null; cur = next1(cur)) {
            System.out.println(cur.data);
        }
    }
    //求中序线索树中，节点node的前驱节点，就是如果当前节点的ltag=1即返回node.left，否则，返回
    //其左子树中的最大节点
    private Node<E> pre1(Node<E> node){
        if (node.ltag == 0) {
            node = firstNode12(node);
        }
        return node.left;
    }

    private Node<E> firstNode12(Node<E> node) {
        while (node.rtag == 0) {
            node = node.right;
        }
        return node;
    }

    //中序线索二叉树 逆向遍历
    private void loop12() {
        for (Node<E> cur = firstNode12(root); cur != null; cur = pre1(cur)) {
            System.out.println(cur.data);
        }
    }

    /**
     * 前序线索化
     * */
    Node<E> preOrderThreadPre = null;
    public void preOrderThread() {
        preOrderThread(root);
        //最终，这个pre会指向二叉树的最大的一个节点上
        if (threadIn != null) {
            threadIn.rtag = 1;
        }
    }

    public void preOrderThread(Node<E> node) {
        if (node == null) {
            return;
        }
        preVisit(node);
        //这里要加一个判断，否则回形成环，原因是preVisit中会操作preOrderThreadPre
        if (node.ltag == 0) {
            preOrderThread(node.left);
        }
        preOrderThread(node.right);
    }

    private void preVisit(Node<E> node) {
        if (node.left == null) {
            node.left = preOrderThreadPre;
            node.ltag = 1;
        }

        if (preOrderThreadPre != null && preOrderThreadPre.right == null) {
            preOrderThreadPre.right = node;
            preOrderThreadPre.rtag = 1;
        }
        preOrderThreadPre = node;
    }

    /**
     * 先序后继：
     * rtag = 1, next = cur.right
     * rtag = 0
     *   if left != null
     *     next = cur.left
     *   if right != null
     *     next = cur.right
     *
     * 先序前驱：
     * ltag = 1, pre = cur.left
     * ltag = 0
     *   找不到
     * */


    /**
     * 后续前驱：
     * ltag = 1, pre = cur.left
     * ltag = 0
     *    if cur.left != null
     *       pre = cur.left
     *    if cur.right != null
     *       pre = cur.right
     *
     * */




    //后续遍历的线索化
    Node<E> postOrderPre = null;
    public void postOrderThread() {
        postOrderThread(root);
        //最终，这个pre会指向二叉树的最大的一个节点上
        if (threadIn != null) {
            threadIn.rtag = 1;
        }
    }

    private void postOrderThread(Node<E> node) {
        if (node == null) {
            return;
        }
        postOrderThread(node.right);
        postOrderThread(node.left);
        postVisit(node);
    }

    private void postVisit(Node<E> node) {
        if (node.left == null) {
            node.left = postOrderPre;
            node.ltag = 1;
        }

        if (postOrderPre != null && postOrderPre.right == null) {
            postOrderPre.right = node;
            postOrderPre.rtag = 1;
        }
        postOrderPre = node;
    }


    private static class Node<E extends Comparable<E>>{

        private E data;
        private Node<E> left;
        private Node<E> right;

        private int ltag;   //线索化 0 - 左子节点  1 - 前驱节点
        private int rtag;   //0 - 右子接待  2 - 后继节点

        public Node(E elem) {
            this(elem, null, null);
        }

        public Node(E elem, Node<E> left, Node<E> right) {
            this.left = left;
            this.right = right;
            this.data = elem;
            this.ltag = 0;
            this.rtag = 0;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
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

        /**

                5
             3      8
          2    4  7   10
        1            9   15

        1,2,3,4,5,7.,8,9,10,15
         * */
        //bsTree.postOrder();
        //bsTree.levelOrder();
        Node<Integer> node = bsTree.findNode(10);
        System.out.println(node.data + ", " + node.left.data + ", " + node.right.data);
        System.out.println(bsTree.findInOrderPre(10));

    }
}
