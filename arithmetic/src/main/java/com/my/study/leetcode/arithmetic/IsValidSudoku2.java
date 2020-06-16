package com.my.study.leetcode.arithmetic;

import java.util.ArrayList;
import java.util.List;

public class IsValidSudoku2 {

    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][10];
        boolean[][] col = new boolean[9][10];
        boolean[][][] square = new boolean[3][3][10];


        for(int i=0; i< board.length; i++){
            for (int j=0; j< board[i].length; j++){
                char numCh = board[i][j];
                if(numCh == '.') continue;
                int num = numCh - '0';
                int x = j/3;
                int y = i/3;
                if(row[i][num] )
                    return false;
                if(col[j][num])
                    return false;
                if(square[x][y][num])
                    return false;
                row[i][num] = true;
                col[j][num] = true;
                square[x][y][num] = true;
            }
        }
        return true;
    }
}
