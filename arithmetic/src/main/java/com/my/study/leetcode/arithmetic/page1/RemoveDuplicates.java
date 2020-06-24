package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

public class RemoveDuplicates {

    @Test
    public void test(){

        int [] nums = {1,1,2};

        System.out.println(removeDuplicates(nums));
    }

    public int removeDuplicates(int[] nums) {
        if(nums.length <2)
            return nums.length;

        int idx = 1;
        int pos = 1;
        while (pos < nums.length){

            while ( pos < nums.length && nums[pos] == nums[pos-1]){
                pos++;
            }
            if( pos < nums.length ){
                nums[idx++] = nums[pos++];
            }

        }
        return idx;
    }


}
