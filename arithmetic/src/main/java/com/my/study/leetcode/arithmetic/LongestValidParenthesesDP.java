package com.my.study.leetcode.arithmetic;

import java.util.HashMap;
import java.util.Map;

public class LongestValidParenthesesDP {


    /**
     * 动态规划
     */
    public int longestValidParentheses(String s) {
        if(s == null || s.length() < 2) return 0;

        int[] dp = new int[s.length()];

        int longestLen = 0;
        for(int i=1; i< s.length(); i++){

            // 如果找到左括号则先忽略，等找到对应的右括号了再进行处理
            if(s.charAt(i) == ')'){

                // 刚好跟紧挨前面的是一对括号
                if(s.charAt(i-1) == '('){
                    dp[i] = i > 2 ? dp[i-2] + 2 : 2;
                }

                // 前面的括号也是右括号
                else {

                    // dp[i-1] > 0 是需要紧挨的前一个字符串是有效的，不然就不用计算了
                    // dp[i-1] < i 是避免刚好前面所有字符串刚好是完整的有效字符串（因为是的话，当前的右括号肯定是多余的），
                    if(dp[i-1] > 0 && dp[i-1] < i && s.charAt( i - dp[i-1]-1) == '('){
                        dp[i] = dp[i-1] + 2;

                        // 如果计算完之后，前面还剩余至少两个字符，且 dp[ i - dp[i-1] - 2] 不为0 时，继续累加
                        int preLen;
                        if(i-dp[i-1] - 2 > 0 && (preLen = dp[ i - dp[i-1] - 2]) > 0 ){
                            dp[i] += preLen;
                        }
                    }
                }

                if(dp[i] > longestLen){
                    longestLen = dp[i];
                }
            }
        }
        return longestLen;
    }
}
