package com.my.study.leetcode.arithmetic.page1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = Arrays.stream(strs).collect(Collectors.groupingBy(s ->{
            char[] chArr = s.toCharArray();
            Arrays.sort(chArr);
            return new String(chArr);
        }));
        return new ArrayList<>(map.values());
    }


}
