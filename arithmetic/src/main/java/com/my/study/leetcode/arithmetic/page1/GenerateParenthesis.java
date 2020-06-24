package com.my.study.leetcode.arithmetic.page1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    @Test
    public void test(){
        List<String> strList = generateParenthesis(4);

        System.out.println(strList);
    }

    public List<String> generateParenthesis(int n) {
       List<String> list = new ArrayList<>();
        generate(list, "", n, n);
        return list;
    }

    /**
     * 穷举法
     */
    private void generate(List<String> list, String baseStr, int left, int right){

        if(left == 0 && right == 0){
            list.add(baseStr);
            return;
        }
        if(left > 0){
            generate(list, baseStr +"(", left-1, right);
        }

        // 任意时刻， 左边括号都不能少于右边括号，如果左边括号大于右边括号，则可以追加右括号
        if (right > left){
            generate(list, baseStr +")", left, right-1);
        }
    }


}
