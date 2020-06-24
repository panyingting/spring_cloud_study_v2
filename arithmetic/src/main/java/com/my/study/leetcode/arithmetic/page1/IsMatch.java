package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

/**
 * """aaa"
 * """"
 * ""
 * ""
 */
public class IsMatch {

    @Test
    public void test(){
        System.out.println(isMatch("aa", "a*"));
    }

    public boolean isMatch(String s, String p) {

        return doMatch(s, 0, p, 0);
    }

    private boolean doMatch(String s, int i, String p, int j){
        if(i == s.length() ){
            int idx = j;
            while (idx < p.length() &&( p.charAt(idx) == '*' ) || (idx < p.length()-1 && p.charAt(idx+1) == '*') ){
                idx++;
            }
            return idx == p.length();
        }

        if(j == p.length()) return false;

        if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'){
            if(j < p.length() -1 && p.charAt(j+1) == '*'){
                int idx = i;
                boolean result = doMatch(s, idx, p, j+2);
                while (!result && ++idx < s.length() && (s.charAt(idx) == p.charAt(j) || p.charAt(j) == '.')){
                    result = doMatch(s, idx, p, j+2);
                }
                return result || doMatch(s, idx, p, j+2);
            }
            else {
                return  doMatch(s, i+1, p, j+1);
            }
        }else if( j < p.length() -1 && p.charAt(j+1) == '*'){
            return doMatch(s, i, p, j+2);
        }
        return false;
    }





}
