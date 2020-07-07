package com.my.study.leetcode.arithmetic.page2;

public class GenerateMatrix {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int begin = 0;
        int end = n-1;
        int num = 1;
        while (begin <= end){
            num = fillMatrix(matrix, begin, end, begin, end, true, num);
            begin ++;
            end --;
        }
        return matrix;

    }

    private int fillMatrix(int[][] matrix, int beginX, int endX, int beginY, int endY, boolean flag, int beginNum){

        if(flag){
            if(beginX == endX){
                matrix[beginY][beginX] = beginNum++;
                return beginNum;
            }
        }
        int added = flag ? 1 : -1;

        for(int i=beginX; i != endX; i+= added){
            matrix[beginY][i] = beginNum ++;
        }

        for(int j=beginY; j != endY; j += added){
            matrix[j][endX] = beginNum++;
        }

        if(flag){
            return fillMatrix(matrix, endX, beginX, endY, beginY, false, beginNum);
        }else {
            return beginNum;
        }

    }
}
