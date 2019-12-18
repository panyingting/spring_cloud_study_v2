package com.study.arithmetic.sort;

import java.util.Arrays;

public class InsertSort {

    public static void sort(int[]arr){

        for(int i=0; i<arr.length - 1; i++){
            int tmp = arr[i+1];

            int j = i;
            for(; j >= 0 && tmp < arr[j]; j--){
                arr[j+1] = arr[j];
            }

            arr[j+1] = tmp;
        }

    }

    public static void main(String[] args) {
        int[] arr = {1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0};
        InsertSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
