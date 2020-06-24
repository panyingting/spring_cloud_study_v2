package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

public class LongestValidParentheses3 {

    @Test
    public void test(){
        System.out.println(longestValidParentheses("(()"));
    }

    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        int len1 = getLongest(s, 0, s.length(), 1, '(');
        int len2 = getLongest(s, s.length()-1, -1, -1, ')');
        return len1 > len2 ? len1 : len2;

    }

    private int getLongest(String s, int fromIdx, int exclusiveIdx, int add, char flag){

        int longest = 0;
        int chCount = 0;
        int len = 0;
        for(int i = fromIdx; i != exclusiveIdx; i += add){
            if(s.charAt(i) == flag){
                chCount ++;
            }else {
                if(chCount > 0){
                    len +=2;
                    chCount --;

                    // 避免 "()()()" 这种情况而没有计算进来，所以等于0 的时候更新 longest
                    if(chCount == 0){
                        longest = len > longest ? len : longest;
                    }
                }
                else if(chCount == 0 && len > 0){
                    longest = len > longest ? len : longest;
                    len = 0;
                }
            }
        }

        return longest;
    }
}
