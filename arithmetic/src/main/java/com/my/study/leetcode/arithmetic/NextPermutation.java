package com.my.study.leetcode.arithmetic;

/**
 * 其实最重要的是这一句 ： 算法需要将给定数字序列重新排列成字典序中下一个更大的排列
 * --- 其实意思就是找一个最小，但比当前序列更大的序列
 * 我第一个想到的是插入排序的例子，如果排序过程中，能执行插入，说明是可以调整大小的
 *
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if(nums.length <2) return;
        int i= nums.length-2;

        while ( i>=0){
            if(nums[i] < nums[i+1]){    // 说明可以找到下一个更大的排列

                // 从 i 的右边找出最小的但比 nums[i] 大的数
                int greaterThan = i+1;
                while (greaterThan < nums.length && nums[greaterThan] > nums[i]){
                    greaterThan ++;
                }
                swap(nums, i, greaterThan-1);
                break;
            }
            i--;
        }


        // 旋转，让右边的数变为最小，或者整个数组变为最小
        convert(nums,i+1, nums.length-1);
    }

    private void swap(int [] nums, int idx1, int idx2){
        int tmp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = tmp;
    }

    private void convert(int[]nums, int left, int right){
        while(left < right){
            swap(nums, right, left);
            left ++;
            right --;
        }
    }
}
