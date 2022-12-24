package com.base.algorithm.leetcode;

public class _24_SwapPairs {

    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next = head.next, nextTwo;
        if (next != null){
            nextTwo = next.next;
            next.next = head;
            head.next = swapPairs(nextTwo);
        } else {
            return head;
        }
        return next;
    }

    public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
