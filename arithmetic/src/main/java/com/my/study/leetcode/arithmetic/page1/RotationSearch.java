package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

public class RotationSearch {

    @Test
    public void test(){

        int[] arr = {3,4,5,6,1,2};
        System.out.println(search( arr, 2));
    }

    public int search(int[] nums, int target) {
        if(nums.length ==0) return -1;
        return find( nums,0, nums.length-1, target);
    }

    private int find(int [] nums, int left, int right, int target){
        if(left == right) return nums[left] == target ? left : -1;
        if(left > right) return -1;

        int midIdx = (left+right)/2;
        int mid = nums[midIdx];
        if(mid == target) return midIdx;

        if(nums[left] <= target){
            if( target < mid || mid < nums[left]){
                return find( nums,left, midIdx-1, target);
            }
            return find( nums,midIdx+1, right, target);
        }else {
            if(mid > nums[right] || target > mid){
                return find(nums, midIdx+1, right, target);
            }
            return find(nums, left, midIdx-1, target);
        }
    }


}
