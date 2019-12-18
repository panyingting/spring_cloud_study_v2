package com.study.arithmetic.sort;

import java.util.Arrays;

public class ShellSort {
    private static int[] sort(int[] source){
        for(int gap = source.length / 2; gap > 0; gap = gap / 2){

            for(int gapIndex = gap; gapIndex < source.length; gapIndex ++){

                int tmp = source[gapIndex];
                int sortIndex = gapIndex - gap;
                for(; sortIndex >= 0 && tmp < source[sortIndex]; sortIndex -= gap){
                    source[sortIndex + gap] = source[sortIndex];
                }
                source[sortIndex + gap] = tmp;
            }
        }
        return source;

    }

    public static void main(String[] args) {
        int[] arr = {1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0};
        ShellSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
