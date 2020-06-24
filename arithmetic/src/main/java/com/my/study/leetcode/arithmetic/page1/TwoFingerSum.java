package com.my.study.leetcode.arithmetic.page1;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;



public class TwoFingerSum {

    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> valIndexMap = new HashMap<>(nums.length);
        for(int i=0; i<nums.length; i++){
            int val = nums[i];
            Integer otherVal = target - val;
            if(valIndexMap.keySet().contains(otherVal))
                return new int[]{valIndexMap.get(otherVal),  i};
            valIndexMap.put(val, i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0, 39};

        int[] result = twoSum(arr, 40);

        System.out.println(JSON.toJSONString(result));
    }

}
