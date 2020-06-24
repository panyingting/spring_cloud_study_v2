package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

import java.util.*;

public class LetterCombinationsDp {

    @Test
    public void test(){
        System.out.println(letterCombinations("23"));
    }

    Map<Character, List<Character>> map = LetterCombinations.map;

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) return new ArrayList<>();

        List<List<String>> dpList = new ArrayList<>();
        for(int i=0; i<digits.length(); i++){
            dpList.add(null);
        }

        List<Character> chList = map.get(digits.charAt( digits.length()-1));
        List<String> stringList = new ArrayList<>();
        if(chList != null){
            chList.forEach( ch ->{
                stringList.add(String.valueOf(ch));
            });
        }
        dpList.set(digits.length()-1, stringList);

        for(int i = digits.length()-2; i >=0; i--){
            List<String> subDp = dpList.get(i+1);
            chList = map.get(digits.charAt(i));
            if (chList == null){
                dpList.set(i, subDp);
            }else {
                List<String> currDp = new ArrayList<>();
                chList.forEach( ch ->{
                    if(subDp.size() > 0){
                        subDp.forEach( subStr -> currDp.add(ch + subStr));
                    }else {
                        currDp.add( String.valueOf(ch));
                    }
                });
                dpList.set(i, currDp);
            }

        }

        return dpList.get(0);
    }
}
