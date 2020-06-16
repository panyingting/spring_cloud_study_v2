package com.my.study.leetcode.arithmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        Map<Character, List<int[]>> map = new HashMap<>();

        for(int i=0; i< board.length; i++){
            for (int j=0; j< board[i].length; j++){
                char num = board[i][j];
                if(num == '.') continue;
                List<int[]> intList = map.get(num);
                int x = j/3;
                int y = i/3;
                if(intList != null){
                    for(int[] properties: intList){
                        if ( j == properties[0])
                            return false;
                        if ( i == properties[1])
                            return false;
                        if(properties[2] == x && properties[3] == y)
                            return false;
                    }
                }else {
                    intList = new ArrayList<>();
                    map.put(num, intList);
                }
                int[] arr = {j, i, x, y};
                intList.add(arr);
            }
        }
        return true;
    }

}
