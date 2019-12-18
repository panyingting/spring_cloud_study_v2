package com.study.arithmetic.sort;

import java.util.Arrays;

public class QuickSort {

    public static void sort(int[]arr){

        doSort(arr, 0, arr.length-1);

    }

    private static void doSort( int[] arr, int start, int end){
        if(start == end)
            return;
        int pivot = arr[start];
        int highIndex = end;
        int pivotIndex = end;   // 游标下标，找到大的就是 index -1; 交换之后就 加1
        int index = start;
        for(; index <= highIndex; index++){

            if(arr[index] > pivot){
                pivotIndex = index -1;
                for(; highIndex > index; highIndex--){
                    if(arr[highIndex] <= pivot){
                        int tmp = arr[index];
                        arr[index] = arr[highIndex];
                        arr[highIndex] = tmp;
                        pivotIndex ++;
                        break;
                    }
                }
            }
        }
        arr[start] = arr[pivotIndex];
        arr[pivotIndex] = pivot;

        doSort(arr, start, highIndex-1);
        doSort(arr, highIndex, end);
    }

    public static void main(String[] args) {
        int[] arr = {1,4,6,8,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0};
        QuickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
