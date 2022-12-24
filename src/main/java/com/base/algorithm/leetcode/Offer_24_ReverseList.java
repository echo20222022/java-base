package com.base.algorithm.leetcode;

public class Offer_24_ReverseList {

    public ListNode reverseList(ListNode head) {
        /**
        if (head.next == null) {
            return head;
        }
        ListNode node = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return node;
         */

        ListNode pre = null, cur = head, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
   }

}
