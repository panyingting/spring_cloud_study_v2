package com.my.study.leetcode.arithmetic.page1;

public class IsMatchCommonMatch2 {

    public boolean isMatch(String s, String p) {

        return isMatch0(s, 0, p, 0) > 0;
    }

    private int isMatch0(String s, int sIdx, String p, int pIdx){

        if(sIdx < s.length()){
            if(pIdx >= p.length())
                return 0;
            if(s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '?')
                return isMatch0(s, sIdx+1, p, pIdx+1);
            if(p.charAt(pIdx) == '*'){

                while (pIdx < p.length()-1 && p.charAt(pIdx+1) == '*'){
                    pIdx++;
                }

                int result = isMatch0(s, sIdx, p, pIdx+1);
                while ( result == 0 && sIdx <= s.length()){
                    sIdx++;
                    result = isMatch0(s, sIdx, p, pIdx+1);
                }

                // 由于如果最后一个 * 所匹配的循环都失败的话，那上层的循环也不用循环了，所以也就是如果里层循环结束且失败，则后续的循环都可以终止了，所以得有个 -1 的失败标记着
                return result == 1 ? 1 : -1;
            }
            return 0;
        }
        while (pIdx < p.length()){
            if(p.charAt(pIdx) != '*') return 0;
            pIdx++;
        }
        return 1;
    }
}
