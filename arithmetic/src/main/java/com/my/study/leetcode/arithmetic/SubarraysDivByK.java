package com.my.study.leetcode.arithmetic;

import org.junit.Test;

public class SubarraysDivByK {

    @Test
    public void test(){
        int[] arr = {4,5,0,-2,-3,1};
        int count = subarraysDivByK(arr, 5);
        System.out.println(count);
    }

    /**
     * 同余定理
     */
    public int subarraysDivByK(int[] arr, int k) {

        int[] modCount = new int[k];    // 余数统计，下标为余数，值为余数个数
        modCount[0] = 1;    // 余数为0的算特列（开始就算一个组合）， 余数为其它的，第二个开始算一个组合
        int sum = 0;
        int count = 0;
        for (int anArr : arr) {
            sum += anArr;
            int mod = (sum % k + k) % k;
            count += modCount[mod]++;
        }
        return count;
    }
}
