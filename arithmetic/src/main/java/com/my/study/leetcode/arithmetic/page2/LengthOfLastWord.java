package com.my.study.leetcode.arithmetic.page2;

public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if(s.isEmpty()) return 0;

        int len = 0;
        for(int i=s.length()-1; i>=0; i--){
            if(s.charAt(i) == ' '){
                if(len > 0) return len;
            }else {
                len++;
            }
        }
        return len;
    }
}
