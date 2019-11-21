package com.my.study.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) throws ParseException {
        System.out.println(new Date(1562731510495L));
        System.out.println(new Date(1562731381829L));

        System.out.println(String.format("当前您商品库存不足，您还可购买%ss个，请重新选择后再下单", "ww"));


        System.out.println(1<<3);


        int i = 3;

        i += 2 * 8;
        System.out.println(i);


        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-11-12 10:00:00"));
    }
}
