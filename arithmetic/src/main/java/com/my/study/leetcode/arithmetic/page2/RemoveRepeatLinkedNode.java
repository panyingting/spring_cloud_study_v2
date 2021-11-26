package com.my.study.leetcode.arithmetic.page2;

/**
 * @author : Pan Yingting
 * @date : 2021/6/10 2:40 下午
 */
public class RemoveRepeatLinkedNode {
}

  class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode myHead = null;
            ListNode pos = null;
            ListNode prePos = head;
            ListNode nextPos;

            while (prePos != null) {
                nextPos = prePos.next;
                if (nextPos != null && nextPos.val == prePos.val) {
                    while (nextPos != null && nextPos.val == prePos.val) {
                        nextPos = nextPos.next;
                    }
                    prePos = nextPos;
                } else {
                    if (myHead == null) {
                        myHead = prePos;
                        pos = prePos;
                    } else {
                        pos.next = prePos;
                        pos = pos.next;
                    }
                    prePos = prePos.next;
                }
            }
            if (pos != null) {
                pos.next = null;
            }
            return myHead;
    }
}
