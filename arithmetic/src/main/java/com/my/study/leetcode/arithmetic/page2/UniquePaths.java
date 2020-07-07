package com.my.study.leetcode.arithmetic.page2;

public class UniquePaths {


    int count = 0;
    public int uniquePaths(int m, int n) {

        walk(0,0, m-1, n-1);
        return count;
    }

    private void walk(int x, int y, int xEnd, int yEnd){
        if (x > xEnd || y > yEnd) return;
        if (x == xEnd && y == yEnd){
            count ++;
            return;
        }

        walk(x+1, y, xEnd, yEnd);
        walk(x, y+1, xEnd, yEnd);
    }
}
