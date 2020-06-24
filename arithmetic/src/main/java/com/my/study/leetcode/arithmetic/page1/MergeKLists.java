package com.my.study.leetcode.arithmetic.page1;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * 23. 合并K个排序链表
 */
public class MergeKLists {

    @Test
    public  void main() {
        ListNode l1 = new ListNode(2);


        ListNode l2 = null;
        ListNode l3 = new ListNode(-1);


        ListNode[] nodes = {l1, l2, l3};
        ListNode head = mergeKLists(nodes);

        System.out.println(JSON.toJSONString(head));

    }

    public ListNode mergeKLists(ListNode[] lists) {

        if(lists.length == 0)
            return null;
        removeEmptyNode(lists);
        sort(lists, false);
        ListNode head = lists[0];
        ListNode tail = lists[0];
        while ( tail != null){
            if(lists[0] != null){
                lists[0] = lists[0].next;
            }
            sort(lists, true);
            tail.next = lists[0];
            tail = tail.next;
        }

        return head;
    }

    private void removeEmptyNode(ListNode[] lists){
        for(int i=0; i < lists.length-1; i++){
            if(lists[i] == null){
                for(int j=i+1; j<lists.length; j++){
                    if(lists[j] != null){
                        lists[i] = lists[j];
                        lists[j] = null;
                        break;
                    }
                }
            }
        }
    }

    private void sort(ListNode[] lists, boolean notFirst){
        for( int i=1; i < lists.length; i++){
            if(lists[i] == null){
                return;
            }
            ListNode tmp = lists[i];
            int j=i;
            while (j  > 0 && (lists[j-1] == null || tmp.val < lists[j-1].val)){
                lists[j] = lists[j-1];
                j--;
            }
            lists[j] = tmp;
            if( notFirst && j==i)
                return;
        }
    }
}
