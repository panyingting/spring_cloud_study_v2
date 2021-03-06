package com.my.study.leetcode.arithmetic.page2;

import java.util.ArrayList;
import java.util.List;

public class SolveNQueens {


    private List<List<String>> queensList = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {

        recursionBackTracking(new ArrayList<>(), n);

        return queensList;
    }

    private void recursionBackTracking(List<int[]> queensSite, int n){
        if(queensSite.size() == n){
            fillQueens(queensSite);
            return;
        }
        int size = queensSite.size();
        out:for(int j=0; j<n; j++){
            for (int[] site : queensSite) {
                if (j == site[1] || (size - site[0]) == Math.abs(j - site[1])) {
                    continue out;
                }
            }
            int newSite[] = {size, j};
            queensSite.add(newSite);
            recursionBackTracking(queensSite, n);
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
