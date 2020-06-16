package com.my.study.leetcode.arithmetic;

import org.junit.Test;

import java.util.*;


/**
 * "wordgoodgoodgoodbestword"
 * []
 */
public class FindSubstring {


    @Test
    public void test(){
        String str = "wordgood_goodgoodbestword";
        String [] strArr = {"word","good","best","good"};

        System.out.println(findSubstring(str, strArr));
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> list = new ArrayList<>();
        if(words.length == 0 || s.length() == 0 || s.length() < words.length* words[0].length()){
            return list;
        }

        // 计算各个单词的次数，
        Map<String, Integer> originMap = new HashMap<>();
        for(String str: words){
            int num = originMap.computeIfAbsent( str, k ->0);
            originMap.put(str, num+1);
        }

        // 用一个临时Map来处理出现的单词相关逻辑，用完之后再重新初始化，减少内存开销
        Map<String, Integer> tmpMap = new HashMap<>(originMap);

        Integer count;  // 对应单词在map中的计数器

        String word;
        int perLen = words[0].length();
        int allWordsLen = perLen * words.length;
        for (int idx=0; idx<=s.length() - allWordsLen; idx++){

            // 查找第一个匹配的单词
            word = s.substring( idx, perLen+idx);
            count = tmpMap.get(word);
            if (count != null){

                /*
                 *找到匹配的单词，计数器就减一， 如果减到负数就说明不匹配，如果能减到 endPos 说明刚好完全匹配
                 */
                tmpMap.put(word, count-1);
                int pos = idx+perLen;
                int endPos = idx+allWordsLen;
                while (pos < endPos){
                    word = s.substring( pos, pos+perLen);
                    count = tmpMap.get(word);
                    if(count == null || count < 1){
                        break;
                    }
                    tmpMap.put(word, count-1);
                    pos += perLen;
                }
                if(pos == endPos){
                    list.add(idx);
                }

                // 初始化Map信息
                tmpMap.putAll(originMap);
            }
        }
        return list;
    }
}
