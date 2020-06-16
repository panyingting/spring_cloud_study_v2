package com.my.study.leetcode.arithmetic;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.*;

public class ThreeSum2 {

    @Test
    public void test(){
        int nums[] = {1,2,-2,-1};

        List<List<Integer>> ls = threeSum(nums);

        System.out.println(JSON.toJSONString(ls));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length <3)
            return Collections.EMPTY_LIST;
        Arrays.sort(nums);
        int partitionIdx = getPartitionIdx(nums, 0 ,nums.length-1);

        int numI ;
        int numJ = 0;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        out:for(int i=0; i<=nums.length-3; i++){
            numI = nums[i];
            mid: for(int j= i+1; j<=nums.length-2; j++){

                if( j != i+1 && numJ == nums[j]){
                    continue ;
                }
                numJ = nums[j];
                int sum = numI + numJ;
                if(sum > 0)
                    continue out;

                int begin = partitionIdx > j ? partitionIdx : j+1;
                int end = nums.length-1;
                while (begin <= end){
                    if(sum == 0){
                        if(nums[begin] == 0){
                            filNums(numI, numJ, nums[begin], map);
                            continue mid;
                        }else {
                            continue out;
                        }
                    }else{
                        int gap = end - begin;
                        int midNum = nums[begin+gap/2];
                        if(midNum == -sum){
                            filNums(numI, numJ, midNum, map);
                            break ;
                        }
                        else if(midNum < -sum){
                                begin = begin+gap/2+1;
                        }else {
                            end = begin + gap / 2 - 1;
                        }
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

    private void filNums(int numI, int numJ, int numK, Map<Integer, Map<Integer, Integer>> map){
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
    }

    private int getPartitionIdx(int[] nums, int begin, int end){
        int gap = end - begin;
        if(gap <= 1){
            if(nums[end] > 0)
                return begin;
            return end;
        }
        int mid = nums[begin + gap/2];
        if (mid > 0){
            return getPartitionIdx(nums, begin, begin+gap/2-1);
        }else {
            return getPartitionIdx(nums, begin+gap/2, end);
        }
    }
}
