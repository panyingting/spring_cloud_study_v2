package com.my.study.main;

import java.util.Date;

public class Test {

    public static void main(String[] args) {
        System.out.println(new Date(1562731510495L));
        System.out.println(new Date(1562731381829L));

        System.out.println(String.format("当前您商品库存不足，您还可购买%ss个，请重新选择后再下单", "ww"));
    }
}
