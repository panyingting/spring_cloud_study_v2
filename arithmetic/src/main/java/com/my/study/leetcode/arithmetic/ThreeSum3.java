package com.my.study.leetcode.arithmetic;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum3 {

    @Test
    public void test(){
        int nums[] = {-2,0,1,1,2};

        List<List<Integer>> ls = threeSum(nums);

        System.out.println(JSON.toJSONString(ls));
    }

    public List<List<Integer>> threeSum(int[] nums) {

        if(nums.length < 3)
            return Collections.EMPTY_LIST;
        List<List<Integer>> group = new ArrayList<>();
        Arrays.sort(nums);

        for(int i=0; i<= nums.length-3; i++){
            if(nums[i] > 0)
                break;
            if(i == 0 || nums[i] != nums[i-1]){
                int left = i+1;
                int right = nums.length-1;
                while (left < right){

                    int tSum = nums[left] + nums[right];
                    if(tSum == - nums[i]){
                        group.add( Arrays.asList(nums[i], nums[left], nums[right]));

                        while (left < right && nums[left] == nums[left+1])
                            left++;
                        while (right > left && nums[right] == nums[right-1])
                            right --;
                        left++;
                        right--;
                    }else if(tSum > -nums[i]){
                        right--;
                    }else {
                        left++;
                    }
                }
            }
        }
        return group;
    }
}
