package com.study.arithmetic.sort;

import org.junit.Test;

import java.util.Arrays;

public class BFPRTSort2 {

    @Test
    public void sort(){
        int[] arr = {1,4,6,8,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0};

        int topK = topK(arr, arr.length-12);

        System.out.println(topK);
    }

    @Test
    public void main1() {

        int[] arr = {1,4,6,8,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0};
        sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    public int topK(int arr[], int k){

        return getTopK(arr, 0, arr.length-1, k);

    }

    public int getTopK(int arr[], int begin, int end, int k){
        int pivot = getPivot(arr, begin, end);

        // 大于等于 pivot 的在又边，左边是小于 pivot 的
        int index = partition(arr, begin, end, pivot);
        if (index == k-1){
            return pivot;
        }
        if ( index < k-1){
            for(int i=index; i<= end; i++){
                if(arr[i] == pivot){
                    swap( arr, index, i);
                }
            }
            return getTopK(arr, index+1, end, k);
        }
        return getTopK(arr, begin, index-1, k);
    }

    public int partition(int[] arr, int begin, int end, int pivot){


        int  right = end;
        for(int left = begin; left <= right; left++){

            if(arr[left] >= pivot){
                for(; right >left && arr[right] >= pivot; right--){
                }
                if(arr[right] < pivot){
                    swap(arr,  right, left);
                }
            }
            if(left == right){
                break;
            }
        }
        return right;
    }

    public int getPivot(int arr[], int begin, int end){

        int count = end - begin +1;

        if(count <=5){
            sort(arr, begin, end);
            return arr[ begin+ (count+1)/2 -1 ];
        }

        int mod = count % 5;
        int group = count / 5;
        int newLength = mod == 0 ? group : group +1;
        int newArr[] = new int[newLength];
        for(int i=0; i<newLength; i++){
            int newEnd = begin + i*5 + 4;
            if(newEnd > end){
                newEnd = end;
            }
            newArr[i] = getPivot(arr, begin + i*5, newEnd);
        }
        return getPivot(newArr, 0, newLength-1);

    }
    private static void swap(int[]arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
    private void sort(int arr[], int begin, int end){
        for(int i=begin+1; i<= end; i++){
            int tmp = arr[i];
            int j = i-1;
            for (; j>= begin && arr[j] > tmp; j--){
                arr[j+1] = arr[j];
            }
            arr[j+1] = tmp;
        }
    }
}
