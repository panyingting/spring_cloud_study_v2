package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

public class CountAndSay {

    public String countAndSay(int n) {
        String num = "1";
        int count;
        char ch;
        for(int i=2; i<= n; i++){
            ch = num.charAt(0);
            count = 1;
            StringBuilder builder = new StringBuilder();
            for(int j=1; j<num.length(); j++){

                if(ch == num.charAt(j)){
                    count++;
                }else {
                    builder.append(count);
                    builder.append(ch);
                    ch = num.charAt(j);
                    count = 1;
                }
            }
            builder.append(count);
            builder.append(ch);
            num = builder.toString();
        }
        return num;
    }

    @Test
    public void test(){

    }

}
