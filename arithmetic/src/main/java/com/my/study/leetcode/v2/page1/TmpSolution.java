package com.my.study.leetcode.v2.page1;

import java.util.*;

/**
 * @author : Pan Yingting
 * @date : 2021/10/8 9:52 下午
 */
public class TmpSolution {

    public static void main(String[] args) {

        Object ret = new TmpSolution().candy(new int[]{1,2,3,4,5});
        System.out.println(ret);

        LinkedList<Integer> linkedList = new LinkedList<>();
    }
    public int candy(int[] ratings) {
        int n = ratings.length;
        int ret = 1;
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] == ratings[i - 1]) {
                dec = 0;
                pre = 1;
                ret += pre;
            } else if (ratings[i] > ratings[i - 1]) {
                dec = 0;
                pre ++;
                inc = pre;
                ret += pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }

        }
        return ret;
    }

}
