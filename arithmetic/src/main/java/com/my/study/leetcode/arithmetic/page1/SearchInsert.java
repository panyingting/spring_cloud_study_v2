package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

public class SearchInsert {

    @Test
    public void test(){
        int [] arr = {1,2,3,6};

        System.out.println(searchInsert(arr, 7));
    }
    public int searchInsert(int[] nums, int target) {
        if(nums.length == 0) return 0;
        int left = 0;
        int right = nums.length-1;
        int mIdx = 0;
        int mNum;
        while (left < right){
            mIdx = (left+right)/2;
            mNum = nums[mIdx];
            if(mNum == target) return mIdx;
            if(mNum < target){
                left = mIdx+1;
            }else {
                right = mIdx -1;
            }
        }

        System.out.println(mIdx);
        if(nums[left] == target) return left;
        return nums[left] < target && left < nums.length+1 ? left+1 : left;
    }
}
