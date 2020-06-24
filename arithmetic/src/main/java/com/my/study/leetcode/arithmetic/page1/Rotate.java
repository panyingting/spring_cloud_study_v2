package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

public class Rotate {

    @Test
    public void test(){
        int arr[][] = {{1,2,3},{4,5,6},{7,8,9}};

        rotate(arr);
    }

    /**
     *
     * i 为层次， beginX 为起点且endX 为终点，
     * 以 point(i, beginX) 和 point(i, endX) 为起终止点的线围绕图形中心点进行旋转, 就像贪吃蛇一样，只是我们先一个点一个点进行旋转而已
     */
    public void rotate(int[][] matrix) {

        int beginX = 0;
        int endX = matrix.length-1;
        int i = 0;
        while(beginX < endX){
            for (int j=beginX; j< endX; j++){
                int[] pos = {i,j};              // 旋转点，第一个元素为二维数组第一个下标，第二个元素为二维数组第二个下标，begin 和 end 同
                int begin[] = {i, beginX};
                int end[] = {i, endX};
                doRotate(matrix, pos, begin, end, true);
            }
            beginX ++;
            endX --;
            i++;
        }
    }

    private void doRotate(int[][]matrix, int[] pos, int begin[], int end[], boolean isStart){

        int newPos[] =  getNewRotatePoint(begin, end, pos);
        int val = matrix[pos[0]][pos[1]];
        if(isStart || (begin[0] != end[0] || begin[1] > end[1])){
            int newEnd[] = getNewRotatePoint(begin, end, end);
            doRotate(matrix, newPos, end, newEnd, false);
        }
        matrix[newPos[0]][newPos[1]] = val;

    }

    /**
     * 根据 rotatePoint 点获取旋转后的新点
     */
    private int[] getNewRotatePoint(int begin[], int end[], int rotatePoint[]){
        // 横轴，也就是数组第一个下标相等
        boolean isX = begin[0] == end[0];
        int point[] = new int[2];
        if(isX){
            point[0] = end[0] + (rotatePoint[1] - begin[1]);
            point[1] = end[1];
        }else {
            point[0] = end[0];
            point[1] = end[1] - (rotatePoint[0] - begin[0]);
        }
        return point;
    }
}
