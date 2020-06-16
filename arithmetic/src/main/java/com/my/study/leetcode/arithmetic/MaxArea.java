package com.my.study.leetcode.arithmetic;

import org.junit.Test;

public class MaxArea {


    @Test
    public void test(){

        int[] height={1,8,6,2,5,4,8,3,7};

        int maxArea = maxArea(height);

        System.out.println(maxArea);

    }

    public int maxArea(int[] height) {

        int maxArea = 0;
        if(height.length <2)
            return maxArea;


        for(int left=0; left<height.length-1; left++){

            for( int right = height.length-1, maxHeight = 0; right > left; right--){
                int heightLeft =  height[left];
                int heightRight =  height[right];

                if(heightRight > maxHeight){
                    maxArea = area( heightLeft, heightRight, left, right, maxArea);
                    maxHeight = heightRight;
                }

                if(heightRight >= heightLeft){
                    break;
                }
            }
        }

        return maxArea;
    }

    private int area(int a1, int a2, int left, int right, int maxArea){
        int height = a1 > a2 ? a2 : a1;
        int tmpArea = (right - left) * height;
        return tmpArea > maxArea ? tmpArea : maxArea;
    }


}
