package com.my.study.leetcode.arithmetic;

public class RemoveElement {

    public int removeElement(int[] nums, int val) {

        if(nums.length == 0)
            return 0;
        int pos = 0;
        for(int i=0; i<nums.length; i++){
            if(nums[i] != val){
                nums[pos++] = nums[i];
            }
        }
        return pos;
    }
}
