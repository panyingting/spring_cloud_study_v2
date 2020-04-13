package com.my.study.leetcode.arithmetic;

public class IntReverseDigit {

    public int reverse(int x) {

        if(x == 0)
            return 0;
        long newInt = 0;
        for(;x != 0; x = x /10){

            newInt = newInt*10 + x%10;
        }
        return newInt > Integer.MAX_VALUE || newInt < Integer.MIN_VALUE ? 0 : new Long(newInt).intValue();

        /**
         * int max = 0x7fffffff, min = 0x80000000;//int的最大值最小值
         *         long rs = 0;//用long类型判断溢出
         *         for(;x != 0;rs = rs*10+x%10,x/=10);//逆序，正负通吃，不用单独考虑负值
         *         return rs>max||rs<min?0: (int)rs;//超了最大值低于最小值就返回0
         */
    }

    public int tmp(int x){
        if(x == 0)
            return 0;
        long newInt = 0;
        for(;x != 0; x = x /10){

            newInt = newInt*10 + x%10;
        }
        return newInt > Integer.MAX_VALUE || newInt < Integer.MIN_VALUE ? 0 : new Long(newInt).intValue();
    }

    public static void main(String[] args) {
        System.out.println( new IntReverseDigit().reverse(1463847412));
        System.out.println( Integer.MAX_VALUE  );
        System.out.println( 214748364*10);
    }
}
