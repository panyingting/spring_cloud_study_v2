package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

import java.util.List;


/**
 * 此题我开始是想用list来存一个 divisor * 2^n 的 的数，然后用 dividend来递归除的，看了评论有个更经典的，如下
 */
public class Divide {

    @Test
    public void test(){
        System.out.println(1<<30);
        System.out.println(1<<34);
    }

    public int divide(int dividend, int divisor) {
        if(dividend == 0) return 0;
        if(divisor == 1) return dividend;
        if(divisor == -1) return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend; // Integer.MIN_VALUE 的数值 为 Integer.MAX_VALUE +1 ，所以不能直接取相反数；


        int symbol = 1;
        if((dividend ^ divisor) < 0 ) symbol = -1;

        long lDividend = dividend < 0 ? -(long)dividend : dividend;
        long lDivisor = divisor < 0 ? -(long)divisor : divisor;
        int result = 0;
        for(int i=31; i>=0; i--){
            long displacementNum = lDividend >> i;
            if(displacementNum >= lDivisor){
                result += 1<<i;
                lDividend -= lDivisor << i;
            }
        }
        return symbol*result;
    }


}
