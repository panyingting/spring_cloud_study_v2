package com.study.arithmetic.sort;

import java.util.Arrays;

public class MergeSort {

    public static void sort(int[]tmp, int [] arr, int begin, int end){

        if(end - begin <1)
            return ;

        int mid = (end + begin)/2;
        sort(tmp, arr, begin, mid);
        sort(tmp, arr, mid+1, end);
        merge( tmp, arr, begin, mid, end);


    }

    private static void merge(int[] tmp, int[] source, int begin, int mid, int end ){
        int index = 0;
        int vernier = begin;
        int next = mid+1;
        for(; vernier <= mid; vernier ++){
            for( ;next <= end; next ++){
                if(source[next] <= source[vernier]){
                    tmp[index ++] = source[next];
                }
                else {
                    tmp[index ++] = source[vernier];
                    break;
                }
            }

            if(next > end){
                tmp[index ++] = source[vernier];
            }
        }
        while(next <= end){
            tmp[index ++] = source[next ++];
        }
        vernier = 0;
        for(index = begin; index <= end; index++ ){
            source[index] = tmp[vernier++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0};
        MergeSort.sort(new int[arr.length], arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
