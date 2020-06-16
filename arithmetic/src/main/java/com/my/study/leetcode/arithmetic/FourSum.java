package com.my.study.leetcode.arithmetic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    @Test
    public void test(){
        int[] arr = {1,0,-1,0,-2,2};

        System.out.println(fourSum(arr,0));
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> listList = new ArrayList<>();
        if(nums.length < 4) return listList;

        int sum1;
        int diff;
        int subDiff;
        for(int i=0; i< nums.length-3; i++){
            if(i != 0 && nums[i] == nums[i-1]) continue;
            for( int j = i+1; j<nums.length-2; j++){
                if(j != i+1 && nums[j] == nums[j-1]) continue;
                sum1 = nums[i] + nums[j];
                diff = target - sum1;
                int left = j+1;
                int right = nums.length-1;
                while (left < right){
                    subDiff = diff - nums[left] - nums[right];
                    if (subDiff < 0){
                        right --;
                    }else if(subDiff > 0){
                        left ++;
                    }else {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[left]);
                        list.add(nums[right]);
                        listList.add(list);
                        left ++;
                        right --;
                        while (left < right && nums[left] == nums[left-1] ) left++;
                    }
                }
            }

        }
        return listList;
    }

}
