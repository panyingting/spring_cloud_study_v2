package com.my.study.leetcode.arithmetic;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class IntToRoman {

    @Test
    public void test(){

        System.out.println(intToRoman(9));
    }

    Map<Integer, String> mapOne = new HashMap<Integer, String>(){{
        put(10, "I");
        put(100, "X");
        put(1000, "C");
        put(10000, "M");
    }};

    Map<Integer, String> mapFour = new HashMap<Integer, String>(){{
        put(10, "IV");
        put(100, "XL");
        put(1000, "CD");
    }};

    Map<Integer, String> mapFive = new HashMap<Integer, String>(){{
        put(10, "V");
        put(100, "L");
        put(1000, "D");
    }};
    Map<Integer, String> mapNine = new HashMap<Integer, String>(){{
        put(10, "IX");
        put(100, "XC");
        put(1000, "CM");
    }};

    public String intToRoman(int num) {
        return getPerRomanNum(num, num%10, 10);
    }
    private String getPerRomanNum(int num, int remind, int divide){

        String str ;
        if(remind < 4){
            StringBuilder builder = new StringBuilder(3);
            str = mapOne.get(divide);
            for(int i=1; i<= remind; i++){
                builder.append(str);
            }
            str = builder.toString();
        }
        else if(remind == 4){
            str = mapFour.get(divide);
        }else if(remind < 9){
            StringBuilder builder = new StringBuilder(4);

            builder.append(mapFive.get(divide));
            str = mapOne.get(divide);
            for(int i=6; i<= remind; i++){
                builder.append(str);
            }
            str = builder.toString();
        }else {
            str = mapNine.get(divide);
        }
        num = num/10;
        if(num > 0){
            return getPerRomanNum(num,  num%10,divide*10)+ str;
        }
        return str;
    }
}
