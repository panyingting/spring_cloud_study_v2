package com.my.study.leetcode.arithmetic;

import org.junit.Test;

import java.text.SimpleDateFormat;

public class ReverseKGroup2 {

    @Test
    public void test(){
        try {
            long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-05-29 01:00:00").getTime();
            long time2 = ( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-05-29 20:00:00").getTime());
            System.out.println(time);
            System.out.println(time2);
        }catch (Exception ex){

        }

    }
    public ListNode reverseKGroup(ListNode head, int k) {

        if(head == null || head.next == null || k <2) return head;

        ListNode left = head;
        ListNode right = head;
        ListNode newHead = null;
        ListNode tail = null;
        int i=1;
        while( right.next != null){
            right = right.next;
            i++;
            if(i == k){
                if(newHead == null){
                    newHead = right;
                } else {
                    tail.next = right;
                }

                revert( left, right, right.next);

                tail = left;
                right = tail.next;
                left = tail.next;

                if(right == null){
                    break;
                }
                i=1;
            }
        }

        return newHead;
    }

    private void revert(ListNode l1, ListNode l2, ListNode tail){
        if(l1 == l2){
            return;
        }
        revert(l1.next, l2, null);
        l1.next.next = l1;
        l1.next = tail;
    }


    private ListNode revert(ListNode listNode, int k){
        ListNode left = listNode;
        ListNode pos = listNode.next;
        ListNode right = pos.next;
        for(int j=2; j<=k; j++){
            pos.next = left;
            left = pos;
            pos = right;
            if(right == null){
                break;
            }
            right = right.next;
        }
        listNode.next = pos;
        return left;
    }
}
