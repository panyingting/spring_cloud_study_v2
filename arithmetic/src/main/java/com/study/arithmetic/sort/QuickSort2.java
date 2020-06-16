package com.study.arithmetic.sort;

import java.util.Arrays;

public class QuickSort2 {

    public static void main(String[] args) {

        int[] arr = {1,4,6,8,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0};
        QuickSort2.quickSort(arr);
        System.out.println(Arrays.toString(arr));

    }


    public static void quickSort(int[] arr){
        doqQuickSort(arr, 0, arr.length-1);
    }

    public static void doqQuickSort(int[] arr, int begin, int end){
        if(begin >= end)
            return;

        int pivot = arr[end];
        int pivotIdx = end;
        for(int left = begin, right = end-1; left <= right; left++){
            if(arr[left] > pivot){
                for(; right >left; right--){
                    if(arr[right] <= pivot){
                        swap(arr, right, left);
                        break;
                    }
                }

                if(left == right){
                    arr[end] = arr[left];
                    arr[left] = pivot;
                    pivotIdx = left;
                }
            }
        }

        doqQuickSort(arr, begin, pivotIdx-1);
        doqQuickSort(arr, pivotIdx+1, end);
    }

    private static void swap(int[]arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
}
