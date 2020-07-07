package com.my.study.leetcode.arithmetic.page2;

public class CanJump2 {
    public boolean canJump(int[] nums) {
        for(int i = nums.length-2; i>=0; i--){
            if(nums[i] ==0){
                int nextIdx = canJump(nums, i);
                if(nextIdx == -1)
                    return false;
                i= nextIdx;
            }
        }
        return true;
    }

    private int canJump(int[] nums, int end){
        for(int i=end-1; i>=0; i--){
            if(nums[i] > end-i){
                return i;
            }
        }
        return -1;
    }
}
