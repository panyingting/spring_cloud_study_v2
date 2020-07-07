package com.my.study.leetcode.arithmetic.page2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {

    @Test
    public void test(){

        int[][] arr = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};

        System.out.println(spiralOrder(arr));
    }
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> list = new ArrayList<>();
        if(matrix.length == 0 || matrix[0].length == 0) return list;

        int beginX = 0;
        int endX = matrix[0].length -1;
        int beginY = 0;
        int endY = matrix.length-1;
        while (beginX <= endX && beginY <= endY){
            doSpiralOrder(matrix, list, beginX, endX, beginY, endY, true);
            beginX ++; endX--;
            beginY ++; endY--;
        }
        return list;
    }

    private void doSpiralOrder(int[][] matrix, List<Integer> list, int beginX,  int endX, int beginY, int endY, boolean flag){

        if(beginX == endX){
            for(int y=beginY; y <= endY; y++){
                list.add(matrix[y][beginX]);
            }
            return;
        }
        if(beginY == endY){
            for(int x=beginX; x <= endX; x++){
                list.add(matrix[beginY][x]);
            }
            return;
        }

        int added = flag ? 1 : -1;
        for(int i = beginX; i != endX; i += added){
            list.add(matrix[beginY][i]);
        }
        for(int i=beginY; i != endY; i += added){
            list.add(matrix[i][endX]);
        }
        if(flag && beginX < endX && beginY < endY){
            doSpiralOrder(matrix, list, endX, beginX, endY, beginY, false);
        }
    }
}
