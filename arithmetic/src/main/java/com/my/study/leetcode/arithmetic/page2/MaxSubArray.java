package com.my.study.leetcode.arithmetic.page2;

public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        if(nums.length == 0) return 0;
        int dp[] = new int[nums.length];
        dp[0] = nums[0];

        for(int i=1; i<dp.length; i++){
            dp[i] = dp[i-1] > 0 ? dp[i-1] + nums[i] : nums[i];
        }

        int max = dp[0];
        for(int i=1; i<dp.length; i++){
            if(dp[i] > max){
                max = dp[i];
            }
        }
        return max;
    }

}
