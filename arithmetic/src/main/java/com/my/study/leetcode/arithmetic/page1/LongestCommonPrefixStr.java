package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

public class LongestCommonPrefixStr {

    @Test
    public void test(){
        String[] strs = {"flower","flow","flight"};
        String comnStr = longestCommonPrefix(strs);

        System.out.println(comnStr);
    }
    public String longestCommonPrefix(String[] strs) {

        if(strs.length == 0)
            return "";
        int length = getMinLength(strs);
        StringBuilder commonPrefix = new StringBuilder();
        for(int i=0; i<length; i++){
            char ch = strs[0].charAt(i);
            int j=1;
            for(; j<strs.length; j++){
                if(ch != strs[j].charAt(i)){
                    break;
                }
            }
            if(j == strs.length){
                commonPrefix.append(ch);
            }else {
                break;
            }
        }

        return commonPrefix.toString();
    }

    private int getMinLength(String[] strs){
        int length = strs[0].length();
        for(int i=1; i<strs.length; i++){
            if(strs[i].length() < length){
                length = strs[i].length();
            }
        }
        return length;
    }

}
