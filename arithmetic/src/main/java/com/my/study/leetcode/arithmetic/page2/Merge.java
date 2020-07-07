package com.my.study.leetcode.arithmetic.page2;

import java.util.HashSet;
import java.util.Set;

public class Merge {


    public int[][] merge(int[][] intervals) {
        if(intervals.length <=1) return intervals;

        boolean[] merged = new boolean[intervals.length];   // 用于标记是否已被合并

        int ignore = -1;
        int len = intervals.length;                         // 计算合并之后的数组元素数量

        for(int i=0; i<intervals.length-1; i++){            // 外层循环找出与此区间有交集的区间
            int[] currInterval = intervals[i];

            if(merged[i] && ignore != i) continue;

            int tmpLen = len;
            for(int j=i+1; j<intervals.length; j++){
                if(merged[j]) continue;
                int[] tmpInterval = intervals[j];

                if(between(tmpInterval[0], tmpInterval[1], currInterval[0])){
                    currInterval[0] = tmpInterval[0];
                    if(currInterval[1] < tmpInterval[1]){
                        currInterval[1] = tmpInterval[1];
                    }
                    merged[j] = true;
                    len--;
                    continue;
                }
                if(between(tmpInterval[0], tmpInterval[1], currInterval[1])){
                    currInterval[1] = tmpInterval[1];
                    merged[j] = true;
                    len--;
                    continue;
                }

                // 满足这条件时，只有一种情况了，就是被 tmpInterval 被 currInterval包含住了
                if(between(currInterval[0], currInterval[1], tmpInterval[0])){
                    merged[j] = true;
                    len--;
                }
            }
            if(tmpLen != len){
                ignore = i;         // 当前循环到才合并的则忽略merged条件，也就是从头再找一次，
                i--;
            }
        }

        int[][] mergeIntervals = new int[len][2];

        int idx = 0;
        for(int i=0; i<intervals.length; i++){
            if(!merged[i]){
                mergeIntervals[idx++] = intervals[i];
            }
        }
        return mergeIntervals;
    }

    private boolean between(int left, int right, int checkNum){
        return left <= checkNum && right >= checkNum;
    }

}
