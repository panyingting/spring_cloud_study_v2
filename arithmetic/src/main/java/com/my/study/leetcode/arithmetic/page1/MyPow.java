package com.my.study.leetcode.arithmetic.page1;

public class MyPow {

    public double myPow(double x, int n) {
        boolean positive = true;
        double tmp = 1;
        if( n < 0){
            if(n == Integer.MIN_VALUE){
                n = Integer.MAX_VALUE;
                tmp = x;
            }else {
                n = -n;
            }
            positive = false;
        }
        if(n == 0) return 1;

        double num = myPow(x, n/2);
        double sum = num * num *tmp;
        if(n % 2 == 1){
            sum *= x;
        }
        return positive ? sum: 1/sum;
    }

}
