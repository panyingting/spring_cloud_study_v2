package com.my.study.leetcode;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.my.study.leetcode.v2.page1.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Test;

import java.util.*;

/**
 * @author : Pan Yingting
 * @date : 2021/9/29 4:12 下午
 */
public class MainTest {


    public static void main(String[] args) {

        String[] words = " world  ".split("\\W+");
        System.out.println(words);

    }
    static class LRUCache {

        private final LRULinkedHashMap map;
        public LRUCache(int capacity) {
            map = new LRULinkedHashMap(capacity);
        }

        public int get(int key) {
            return map.get(key);
        }

        public void put(int key, int value) {
            map.put(key, value);
        }

        static class LRULinkedHashMap extends LinkedHashMap<Integer, Integer> {
            private final int maxNum;
            public LRULinkedHashMap(int capacity) {
                super(capacity, 0.75f, true);
                maxNum = capacity;
            }

            @Override
            public boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {

                return size() > maxNum;
            }
        }

    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        TreeNode p1 = root, p2 = null;

        while (p1 != null) {
            p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    res.add(p1.val);
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                }
            } else {
                res.add(p1.val);
            }
            p1 = p1.right;
        }
        return res;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class ShopPriceWrapper {

        private int count;

        private String toUid;

        private String toUsername;

        private int discountPrice;

        private Integer validDay = 0;

    }



    @Test
    public void test2(){

        List<String> strList = ImmutableList.of("leet","code");

        new Solution().wordBreak("leetcode", strList);
    }
    static class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {

            Map<Integer, Set<String>> map = new HashMap<>();
            int maxLen = initMap(wordDict, map);
            for (int i=0; i<maxLen; i++) {
                boolean find = containsWord(s, map, 0, i, maxLen);
                if (find) {
                    return true;
                }
            }
            return false;

        }

        private int initMap(List<String> wordDict, Map<Integer, Set<String>> map){
            int maxLen = 0;
            for (String word : wordDict) {
                Set<String> set = map.computeIfAbsent( word.length(), (key) -> new HashSet<>());
                set.add(word);
                maxLen = Math.max(maxLen, word.length());
            }
            return maxLen;
        }

        private boolean containsWord(String s, Map<Integer, Set<String>> map, int begin, int end, int maxLen){
            if (begin >= s.length() ){
                return true;
            }
            if (end >= s.length()) {
                return false;
            }
            int len = end - begin;
            Set<String> set;
            boolean find = false;
            if ((set = map.get(len)) != null) {
                String word = s.substring(begin, end + 1);
                if (set.contains(word)) {
                    find = containsWord(s, map, end + 1, end + 1, maxLen);
                }
            }
            if (!find) {
                if (len > maxLen) {
                    return false;
                }
                find = containsWord(s, map, begin, end + 1, maxLen);
            }
            return find;
        }
    }
}
