package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

import java.util.Arrays;

public class ThreeSumClosest {

    @Test
    public void test(){

        // -4 -1 1 2
        System.out.println(threeSumClosest(new int[]{-1,2,1,-4}, 1));
    }

    public int threeSumClosest(int[] nums, int target) {
        if(nums.length <3) return 0;
        Arrays.sort(nums);
        int sum;
        int greatVal = Integer.MAX_VALUE;
        int allDiff;
        for(int i=0; i <nums.length-2; i++){
            int diff = target - nums[i];

            // 为什么 left 可以为 i + 1 呢，是因为i之前的数子都可以不用检查了，因为之前的数字都游走过来，它们已找到最佳值，所以肯定不用再查了
            int left = i+1, right = nums.length-1;

            while (left < right){
                sum = nums[left] + nums[right];

                allDiff = diff-sum;
                if(allDiff > 0){
                    left++;
                }else if(allDiff < 0){
                    right --;
                }else {
                    return target;
                }
                if(Math.abs(allDiff) < Math.abs(greatVal)){
                    greatVal = allDiff;
                }
            }
        }

        return target - greatVal;

    }


}
