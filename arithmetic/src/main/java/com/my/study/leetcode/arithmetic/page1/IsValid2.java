package com.my.study.leetcode.arithmetic.page1;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class IsValid2 {

    public boolean isValid(String s) {
        Map<Character, Character> chMap = new HashMap<Character, Character>(){{
            put('(',')');
            put('[',']');
            put('{','}');
        }};
        Stack<Character> stack = new Stack<>();
        for(char ch: s.toCharArray()){
            if(ch == '{' || ch == '[' || ch == '('){
                stack.push(ch);
            }else {
                if(stack.isEmpty()){
                    return false;
                }
                char ch2 = stack.pop();
                char ch3 = chMap.get(ch2);
                if(ch3 != ch){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
