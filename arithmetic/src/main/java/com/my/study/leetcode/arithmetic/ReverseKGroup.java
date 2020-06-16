package com.my.study.leetcode.arithmetic;

import java.util.Stack;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 *  
 *
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {

        if(head == null || head.next == null || k <2) return head;

        Stack<ListNode> stack = new Stack<>();
        ListNode realHead = null;
        ListNode pos = head;
        int deep = 0;
        ListNode tail = null;
        while ( pos != null){
            stack.push(pos);
            deep++;
            pos = pos.next;
            if ( deep == k){
                if(realHead == null){
                    realHead = stack.peek();
                }
                if(tail != null){
                    tail.next = stack.peek();
                }
                tail = stack.pop();
                while (!stack.isEmpty()){
                    tail.next = stack.pop();
                    tail = tail.next;
                }
                tail.next = pos;
                deep = 0;
            }
        }
        return realHead == null ? head : realHead;
    }

}
