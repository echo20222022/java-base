package com.base.algorithm.leetcode;

//删除链表中指定的值
public class _18_DeleteListNode {

    public ListNode deleteNode(ListNode head, int val) {

        //递归
        /*if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        head.next = deleteNode(head.next, val);
        return head;*/

        //循环

        ListNode pre = null;
        ListNode cur = head;
        while (cur.val != val) {
            pre = cur;
            cur = cur.next;
        }

        if (cur == null) {
            return null;
        }
        if(cur == head) {
            head = head.next;
        } else {
            pre.next = cur.next;
            cur.next = null;
        }
        return head;
    }


    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

}
