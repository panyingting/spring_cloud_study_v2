package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

public class MergeTwoLists {

    @Test
    public void test(){
        ListNode l1 = new ListNode(6);
        l1.next =  new ListNode(8);
        l1.next.next =  new ListNode(10);
        ListNode l2 = new ListNode(3);
        l2.next =  new ListNode(8);
        l2.next.next =  new ListNode(10);

        ListNode node = mergeTwoLists(l1, l2);

        System.out.println(node);

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 ==null)
            return l2;
        if(l2 == null)
            return l1;
        ListNode head = l1.val > l2.val ? l2 : l1;
        ListNode tail = head;
        while (l1 != null && l2 != null){

            while(l1 != null && l1.val <= l2.val){
                ListNode tmp = l1;
                l1 = l1.next;
                tail.next = tmp;
                tail = tmp;
            }
            if(l1 != null){
                while(l2 != null && l2.val <= l1.val){
                    ListNode tmp = l2;
                    l2 = l2.next;
                    tail.next = tmp;
                    tail = tmp;
                }
            }
        }
        if(l1 == null){
            tail.next = l2;
        }
        else {
            tail.next = l1;
        }
        return head;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        public String toString(){
            if(next != null)
                return val +"->"+next;
            return String.valueOf(val);
        }
    }
}
