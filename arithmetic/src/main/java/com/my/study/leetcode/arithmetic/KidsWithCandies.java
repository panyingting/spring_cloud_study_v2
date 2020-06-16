package com.my.study.leetcode.arithmetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KidsWithCandies {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        int max = candies[0];

        for(int i=1; i<candies.length; i++){
            if(max < candies[i])
                max = candies[i];
        }

        List<Boolean> list = new ArrayList<>(candies.length);
        int less = max-extraCandies;
        if(less <=0){
            Collections.fill(list, Boolean.TRUE);
            return list;
        }
        for (int i=0; i<candies.length; i++){
            list.add( candies[i] >= less);
        }

        return list;
    }
}
