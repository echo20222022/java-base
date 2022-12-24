package com.base.algorithm.leetcode;

import java.util.Stack;

//从尾到头打印链表
public class Offer_06_ReversionPrintList {

    /**
     * 利用栈
     * 利用递归
     * */
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        int[] res = new int[stack.size()];
        for(int i = 0;i < res.length;i ++) {
            res[i] = stack.pop();
        }
        return res;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
   }

}
