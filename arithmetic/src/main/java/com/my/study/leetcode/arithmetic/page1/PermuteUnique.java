package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermuteUnique {

    @Test
    public void test(){
        int [] nums = {0,1,0,0,9};
        System.out.println(permuteUnique(nums ));
    }


    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {

        Arrays.sort(nums);
        permute( nums, new ArrayList<>(), new boolean[nums.length]);

        return result;
    }

    private void permute(int[] nums, List<Integer> list, boolean added[]){
        if(nums.length == list.size()){
            result.add(new ArrayList<>(list));
            return;
        }

        Integer tmp = null;
        for(int i=0; i<nums.length; i++){
            if(added[i]) continue;
            if ( tmp != null && nums[i] == tmp){
                continue;
            }
            tmp = nums[i];
            list.add( nums[i]);
            added[i] = true;
            permute(nums, list, added);
            added[i] = false;
            list.remove(list.size()-1);
        }

    }
}
