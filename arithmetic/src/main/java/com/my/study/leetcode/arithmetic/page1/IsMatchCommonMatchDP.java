package com.my.study.leetcode.arithmetic.page1;

public class IsMatchCommonMatchDP {


    public boolean isMatch(String s, String p) {

        if(p.length() ==0 || s.length() == 0){
            if(s.length() == 0){
                int i=0;
                while (i < p.length()){
                    if(p.charAt(i) != '*') return false;
                    i++;
                }
                return true;
            }
            return false;
        }
        boolean[][] dp = new boolean[p.length()][s.length()];
        boolean allMatchChar = true;
        for(int i=0; i<p.length(); i++){

            if(p.charAt(i) == '*'){
                boolean mark = allMatchChar;
                for (int j=0; j<s.length(); j++){
                    if(!mark){
                        mark = dp[i-1][j];
                    }
                    dp[i][j] = mark;
                }
            }else {
                for (int j=0; j<s.length(); j++){
                    if( p.charAt(i) == s.charAt(j) || p.charAt(i) == '?'){
                        if(i == 0){
                            dp[i][j] = j == 0;
                        }else {
                            dp[i][j] = (j == 0) ? allMatchChar : dp[i-1][j-1];
                        }
                    }else {
                        dp[i][j] = false;
                    }

                }
                allMatchChar = false;
            }
        }
        return dp[p.length()-1][s.length()-1];
    }
}
