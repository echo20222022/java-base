package com.base.ds.tree;

import java.util.Map;
import java.util.TreeMap;

/**
 * 字典树，用来实现高效的字符串匹配
 *
 * */
public class Trie {

    private Node root;
    //单词的数量
    private int size;

    public Trie() {
        this.root = new Node();
        this.size = 0;
    }

    //向前缀树中添加一个单词
    public void add(String word) {
        Node cur = root;
        //遍历单词中的每一个字符
        for (int i = 0;i < word.length();i ++) {
            char c = word.charAt(i);
            if (cur.get(c) == null) {
                cur.put(c, new Node());
            }
            cur = cur.get(c);
        }

        if (!cur.isWord) {
            cur.isWord = true;
            size ++;
        }
    }

    //判断释放包含一个单词
    public boolean contains(String word) {
        if (size == 0) {
            return false;
        }
        Node cur = root;
        for (int i = 0;i < word.length();i ++) {
            char c = word.charAt(i);
            if (cur.get(c) == null) {
                return false;
            }
            cur = cur.get(c);
        }
        return cur.isWord;
    }

    //查询释放有包含指定前缀的单词
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0;i < prefix.length();i ++) {
            char c = prefix.charAt(i);
            if (cur.get(c) == null) {
                return false;
            }
            cur = cur.get(c);
        }
        return true;
    }


    public int getSize() {
        return size;
    }

    private static class Node{
        //标记是不是一个单词的结尾
        public boolean isWord;
        public Map<Character, Node> next;

        //默认不是单词结束的节点
        public Node() {
            this(false);
        }

        public Node(boolean isWord) {
            this.isWord = isWord;
            next =new TreeMap<>();
        }

        public Node get(Character c) {
            return next.get(c);
        }
        public void put(Character c, Node node) {
            next.put(c, node);
        }
    }
}
