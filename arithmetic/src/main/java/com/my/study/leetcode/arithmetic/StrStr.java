package com.my.study.leetcode.arithmetic;

import org.junit.Test;

public class StrStr {

    @Test
    public void test (){
        System.out.println(strStr("",""));
    }
    public int strStr(String haystack, String needle) {
        if(needle == null || needle.length() == 0) return 0;
        if(haystack == null  || haystack.length() < needle.length()) return -1;
        char ch0 = needle.charAt(0);
        int lessStart = haystack.length() - needle.length();
        out: for(int i=0; i<= lessStart; i++){
            if( haystack.charAt(i) == ch0 ){
                for(int j=1; j< needle.length(); j++){
                    if(needle.charAt(j) != haystack.charAt(i+j)){
                        continue out;
                    }
                }
                return i;
            }
        }
        return -1;

    }
}
