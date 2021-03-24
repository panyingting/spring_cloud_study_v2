package common.study.main;

import java.util.*;

public class MainDemo {

    public static void main(String[] args) {

        // Map [1e] [2->value] [3] [6]
        // Map:  1 - 100
        // Map:  key - value
        // Map:  key - value

        Map<Integer, Object> map = new HashMap<>();
        map.put(3, 100);
        map.put(19, 200);
        map.put(35, 300);

        int[] arr = new int[1];
        arr[0] = 1;


        List<Integer> list = new ArrayList<>();
        list.add(1);

        int i = 1;
        Integer integer = i;


        List<Long> list2 = new ArrayList<>();


        // set

        Set<Object> set = new HashSet<>();
        set.add(1);

        System.out.println(map.get(1));


    }
}
