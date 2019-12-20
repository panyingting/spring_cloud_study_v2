package com.study.arithmetic.sort;

public class BFPRTSort {

    public static int findNum(int[] arr, int topFromNum){


        int pivotIndex = -1;
        int [] tmpArr = new int[arr.length/5 +1];

        int left = 0;
        int right = arr.length-1;
        do{

            // 不能包含 pivotIndex, 否则死循环
            if(pivotIndex > topFromNum){
                right = pivotIndex-1;
            }
            else if(pivotIndex != -1){
                left = pivotIndex + 1;
            }
            pivotIndex = divideByMiddleIndex(arr, tmpArr, left, right);

        }while (pivotIndex != topFromNum);

        return arr[pivotIndex];
    }


    /**
     * 坑： begin 直接等于 left, 导致 targetIndex = begin - 1 越下界了；
     * 2. 递归调用的时候用了 pivotIndex ， 应该用 targetIndex （新的 pivotIndex），
     */
    private static int divideByMiddleIndex(int[] arr, int tmpArr[], int left, int right){

        if(left == right)
            return left;


        int begin = left;
        int end = right;

        int pivot = findMiddle( arr, tmpArr, left, right);

        for(int i=left; i<=right; i++){
            if(arr[i] == pivot){
                arr[i] = arr[right];
                arr[right] = pivot;
            }
        }

        for(; begin<=end; begin++){

            if(arr[begin] >= pivot){
                for(;end > begin; end --){

                    if(arr[end] < pivot){
                        int tmp = arr[end];
                        arr[end] = arr[begin];
                        arr[begin] = tmp;
                        break;
                    }
                }
            }
            if(begin == end){
                arr[right] = arr[end];
                arr[end] = pivot;
                break;
            }

        }

        return end;
    }


    private static int findMiddle(int[] arr, int tmp[], int leftIndex, int rightIndex){

        if(leftIndex == rightIndex)
            return arr[leftIndex];

        int reminder = (rightIndex - leftIndex + 1) % 5;
        int length = reminder == 0 ? (rightIndex - leftIndex+1) / 5 : (rightIndex - leftIndex+1) / 5 + 1;

        int index = 0;
        int i = leftIndex;
        for(; i <= rightIndex-4; i+=5){
            tmp[index++] = findMiddleForSingleGroup(arr, i, i+4);
        }
        if(index == length-1){
            tmp[index] = findMiddleForSingleGroup( arr, i , rightIndex);
        }

        return findMiddleForSingleGroup(tmp, 0, length-1);
    }

    private static int findMiddleForSingleGroup(int[] arr, int leftIndex, int rightIndex){

        if(leftIndex == rightIndex)
            return arr[leftIndex];
        for(int i = leftIndex+1; i <= rightIndex; i++){

            int tmp = arr[i];
            int j=i-1;
            for(; j >= leftIndex && arr[j] > tmp; j--){
                arr[j+1] = arr[j];
            }

            arr[j+1] = tmp;
        }

        int middleIndex = (leftIndex + rightIndex) / 2 ;
        return arr[ middleIndex];
    }

    public static void main(String[] args) {
        int[] arr = {1,10,9,2,8,3,4,7,5,6,11,20,19,18,17,16,15,14,13,12};
        int num = BFPRTSort.findNum(arr, 19);
        System.out.println(num);

    }

}
