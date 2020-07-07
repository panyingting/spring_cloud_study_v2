package com.my.study.leetcode.arithmetic.page2;

public class MinPathSum {

    public int minPathSum(int[][] grid) {

        if(grid.length == 0) return 0;

        int[][] dp = new int[grid.length][grid[0].length];

        for(int i=0; i< grid.length; i++){
            for (int j=0; j< grid[0].length; j++){
                if(i==0 && j==0){
                    dp[0][0] = grid[0][0];
                }else if(i==0){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }else if(j==0){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

}