package com.study.arithmetic.letcode;

public class IntegerIsPalindrome {

    public boolean isPalindrome(int x) {

        if(x < 0)
            return false;

        int y = 0;
        int orign = x;
        while(x > 0){
            y = y *10 + x%10;
            x = x / 10;
        }

        return y == orign;

    }

    public static void main(String[] args) {
        System.out.println(new IntegerIsPalindrome().isPalindrome(1010));
    }
}
