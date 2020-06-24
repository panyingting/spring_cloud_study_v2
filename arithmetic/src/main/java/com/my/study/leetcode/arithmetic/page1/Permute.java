package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

import java.util.*;

public class Permute {


    @Test
   public void test(){
        int [] nums = {1,2,3};
        System.out.println(permute(nums ));
   }


    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {

        permute( nums, new ArrayList<>(), new boolean[nums.length]);

        return result;
    }

    private void permute(int[] nums, List<Integer> list, boolean added[]){
        if(nums.length == list.size()){
            result.add(new ArrayList<>(list));
            return;
        }

        for(int i=0; i<nums.length; i++){
            if(added[i]) continue;

            list.add( nums[i]);
            added[i] = true;
            permute(nums, list, added);
            added[i] = false;
            list.remove(list.size()-1);
        }

    }

}
