package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

public class Multiply {

    @Test
    public void test(){

        int num = 3;
        num *= 3+9;
        System.out.println(num);
        System.out.println(multiply("123", "456"));
    }
    public String multiply(String num1, String num2) {
        int[] bit = new int[num1.length() + num2.length() + 1];
        for(int i=num1.length()-1; i >= 0; i--){
            int val1 = num1.charAt(i) - '0';
            for(int j=num2.length()-1; j >= 0; j--){
                int val2 = num2.charAt(j) - '0';

                int idx = bit.length - i - j - 3;
                int val = val1 * val2 ;
                while (val > 0){
                    val +=  bit[idx];
                    bit[idx] = val%10;
                    val = val / 10;
                    idx++;
                }
            }
        }
        StringBuilder builder = new StringBuilder(bit.length);

        int right = bit.length-1;

        while (right >= 0 && bit[right] == 0){
            right --;
        }
        for(int i=right; i>= 0; i--){
            builder.append(bit[i]);
        }

        return builder.length() > 0 ? builder.toString() : "0";
    }
}
