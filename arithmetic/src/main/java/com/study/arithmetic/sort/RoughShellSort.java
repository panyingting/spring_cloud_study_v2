package com.study.arithmetic.sort;

import java.util.Arrays;

public class RoughShellSort {

    private static int[] sort(int[] source){
        for(int gap = source.length / 2; gap > 0; gap = gap / 2){

            // 其实多一个循环问题不大， 和三个循环差不多，最后当 gap 为 1 时 此循环只会执行一次，只是增加了代码复杂度
            for(int gapIndex = 0; gapIndex < gap; gapIndex ++){

                for(int index = gapIndex; index < source.length - gap; index += gap){

                    int tmp = source[index + gap];
                    int sortIndex = index;
                    for(; sortIndex >= 0 && tmp < source[sortIndex]; sortIndex -= gap){
                        source[sortIndex + gap] = source[sortIndex];
                    }
                    source[sortIndex + gap] = tmp;

                }
            }
        }
        return source;

    }

    public static void main(String[] args) {
        int[] arr = {1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0};
        RoughShellSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
