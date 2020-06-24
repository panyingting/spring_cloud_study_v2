package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsLookBack {


    @Test
    public void test(){
        System.out.println(letterCombinations("23"));
    }

    public List<String> letterCombinations(String digits) {
        List<String> res=new ArrayList<>();
        if(digits.length()==0) return res;

        StringBuilder sb = new StringBuilder();
        search(digits, sb, 0, res);
        return res;
    }

    private void search(String digits, StringBuilder sb, int index, List<String> res){
        if(index == digits.length()){
            res.add(sb.toString());
            return;
        }

        String str = convert(digits.charAt(index));
        for(int i=0; i< str.length(); i++){
            sb.append(str.charAt(i));
            search(digits, sb, index+1, res);
            sb.deleteCharAt(sb.length()-1);
        }
    }

    private String convert(char c){
        if(c=='2') return "abc";
        if(c=='3') return "def";
        if(c=='4') return "ghi";
        if(c=='5') return "jkl";
        if(c=='6') return "mno";
        if(c=='7') return "pqrs";
        if(c=='8') return "tuv";
        if(c=='9') return "wxyz";
        return "";
    }

}
