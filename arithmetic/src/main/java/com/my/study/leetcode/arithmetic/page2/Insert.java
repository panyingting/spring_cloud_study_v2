package com.my.study.leetcode.arithmetic.page2;

public class Insert {

    public int[][] insert(int[][] intervals, int[] newInterval) {

        boolean[] merged = new boolean[intervals.length];

        int len = intervals.length+1;

        for(int i=0; i<intervals.length; i++){
            int[] arr = intervals[i];

            // 下界被包含
            if(between(newInterval[0], newInterval[1], arr[0])){

                // 上界没有被包含
                if(!between(newInterval[0], newInterval[1], arr[1])){
                    newInterval[1] = arr[1];
                    merged[i] = true;
                    len--;
                    break;
                }else {
                    // 上界被包含了（整个被包含）
                    merged[i] = true;
                    len--;
                }
            }
            // 上界被包含了（下界没被包含）
            else if( between(newInterval[0], newInterval[1], arr[1])){
                newInterval[0] = arr[0];
                merged[i] = true;
                len--;
            }

            // newInterval 整个被 arr 包含
            else if(between(arr[0], arr[1], newInterval[0])){
                newInterval = arr;
                merged[i] = true;
                len--;
                break;
            }
        }

        int idx = 0;
        boolean added = false;
        int[][] newArr = new int[len][2];

        if(len > 0){
            if(intervals.length == 0 || newInterval[0] <= intervals[0][0]){
                newArr[idx++] = newInterval;
                added = true;
            }else if(newInterval[1] >= intervals[intervals.length-1][1]){
                newArr[newArr.length-1] = newInterval;
                added = true;
            }
        }

        // 说明没有合并
        if(len == intervals.length+1){
            for(int i=0; i<intervals.length; i++){
                if(added || intervals[i][0] < newInterval[0]){
                    newArr[idx++] = intervals[i];
                }else {
                    newArr[idx++] = newInterval;
                    added = true;
                    i--;
                }
            }
        }else {
            for(int i=0; i<intervals.length; i++){
                if(!added && merged[i]){
                    newArr[idx++] = newInterval;
                    added = true;
                }else if(!merged[i]){
                    newArr[idx++] = intervals[i];
                }
            }
        }
        return newArr;
    }

    private boolean between(int left, int right, int checkNum){
        return left <= checkNum && right >= checkNum;
    }
}
