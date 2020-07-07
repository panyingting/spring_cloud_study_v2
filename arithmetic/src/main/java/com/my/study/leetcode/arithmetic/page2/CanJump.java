package com.my.study.leetcode.arithmetic.page2;

public class CanJump {
    public boolean canJump(int[] nums) {
        if(nums.length <= 1) return true;
        if(nums[0] == 0) return false;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            if(dp[i-1] == 1 && nums[i] == 0){
                return i == nums.length-1;
            }

            dp[i] = Math.max(dp[i-1] -1,nums[i]);
        }
        return true;
    }
}
