package com.my.study.leetcode.arithmetic;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Arrays;

public class MergeKLists2 {

    @Test
    public  void main() {
        ListNode l1 = new ListNode(3);


        ListNode l2 = null;
        ListNode l3 = new ListNode(-2);


        ListNode[] nodes = {l1,l1, l1, l3};
        ListNode head = mergeKLists(nodes);

        System.out.println(JSON.toJSONString(head));

    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)
            return null;
        if(lists.length == 1)
            return lists[0];
        if(lists.length == 2){
            return mergeTwoList(lists[0], lists[1]);
        }
        int mid = lists.length/2;
        ListNode[] listOne= new ListNode[mid];
        ListNode[] listTwo= new ListNode[ lists.length - mid];
       System.arraycopy(lists, 0, listOne, 0, mid);
       System.arraycopy(lists, mid, listTwo, 0, lists.length-mid);
        return mergeTwoList( mergeKLists(listOne), mergeKLists(listTwo));


    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2){
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        ListNode tmp;
        if(l1.val > l2.val){
            tmp = l2;
            l2 = l2.next;
            tmp.next = mergeTwoList(l1, l2);
        }else {
            tmp = l1;
            l1 = l1.next;
            tmp.next = mergeTwoList(l1, l2);
        }
        return tmp;
    }
}
