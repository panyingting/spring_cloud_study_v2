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


//    /**
//     * 以下代码也已走通
//     * 坑： begin 直接等于 left, 导致 targetIndex = begin - 1 越下界了；
//     * 2. 递归调用的时候用了 pivotIndex ， 应该用 targetIndex （新的 pivotIndex），
//     */
//    private static int findMiddleIndex(int[] arr, int left, int right, int pivotIndex){
//
//        if(left >= right)
//            return 0;
//        int begin = left+1;
//        int end = right;
//        int pivot = arr[pivotIndex];
//        int targetIndex = end;
//
//        for(; begin<=end; begin++){
//
//            if(arr[begin] >= pivot){
//
//                targetIndex = begin-1;
//                while(end >= begin){
//
//                    if(arr[end] < pivot){
//                        int tmp = arr[begin];
//                        arr[begin] = arr[end];
//                        arr[end] = tmp;
//                        targetIndex = end - 1;
//                        break;
//                    }
//                    end --;
//                }
//            }
//        }
//
//        arr[pivotIndex] = arr[targetIndex];
//        arr[targetIndex] = pivot;
//
//
//        findMiddleIndex(arr, left, targetIndex-1 , left);
//        findMiddleIndex(arr,  targetIndex+1,  right, targetIndex+1);
//
//        return targetIndex;
//    }

    public static void main(String[] args) {
        int[] arr = {1,4,6,8,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0};
        QuickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
