package com.my.study.leetcode.arithmetic.page1;

public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode left = null;
        ListNode right = head;

        // 先走N-1步,使得 head成为 倒数第N 个元素
        while(--n > 0){
            right = right.next;
        }

        if(right.next == null){
            return head.next;
        }
        // 若后面还有节点，则 left继续跟着走下去
        left = head;
        right = right.next;
        while (right.next != null){
            left = left.next;
            right = right.next;
        }

        left.next = left.next.next;
        return head;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
