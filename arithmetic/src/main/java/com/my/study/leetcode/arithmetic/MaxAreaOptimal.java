package com.my.study.leetcode.arithmetic;

import org.junit.Test;

public class MaxAreaOptimal {


    @Test
    public void test(){

        int[] height={1,8,6,2,5,4,8,3,7};

        int maxArea = maxArea(height);

        System.out.println(maxArea);

    }

    public int maxArea(int[] height) {
        if(height.length <2)
            return 0;

        int left = 0;
        int right = height.length-1;
        int maxHeight = Math.min( height[left], height[right]);
        int maxArea = maxHeight *  (right -left);

        while (right > left){
            int leftH = height[left];
            int rightH = height[right];
            int tmpH = Math.min(leftH, rightH);
            if(tmpH > maxHeight){
                maxHeight = tmpH;
                int tmpArea = maxHeight *  (right -left);
                if(tmpArea > maxArea)
                    maxArea = tmpArea;
            }
            if(leftH > rightH){
                right --;
            }else {
                left ++;
            }
        }
        return maxArea;
    }


}
