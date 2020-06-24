package com.my.study.leetcode.arithmetic.page1;

public class Trap {
    public int trap(int[] height) {
        if(height.length < 1) return 0;
        int count = 0;
        int left;
        int right = 0;
        for(int i=0; i< height.length; i++){
            if(height[i] != 0){
                left = height[i];
                int rightIdx = 0;
                for( int j = i+1; j<height.length; j++){
                    if(height[j] > right){
                        right = height[j];
                        rightIdx = j;
                    }
                    if(right >= left) break;
                }

                if(rightIdx > 0){
                    int min = Math.min(left, right);
                    for(int k=i+1; k< rightIdx; k++){
                        count += min - height[k];
                    }
                    i = rightIdx-1;
                    right = 0;
                }
            }
        }
        return count;

    }
}
