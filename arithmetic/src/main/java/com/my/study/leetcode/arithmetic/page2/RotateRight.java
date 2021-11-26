package com.my.study.leetcode.arithmetic.page2;



public class RotateRight {

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null ) return null;

        ListNode left = head;
        ListNode tail = head;
        int len = 1;
        while (tail.next != null){
            tail = tail.next;
            len++;
        }
        k = k % len + 1;
        if(k == 1) return head;

        while (len != k){
            left = left.next;
            len --;
        }
        ListNode newHead = left.next;
        left.next = null;
        tail.next = head;
        return newHead;



    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString(){
            if(next != null) {
                return val +"->"+next;
            }
            return String.valueOf(val);
        }
    }
}
