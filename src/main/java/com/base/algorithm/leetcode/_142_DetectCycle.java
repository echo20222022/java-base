package com.base.algorithm.leetcode;

import java.util.HashSet;

//找环点
public class _142_DetectCycle {

    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        ListNode cur = new ListNode(0);
        cur.next = head;
        while (set.add(cur)) {
            if (cur == null) {
                return null;
            }
            cur = cur.next;
        }
        return cur;
    }


    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
}
