package com.my.study.leetcode.arithmetic.page1;

import java.util.HashMap;
import java.util.Map;

public class MaxNoRepeatCharacters {

    public static int lengthOfLongestSubstring(String s) {

        int maxLen = 0;
        int currLen;
        int beginIndex = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();
        for(int i=0; i< s.length(); i++){
            char ch = s.charAt(i);

            Integer leftIndex = charIndexMap.get(ch);

            if(leftIndex != null && leftIndex >= beginIndex){

                currLen = charIndexMap.size();
                if(currLen > maxLen)
                    maxLen = currLen;
                i = leftIndex;
                beginIndex = leftIndex + 1;
                charIndexMap.clear();
            }else {
                charIndexMap.put(ch, i);
            }
        }
        if(maxLen == 0)
            return s.length();
        if(maxLen < charIndexMap.size())
            return charIndexMap.size();
        return maxLen;
    }

    public static int lengthOfLongestSubstring2(String s) {

        int begin = 0;
        int max = 0;

        for(int i=0; i<s.length(); i++){
            int j = begin;
            for(; j<i; j ++){
                if(s.charAt(j) == s.charAt(i)){
                    begin = j+1;
                    break;
                }
            }
            if(i-begin+1 > max)
                max = i-begin+1;
        }
        return max;
    }

    public static void main(String[] args) {

        System.out.println(lengthOfLongestSubstring("qwqwrtyrirasdfghhjk"));
    }
}
