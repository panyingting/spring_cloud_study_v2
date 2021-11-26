package com;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

//@SpringBootApplication
public class TopCompileMain {

    public static void main(String[] args) {
        System.out.println(maskMobile("12311231"));
    }

    ch.qos.logback.core.rolling.RollingFileAppender ss;

    private static String maskMobile(String mobile) {
        long days = TimeUnit.HOURS.toDays(99999);
        System.out.println(days);
        if(mobile.length() >= 11){
            return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }else {
            return mobile.replaceAll("(\\d{2})\\d{3}(\\d*)", "$1****$2");
        }
    }

    @Test
    public void test3(){
        List<Integer> integers = new ArrayList<>();
        integers.add(1231);
        integers.add(1231);
        Map<Object, Object> map = integers.stream().map(Object::toString).collect(Collectors.toMap(x->x, y->y));
        System.out.println(map);

        System.out.println(map);

    }

    private static final ObjectMapper JACKSON = new ObjectMapper();


    @Test
    public void test2() throws JsonProcessingException {


        List<List<String>> lists = findLadders("hit", "cog", ImmutableList.of("hot","dot","dog","lot","log","cog"));
        System.out.println(lists);

    }




    private int len = 0;
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> tmp = new ArrayList<>();
        List<List<String>> ans = new ArrayList<>();
        boolean[] flag = new boolean[wordList.size()];
        tmp.add(beginWord);
        findLadders(beginWord, endWord, wordList, tmp, ans, flag);
        return ans;
    }

    private void findLadders(String beginWord, String endWord, List<String> wordList, List<String> tmp, List<List<String>> ans, boolean[] flag){
        if (len != 0 && tmp.size() > len) {
            return;
        }
        if (beginWord.equals(endWord)) {
            if (tmp.size() < len) {
                ans.clear();
            }
            len = tmp.size();
            ans.add(new ArrayList<>(tmp));
        }

        int i=0;
        while (i < wordList.size()) {
            int idx = findWord(beginWord, wordList, i, flag);
            if (idx >= 0) {
                tmp.add(wordList.get(idx));
                flag[idx] = true;
                findLadders(wordList.get(idx), endWord, wordList, tmp, ans, flag);
                tmp.remove(tmp.size()-1);
                flag[idx] = false;
                i = idx+1;
            } else {
                i = wordList.size();
            }
        }


    }

    private int findWord(String beginWord, List<String> wordList, int begin, boolean [] flag){
        for (int i = begin; i < wordList.size(); i++) {
            if (!flag[i]) {
                if (find(wordList.get(i), beginWord)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private boolean find(String word1, String word2){
        if (word1.length() != word2.length()) {
            return false;
        }
        boolean diff = false;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                if (diff) {
                    return false;
                }
                diff = true;
            }
        }
        return diff;
    }

}
