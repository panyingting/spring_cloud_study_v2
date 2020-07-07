package com.my.study.leetcode.arithmetic.page2;

public class PlusOne {


    public int[] plusOne(int[] digits) {
        boolean move = false;
        int i=digits.length-1;

        while (i >=0){
            if(digits[i] != 9){
                break;
            }
            digits[i] = 0;
            i--;
        }
        if(i<0){
            int[] arr = new int[digits.length+1];
            arr[0] = 1;
            return arr;
        }
        digits[i] = digits[i]+1;

        return digits;
    }
}
