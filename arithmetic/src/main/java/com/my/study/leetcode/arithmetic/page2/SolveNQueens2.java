package com.my.study.leetcode.arithmetic.page2;

import java.util.ArrayList;
import java.util.List;

/**
 * N皇后优化版，其中可以用 boolean数组
 */
public class SolveNQueens2 {

    private List<List<String>> queensList = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        boolean[] col = new boolean[n];
        boolean[] left = new boolean[2*n];
        boolean[] right = new boolean[2*n];
        recursionBackTracking(new ArrayList<>(), n, col, left, right);

        return queensList;
    }

    private void recursionBackTracking(List<int[]> queensSite, int n, boolean[] col, boolean[] left, boolean[] right){
        if(queensSite.size() == n){
            fillQueens(queensSite);
            return;
        }
        int size = queensSite.size();
        for(int j=0; j<n; j++){
            if (col[j] || left[size + j] || right[n-j+size-1]) {
                continue;
            }
            col[j]  = left[size + j] = right[n-j+size-1] = true;
            int newSite[] = {size, j};
            queensSite.add(newSite);

            recursionBackTracking(queensSite, n, col, left, right);
            col[j]  = left[size + j] = right[n-j+size-1] = false;
            queensSite.remove(size);
        }
    }

    private void fillQueens(List<int[]> queensSite){
        List<String> stringList = new ArrayList<>(queensSite.size());
        for(int i=0; i<queensSite.size(); i++){
            StringBuilder builder = new StringBuilder(queensSite.size());
            int[] queenSite = queensSite.get(i);
            for(int j=0; j<queensSite.size(); j++) {
                builder.append( j == queenSite[1] ? "Q" : ".");
            }
            stringList.add(builder.toString());
        }
        queensList.add(stringList);
    }

}
