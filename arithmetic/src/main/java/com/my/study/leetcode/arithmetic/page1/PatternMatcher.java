package com.my.study.leetcode.arithmetic.page1;

public class PatternMatcher {

    public boolean isMatch(String s, String p) {

        if(s == null || p == null){
            return s == null && p == null;
        }
        if(s.length() == 0 || p.length() == 0){
            return s.length() == 0 && p.length() == 0;
        }

        char pre = 0;
        int idxS = 0;
        boolean all ;
        for(int i=0; i<p.length(); i++){
            if(p.charAt(i) == '.'){
                idxS++;
            }
            else if(p.charAt(i) == '*'){
                if(i == 0){
                    return false;
                }
                pre = p.charAt(i-1);

            }

        }

        return true;
    }

    private boolean matchAll(String p, int index){

        char pre = p.charAt(index);

        return false;

    }




}
