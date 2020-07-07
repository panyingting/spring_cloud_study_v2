package com.my.study.leetcode.arithmetic.page2;

import java.util.Arrays;

public class SmallestRangeII {

    public int smallestRangeII(int[] A, int K) {
        Arrays.sort(A);
        int n = A.length;
        int res = A[n-1] - A[0];
        for (int i=1;i<n;i++) {
            int min = Math.min(A[0]+K,A[i]-K);
            int max = Math.max(A[n-1]-K,A[i-1]+K);
            res = Math.min(max-min,res);
        }
        return res;
    }
}
