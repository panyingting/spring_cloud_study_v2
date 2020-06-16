package com.my.study.leetcode.arithmetic;

import com.google.common.collect.ImmutableList;
import com.sun.org.apache.bcel.internal.generic.PUTFIELD;
import org.junit.Test;

import java.util.*;

public class LetterCombinations {

    @Test
    public void test(){
        System.out.println(letterCombinations("23"));
    }
    static Map<Character, List<Character>> map = new HashMap<Character, List<Character>>(){{
        put('2', Arrays.asList('a', 'b', 'c'));
        put('3', Arrays.asList('d', 'e', 'f'));
        put('4', Arrays.asList('g', 'h', 'i'));
        put('5', Arrays.asList('j', 'k', 'l'));
        put('6', Arrays.asList('m', 'n', 'o'));
        put('7', Arrays.asList('p', 'q', 'r', 's'));
        put('8', Arrays.asList('t', 'u', 'v'));
        put('9', Arrays.asList('w', 'x', 'y', 'z'));
    }};

    public List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) return new ArrayList<>();

        List<Character> digitList = new ArrayList<>();
        for (char ch: digits.toCharArray()){
            digitList.add(ch);
        }

        List<String> list = getStr( digitList);

        return list;

    }

    private List<String> getStr(List<Character> digitList){
        List<String> strList = new ArrayList<>();
        if(digitList.size() > 0){

            Character digit = digitList.get(0);
            List<Character> characterList = map.get(digit);
            if(characterList != null){
                digitList.remove(digit);
                List<String> subList = getStr(digitList);

                characterList.forEach( ch ->{
                    if(subList.size() > 0){
                        for(String subStr: subList){
                            strList.add(ch+subStr);
                        }
                    }else {
                        strList.add( String.valueOf(ch));
                    }
                });

            }
        }
        return strList;
    }
}
