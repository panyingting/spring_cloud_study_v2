package com.my.study.leetcode.arithmetic;

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
        for(int i=0; i<p.length(); i++){
            if(p.charAt(i) == '.'){

            }

        }

        return true;
    }

    private boolean matchAll(String p, int index){

        char pre = p.charAt(index);

        return false;

    }




}
