package com.study.arithmetic;

public class HeapSort {

    public static void sort(int arr[]){

        for(int fromIndex = arr.length/2; fromIndex >= 0; fromIndex --)
            doSort(arr, fromIndex, arr.length);
    }

    private static void doSort(int[] arr, int fromIndex, int len){

        int left = fromIndex*2 +1;

        if(left >= len)
            return;

        int larger = fromIndex;
        if( arr[left] > arr[larger])
            larger = left;

        int right = fromIndex*2 + 2;
        if(right < len && arr[right] > arr[larger])
            larger = right;

        // 需要调整，
        if(larger != fromIndex){
            int tmp = arr[larger];
            arr[larger] = arr[fromIndex];
            arr[fromIndex] = tmp;

            // 检查被调整的分支，顶部下来的元素，不一定比下面的都大
            doSort( arr, larger, len);
        }
    }

    private static void print(int[] arr){

        int n = 0;
        int count = 1;
        for(int index = 0; index < arr.length; index ++){

            if(count == index){
                n ++;
                count += Math.pow(2, n);
                System.out.println();
            }

            System.out.print(arr[index]+"  ");

        }
    }

    public static void main(String[] args) {
        int[] arr = {1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54,1,4,6,8,9,0,12,14,65,111,45,43,23,4,22,54, 1,4,6,8,9,0};
        HeapSort.sort(arr);
        print(arr);

        System.out.println("\n\n");
        for(int len = arr.length; len > 0; len --){
            System.out.print(arr[0] +" ");
            arr[0] = arr[len - 1];
            doSort( arr, 0, len);
        }
    }
}
