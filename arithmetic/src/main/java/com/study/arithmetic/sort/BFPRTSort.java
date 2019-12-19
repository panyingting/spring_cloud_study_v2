package com.study.arithmetic.sort;

import java.util.Arrays;

public class BFPRTSort {

    public static void sort(int[] arr){


    }


    /**
     * 坑： begin 直接等于 left, 导致 targetIndex = begin - 1 越下界了；
     * 2. 递归调用的时候用了 pivotIndex ， 应该用 targetIndex （新的 pivotIndex），
     */
    private static int findMiddleIndex(int[] arr, int left, int right, int pivotIndex){

        if(left >= right)
            return 0;
        int begin = left+1;
        int end = right;
        int pivot = arr[pivotIndex];
        int targetIndex = end;

        for(; begin<=end; begin++){

            if(arr[begin] >= pivot){

                targetIndex = begin-1;
                while(end >= begin){

                    if(arr[end] < pivot){
                        int tmp = arr[begin];
                        arr[begin] = arr[end];
                        arr[end] = tmp;
                        targetIndex = end - 1;
                        break;
                    }
                    end --;
                }
            }
        }

        arr[pivotIndex] = arr[targetIndex];
        arr[targetIndex] = pivot;


        findMiddleIndex(arr, left, targetIndex-1 , left);
        findMiddleIndex(arr,  targetIndex+1,  right, targetIndex+1);

        return targetIndex;
    }


    public static int findMiddle(int[] arr, int tmp[], int leftIndex, int rightIndex){

        int reminder = (rightIndex - leftIndex) % 5;
        int length = reminder == 0 ? (rightIndex - leftIndex) / 5 : (rightIndex - leftIndex) / 5 + 1;

        int index = 0;
        int i = leftIndex;
        for(; i <= rightIndex-5; i+=5){
            tmp[index++] = findMiddleForSingleGroup(arr, i, i+4);
        }
        if(length > index){
            tmp[index] = findMiddleForSingleGroup( arr, i , rightIndex);
        }

        return findMiddleForSingleGroup(tmp, 0, length);
    }

    private static int findMiddleForSingleGroup(int[] arr, int leftIndex, int rightIndex){

        for(int i = leftIndex+1; i <= rightIndex; i++){

            int tmp = arr[i];
            int j=i-1;
            for(; j >= leftIndex && arr[j] > tmp; j--){
                arr[j+1] = arr[j];
            }

            arr[j+1] = tmp;
        }

        int middleIndex = (leftIndex + rightIndex) % 2;
        middleIndex = middleIndex == 0 ? (leftIndex + rightIndex) / 2 : (leftIndex + rightIndex) / 2 +1;
        return arr[ middleIndex];
    }

    public static void main(String[] args) {
        int[] arr = {1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,3,3,3,3,3,3,3,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0};
//        BFPRTSort.findMiddleForSingleGroup(arr, 0, arr.length-1);
        BFPRTSort.findMiddleIndex(arr, 0, arr.length-1, 0);
        System.out.println(Arrays.toString(arr));

    }

}
