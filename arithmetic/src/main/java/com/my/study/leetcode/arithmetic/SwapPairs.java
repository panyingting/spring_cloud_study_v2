package com.my.study.leetcode.arithmetic;

public class SwapPairs {

    public ListNode swapPairs(ListNode head) {

        if(head == null || head.next == null){
            return head;
        }

        return revert(head);
    }

    private ListNode revert(ListNode listNode){
        if(listNode.next == null){
            return listNode;
        }
        ListNode next = listNode.next;
        listNode.next = next.next;
        next.next = listNode;
        if (listNode.next != null){
            listNode.next = revert(listNode.next);
        }
        return next;
    }

}
