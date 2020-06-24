package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestValidParentheses {

    @Test
    public void test(){
        System.out.println(longestValidParentheses("((()()()((()()()())"));
    }

    public int longestValidParentheses(String s) {
        int longest = 0;
        if(s == null || s.length() <2) return longest;
        int leftCount = 0;


        Map<Integer, Integer> deepCounMap = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                leftCount ++;
            }else {
                if(leftCount > 0){
                    leftCount --;
                    Integer count = deepCounMap.get(leftCount);
                    count = count == null ? 2 : count+2;

                    // 获取下一级的长度
                    Integer subCount = deepCounMap.get( leftCount+1);
                    if(subCount != null){
                        deepCounMap.remove(leftCount+1);
                        count += subCount;
                    }
                    deepCounMap.put( leftCount, count);
                }else{
                    Integer tmpLen = deepCounMap.get(0);
                    longest = tmpLen != null && tmpLen > longest ? tmpLen : longest;
                    deepCounMap.remove(0);
                }
            }
        }
        for(Map.Entry<Integer, Integer> entry: deepCounMap.entrySet()){
            if(entry.getValue() > longest) longest = entry.getValue();
        }
        return longest;
    }

}
