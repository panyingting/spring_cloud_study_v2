package com.my.study.leetcode.arithmetic.page1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class combinationSumII {

    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        if(candidates.length == 0) return result;

        Arrays.sort(candidates);

        find(candidates, new ArrayList<>(), candidates.length-1, target);

        return result;

    }

    private void find(int[]candidates, List<Integer> list, int right, int diff){

        for (int i=right; i>=0; i--){
            if(candidates[i] > diff) continue;

            // 去重，同一深度层次的，不添加重复元素，有重复元素的，让 find 递归去处理
            if((i != right && candidates[i] == candidates[i+1])) continue;

            list.add(candidates[i]);
            if (candidates[i] == diff){
                List<Integer> group = new ArrayList<>(list);
                result.add(group);
            } else{
                find( candidates, list, i-1, diff - candidates[i]);
            }
            list.remove( list.size()-1);
        }
    }
}
