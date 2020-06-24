package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

import java.util.Collections;
import java.util.Map;

public class DecodeString {


    @Test
    public void test(){

        System.out.println(decodeString("3[a2[c]]"));

    }

    public String decodeString(String s) {

        Map<String, Integer> map = decodeStr(s, 0);

        return map.keySet().iterator().next();

    }

    /**
     *  通过递归，检查子串中的信息， map : key 为递归中处理完的字符串，value 为递归中处理到的下标的下一个下标
     */
    private Map<String, Integer> decodeStr(String s, int start){

        if(s.length() == start){
            return Collections.singletonMap("", start);
        }

        int left = s.indexOf('[', start);
        int right = s.indexOf(']', start);

        // 没有右括号，
        if(right == -1){
            return Collections.singletonMap(s.substring(start), s.length());
        }

        // 没有左括号了或者左括号在右括号后边了
        if(left == -1 || left > right){
            return Collections.singletonMap(s.substring(start, right), right+1);
        }

        StringBuilder build = new StringBuilder();
        while (left < right){
            StringBuilder numStrBuild = new StringBuilder();
            for(int i=start; i<left; i++){
                char ch = s.charAt(i);
                if( ch < '0' || ch > '9'){
                    build.append(ch);
                }else {
                    numStrBuild.append(ch);
                }
            }
            int num = Integer.parseInt( numStrBuild.toString());

            Map<String, Integer> map = decodeStr(s, left+1);
            String innerStr = map.keySet().iterator().next();
            int nextIdx = map.values().iterator().next();

            while (num > 0){
                build.append(innerStr);
                num--;
            }
            left = s.indexOf('[', nextIdx);
            right = s.indexOf(']', nextIdx);
            // 没有左括号了或者左括号在右括号后边了
            if(right == -1){
                build.append(s.substring(nextIdx));
                return Collections.singletonMap(build.toString(), s.length());
            }
            if( right < left || left ==-1){
                build.append(s, nextIdx, right);
                return Collections.singletonMap(build.toString(), right+1);
            }
            start = nextIdx;
        }
        throw new RuntimeException("出错了");
    }


}
