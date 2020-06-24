package com.my.study.leetcode.arithmetic.page1;

public class IsMatchCommonMatch {


    public boolean isMatch(String s, String p) {

        int allPowerful = -1;
        int backSIdx = 0;   // 此指针用来等会如果匹配失败了，可以回溯继续匹配
        int pIdx = 0;
        int sIdx = 0;
        while ( sIdx < s.length()){
            if(pIdx < p.length()){
                if(s.charAt(sIdx) == p.charAt(pIdx)){
                    pIdx++;
                    sIdx++;
                    continue;
                }else if(p.charAt(pIdx) == '?'){
                    pIdx ++;
                    sIdx ++;
                    continue;
                }
                else if(p.charAt(pIdx) == '*'){ // 如果匹配到万能字符 * ，则先预留着不用（当做用来匹配空字符串）
                    allPowerful = pIdx;
                    pIdx++;
                    backSIdx = sIdx+1;
                    continue;
                }
            }

            /// 已无法匹配下去( p 可能剩余可能不剩余)
            if(allPowerful >= 0){
                pIdx = allPowerful+1;
                sIdx = backSIdx;
                backSIdx++;
                continue;
            }
            return false;
        }
        // 如果P 还有剩余，则只有在剩余字符都是 * 时返回true
        while (pIdx < p.length()){
            if(p.charAt(pIdx) != '*'){
                return false;
            }
            pIdx++;
        }
        return true;
    }

}
