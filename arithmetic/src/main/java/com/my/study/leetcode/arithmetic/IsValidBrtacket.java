package com.my.study.leetcode.arithmetic;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class IsValidBrtacket {

    @Test
    public void test(){
        String str = "(([]){})";

        System.out.println(isValid(str));
    }

    Map<Character, Character> chMap = new HashMap<Character, Character>(){{
        put('(',')');
        put('[',']');
        put('{','}');
    }};

    private int index = 0;

    public boolean isValid(String s) {

       while (index < s.length()){
           if(!valid(s)){
               return false;
           }
       }
       return true;
    }

    private boolean valid(String str){
        if(index == str.length()){
            return true;
        }

        Character right = chMap.get(str.charAt(index));
        if(right == null || str.length() <= ++index){
            return false;
        }

        Character ch2 = str.charAt(index);
        if(right.equals(ch2)){
            index++;
            return true;
        }else {
            while( index < str.length() && chMap.get(str.charAt(index)) != null){
                boolean boo = valid(str);
                if(!boo){
                    return false;
                }
            }
            if(index < str.length() && right.equals(str.charAt(index))){
                index++;
                return true;
            }
            return false;

        }

    }
}
