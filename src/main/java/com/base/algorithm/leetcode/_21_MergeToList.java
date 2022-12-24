package com.base.algorithm.leetcode;

//合并两个有序链表
public class _21_MergeToList {


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //判断特殊情况
        if(list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        //定义一个新链表的dummyHead
        ListNode node = new ListNode();
        //新链表的移动指针
        ListNode temp = node;
        while (list1 != null && list2 != null) {
            if (list2.val >= list1.val) {
                temp.next = list1;
                list1 = list1.next;
            } else if (list1.val < list2.val) {
                temp.next = list2;
                list2 = list2.next;
            }
            //向后推进
            temp = temp.next;

            if (list1 == null) {
                temp.next = list2;
                return node.next;
            }

            if (list2 == null) {
                temp.next = list1;
                return node.next;
            }

        }
        return node.next;
    }


    public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int val) { this.val = val; }
       ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
