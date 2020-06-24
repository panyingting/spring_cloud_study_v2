package com.my.study.leetcode.arithmetic.page1;

public class SearchRange {


    public int[] searchRange(int[] nums, int target) {

        int left = findIdx(nums, 0, nums.length-1, target, true);
        if(left <0) return new int[]{-1, -1};

        int right = findIdx(nums, left, nums.length-1, target, false);
        return new int[]{left, right};
    }


    private int findIdx(int[] nums, int left, int right , int target, boolean leftFlag){
        if(left > right) return -1;
        if(left == right) return nums[left] == target ? left : -1;

        int mIdx = (left + right)/2;

        int midNum = nums[mIdx];

        if(midNum < target){
            return findIdx(nums, mIdx+1, right, target, leftFlag);
        }
        if(midNum > target){
            return findIdx(nums, left, mIdx-1, target, leftFlag);
        }

        // midNum 等于 target的时候，就根据flag 找起始位置和结束位置了
        if(leftFlag){
            return findIdx(nums, left, mIdx, target, true);
        }
        if(nums[right] == target) return right; // 找右边的时候这个必须有，并且下面得是 right -1, 不然 由于  int mIdx = (left + right)/2 死循环
        return findIdx(nums, mIdx, right-1, target, false);
    }
}
