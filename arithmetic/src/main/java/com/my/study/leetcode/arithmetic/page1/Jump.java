package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

public class Jump {

    @Test
    public void test(){

        int[] nums = {5,4,0,1,3,6,8,0,9,4,9,1,8,7,4,8};

        System.out.println(jump(nums));
    }

    public int jump(int[] nums) {
        if(nums.length <= 1) return 0;
        return doJump(nums, 0, 0);
    }

    private int doJump(int[] nums, int begin, int stepCount){
        if(nums[begin] + begin >= nums.length-1)
            return stepCount+1;

        int maxStep = nums[begin];
        int furthest = begin+maxStep;
        int nextMaxIdx = furthest;


        for(int i=begin+1; i< begin+maxStep; i++){
            if(nums[i] - (furthest-i) > nums[nextMaxIdx] - (furthest-nextMaxIdx))
                nextMaxIdx = i;
        }
        return doJump(nums, nextMaxIdx, stepCount+1);

    }

}
