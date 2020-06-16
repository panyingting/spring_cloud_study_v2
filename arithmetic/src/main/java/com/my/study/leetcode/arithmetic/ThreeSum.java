package com.my.study.leetcode.arithmetic;


import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;

public class ThreeSum {

    @Test
    public void test(){
        int nums[] = {};

        List<List<Integer>> ls = threeSum(nums);

        System.out.println(JSON.toJSONString(ls));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length <3){
            return Collections.EMPTY_LIST;
        }

        int numI ;
        int numJ = 0;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int i=0; i<=nums.length-3; i++){
            numI = nums[i];
            mid: for(int j= i+1; j<=nums.length-2; j++){

                if( j != i+1 && numJ == nums[j]){
                    continue ;
                }
                numJ = nums[j];
                int sum = numI + numJ;
                for(int k= j+1; k<= nums.length-1; k++){
                    int numK = nums[k];
                    if(sum + numK == 0){
                        int maxNum;
                        int midNum;
                        int minNum;
                        if(numI < numJ){
                            maxNum = numJ;
                            midNum = numI;
                        }else {
                            maxNum = numI;
                            midNum = numJ;
                        }
                        if(maxNum < numK){
                            minNum = midNum;
                            midNum = maxNum;
                            maxNum = numK;
                        }else {
                            if(midNum < numK){
                                minNum = midNum;
                                midNum = numK;
                            }else {
                                minNum = numK;
                            }
                        }
                        Map<Integer, Integer> numMap = map.computeIfAbsent(maxNum, k1 -> new HashMap<>());
                        numMap.put(midNum, minNum);
                        continue mid;
                    }
                }

            }
        }
        Integer size = 0;
        for(Map<Integer,Integer> tMap: map.values()){
            size += tMap.size();
        }
        List<List<Integer>> lists = new ArrayList<>(size);

        for(Map.Entry<Integer, Map<Integer, Integer>> entry: map.entrySet()){
            int maxNum = entry.getKey();
            for(Map.Entry<Integer, Integer> innerEntry: entry.getValue().entrySet()){
                List<Integer> list = new ArrayList<Integer>(3){{
                    add(maxNum);
                    add(innerEntry.getKey());
                    add(innerEntry.getValue());
                }};
                lists.add(list);
            }
        }
        return lists;
    }
}
