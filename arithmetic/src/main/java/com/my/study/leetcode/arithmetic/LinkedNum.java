package com.my.study.leetcode.arithmetic;


import com.alibaba.fastjson.JSON;

/**
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class LinkedNum {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int remain = 0;

        ListNode node = l1;
        ListNode node2 = l2;
        ListNode pre = l1;
        while (node != null && (remain != 0 || node2 != null)){

            int tmp = remain + node.val;
            if(node2 != null){
                tmp += node2.val;
                node2 = node2.next;
            }

            remain = tmp / 10;
            node.val = tmp%10;
            if(node.next == null){
                node.next = node2;
                node2 = null;
            }
            pre = node;
            node = node.next;

        }
        if(remain > 0)
            pre.next = new ListNode(remain);

        return l1;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(9);

        ListNode node = addTwoNumbers(l1, l2);

        System.out.println(JSON.toJSONString(node));

    }
}


 class ListNode {
     int val;
    ListNode next;
    ListNode(int x) { val = x; }

     public int getVal() {
         return val;
     }

     public void setVal(int val) {
         this.val = val;
     }

     public ListNode getNext() {
         return next;
     }

     public void setNext(ListNode next) {
         this.next = next;
     }
 }