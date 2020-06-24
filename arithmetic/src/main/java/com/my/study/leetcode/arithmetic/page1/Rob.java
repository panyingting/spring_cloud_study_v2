package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class Rob {

    @Test
    public void test(){
        int[] arr = {114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240};

        int num = rob(arr);

        System.out.println(num);
    }

    @Test
    public void testSum(){

        List<Long> list = new ArrayList<>();

        long sum = list.stream().mapToLong(e -> e).sum();

        System.out.println(sum);

    }
    /**
     * 198，打家劫舍
     * 动态规划
     */
    public int rob1(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        if (nums.length == 1)
            return nums[0];
        if(nums.length == 2)
            return Math.max(nums[0], nums[1]);
        int [] sum = new int[nums.length];

        sum[0] = nums[0];
        sum[1] = Math.max(nums[0],nums[1]);

        for(int i=2; i<nums.length; i++){
            sum[i] = Math.max(sum[i-1], sum[i-2] + nums[i]);
        }
        return sum[sum.length-1];
    }

    /**
     * 记忆搜索法🔍
     */
    public int rob(int[] nums){
        if(nums.length == 0)
            return 0;
        int memory[] = new int[nums.length];

        Arrays.fill(memory, -1);

        return maxSum(nums, nums.length-1, memory);
    }

    private int maxSum(int[] nums, int idx, int memory[]){

        if(idx == 0){
            return nums[0];
        }
        if(idx == 1){
            return Math.max(nums[0], nums[1]);
        }
        if( memory[idx] != -1)
            return memory[idx];

        int max =  Math.max(maxSum(nums, idx-1, memory), nums[idx]+ maxSum(nums, idx-2, memory));
        memory[idx] = max;
        System.out.println(idx +": "+max+"\uD83D\uDD0D");
        return max;

    }


}
