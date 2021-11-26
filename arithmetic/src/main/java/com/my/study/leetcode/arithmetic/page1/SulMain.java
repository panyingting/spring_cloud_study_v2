package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : Pan Yingting
 * @date : 2021/5/26 1:48 下午
 */
public class SulMain {

    @Test
    public void lengthOfLongestSubstring() {
        String s = "abba";
        int left = 0;
        int right = 0;
        int maxLen = 0;
        Map<Character, Integer> map = new HashMap<>(100);
        while(right < s.length()) {
            char ch = s.charAt(right);
            Integer prev = map.put(ch, right);
            if(prev != null) {
                maxLen = Math.max(maxLen, right - left);
                System.out.println(maxLen +"  " + right+ " " +left );
                left = Math.max(prev+1, left);
            }
            right ++;
        }
        maxLen = Math.max(maxLen, right - left);
        System.out.println(maxLen +"  " + right+ " " +left );

    }
}
