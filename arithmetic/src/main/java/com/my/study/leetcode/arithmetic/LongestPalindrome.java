package com.my.study.leetcode.arithmetic;

/**
 * 中心扩散法则，检查越界，注意subString的两个参数对应的意思
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {

        if(s.length() == 0)
            return s;
        int left, right;
        String lStr = String.valueOf(s.charAt(0));

        for(int i=1; i<s.length(); i++){

            if(s.charAt(i) == s.charAt(i-1)){
                left = i-2;
                right = i+1;
                lStr = getNewStr(lStr, s, left, right);
            }
            if(i+1 < s.length() && s.charAt(i+1) == s.charAt(i-1)){
                left = i-2;
                right = i+2;
                lStr = getNewStr(lStr, s, left, right);

            }
        }

        return lStr;
    }

    private String getNewStr(String lStr, String s, int left, int right){
        int tmpLen;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left --;
            right ++;
        }
        tmpLen = right-left-1;
        if(lStr.length() < tmpLen)
            lStr = s.substring(left+1, right);
        return lStr;
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println( longestPalindrome.longestPalindrome("ccc"));
    }

}
