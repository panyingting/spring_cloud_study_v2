package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

public class SolveSudoku {

    @Test
    public void test(){
        for(int i=0; i<10; i++){
            System.out.println(i / 3 * 3 );
            System.out.println(i );
        }
    }

    boolean[][] row = new boolean[9][10];
    boolean[][] col = new boolean[9][10];
    boolean[][] square = new boolean[9][10];

    public void solveSudoku(char[][] board) {

        for(int i=0; i< board.length; i++){
            for (int j=0; j<board[i].length; j++){
                char numCh = board[i][j];
                if(numCh != '.'){
                    int num = numCh - '0';
                    row[i][num] = true;
                    col[j][num] = true;
                    square[i/3 * 3 + j/3][num] = true;
                }
            }
        }
        checkAndSet(board, 0, 0);
    }

    /**
     * 设置值与标记，以及找不到值时的回溯逻辑
     */
    private boolean checkAndSet(char[][] board, int i, int j){

        while (board[i][j] != '.'){
            j++;
            if(j >=9){
                i++;
                j=0;
            }
        }
        if(i >= 9 ) return true;

        for(int num = 1; num <=9; num++){
            if(row[i][num] || col[j][num] || square[i/3 * 3 + j/3][num]) continue;
            row[i][num] = true;
            col[j][num] = true;
            square[i/3 * 3 + j/3][num] = true;
            board[i][j] = (char) (num + '0');

            // 如果成功找到，则直接返回了，否则就循环下一个数；
            boolean result = checkAndSet(board, i, j);
            if(result){
                return true;
            }
            row[i][num] = false;
            col[j][num] = false;
            square[i/3 * 3 + j/3][num] = false;
            board[i][j] = '.';
        }
        return false;

    }
}
