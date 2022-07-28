package com.base.ds.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 哈夫曼树.
 * */
public class HuffmanTree {

    //前序遍历哈夫曼树
    public static void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.value + " ");
        inOrder(node.right);
    }


    public static Node buildHuffmanTree(int[] arr) {
        List<Node> list = new ArrayList(arr.length);
        for (int i = 0;i < arr.length;i ++) {
            list.add(new Node(arr[i]));
        }

        while (list.size() > 1) {
            //从小到大进行排序
            Collections.sort(list);
            //从排序后的list中取出前两个，构造出一棵新树
            Node left = list.get(0);
            Node right =list.get(1);

            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;

            //把已经参加过构造的节点移除
            list.remove(left);
            list.remove(right);
            //把新构造出来的节点加入到排序序列中
            list.add(parent);
        }
        //剩余的最后一个就是哈夫曼树的根节点
        return list.get(0);
    }

    private static class Node implements Comparable<Node>{
        int value;
        Node left;
        Node right;
        public Node(int value) {
            this(value, null, null);
        }
        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return value - o.value;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,4,7,8};
        Node root = buildHuffmanTree(arr);
        preOrder(root);  //22 8 14 7 7 3 1 2 4
        System.out.println();
        inOrder(root); //8 22 7 14 1 3 2 7 4

        /**

                22
             8      14
                 7      7
                     3     4
                  1     2

         * */
    }
}
