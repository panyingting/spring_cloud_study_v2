package com.my.study.leetcode.v2.page1;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 * @author : Pan Yingting
 * @date : 2021/7/5 8:00 上午
 */
public class LongestPalindrome {
    int len = 1;
    String max ;
    public String longestPalindrome(String s) {
        max = s.substring(0, 1);
        for (int i = 1; i < s.length(); i++) {
            int left  = i - 1;
            int right = i + 1;
            findLongestPalindrome(s, left, right);
            left = i - 1;
            right = i;
            findLongestPalindrome(s, left, right);
        }
        return max;
    }

    private void findLongestPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                int newLen = right - left - 1;
                if (newLen > len) {
                    max = s.substring(left + 1, right);
                    len = newLen;
                }
                break;
            }
            left --;
            right ++;
        }
        if (left < 0 || right == s.length()) {
            int newLen = right - left - 1 ;
            if (newLen > len) {
                max = s.substring(left + 1, right);
                len = newLen;
            }
        }
    }
}
