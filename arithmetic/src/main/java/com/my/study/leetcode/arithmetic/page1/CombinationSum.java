package com.my.study.leetcode.arithmetic.page1;

import java.util.*;

public class CombinationSum {


    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        for(int i=0; i<10000000; i++){
            String uuid = UUID.randomUUID().toString();
            String code = uuid.substring(0, 4) + uuid.substring(uuid.length()-12, uuid.length());
            System.out.println(code);
            set.add(code);


        }
        System.out.println(set.size());

    }
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        if(candidates.length == 0) return result;

        Arrays.sort(candidates);

        List<Integer> list = new ArrayList<>();
        find(candidates, candidates.length-1, list, target);
        return result;
    }

    private void find(int[] candidates, int right, List<Integer> list, int diff){

        for(int j = right; j >=0; j--){
            if(candidates[j] < diff){
                list.add(candidates[j]);
                find(candidates, j, list, diff - candidates[j]);
                list.remove(list.size()-1);
            }else if( candidates[j] == diff){
                list.add(candidates[j]);
                result.add(new ArrayList<>(list));
                list.remove(list.size()-1);
            }
        }

    }
}
