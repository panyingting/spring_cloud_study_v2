package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

public class IsMatchDP {

    @Test
    public void test(){
        System.out.println(isMatch("aa", "a*"));
    }
    public boolean isMatch(String s, String p) {

        boolean[][] dp = new boolean[p.length()+1][s.length()+1];

        dp[0][0] = true;

        for(int i=1; i<=p.length(); i++){
            int idxP = i-1;
            int j=1;

            if(j <= s.length()){
                int idxS = j-1;
                if(p.charAt(idxP) == '*'){
                    dp[i][0] = i > 1 && (dp[i-2][0] || dp[i-1][0]);
                    while (idxS < s.length()){
                        if(idxP == 0){
                            dp[i][j] = idxS == 0 && s.charAt(0) == '*';
                        }else {
                            dp[i][j] = (((dp[i-1][j] || dp[i][j-1]) && (p.charAt(idxP-1) == s.charAt(idxS) || p.charAt(idxP-1) == '.')) || dp[i-2][j]);
                        }
                        idxS ++;
                        j++;
                    }
                }else {
                    while (idxS < s.length()){
                        dp[i][j] = dp[i-1][j-1] && (p.charAt(idxP) == s.charAt(idxS) || p.charAt(idxP) == '.');
                        idxS ++;
                        j++;
                    }
                }
            }else {
                dp[i][0] = i > 1 && (dp[i-2][0] || dp[i-1][0]) && p.charAt(idxP) == '*';
            }
        }
        return dp[p.length()][s.length()];
    }

}
