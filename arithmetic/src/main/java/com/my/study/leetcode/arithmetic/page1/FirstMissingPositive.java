package com.my.study.leetcode.arithmetic.page1;

public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        if(nums.length == 0) return 1;

        int posNum;
        int tmp;
        for( int i=0; i<nums.length; i++){
            posNum = nums[i];
            while ( posNum > 0 && posNum <= nums.length && posNum != nums[posNum-1]){
                tmp = nums[posNum-1];
                nums[posNum-1] = posNum;
                posNum = tmp;
            }
        }

        for(int i=0; i<nums.length; i++){
            if(i+1 != nums[i]){
                return i+1;
            }
        }
        return nums[nums.length-1] + 1;
    }

}
