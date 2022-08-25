package com.common.server.istudy.netty;

import com.alibaba.fastjson.JSON;

public class TestMain {


    public static void main(String[] args) {
        System.out.println(maskMobile("13155348521"));

        TestMain testMain = JSON.parseObject("{'num':0}", TestMain.class);
        System.out.println(testMain.num == new A().age);

    }

    private static String maskMobile(String mobile) {

        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");


    }

    private Integer num = 0;

    static class A{
        private Integer age = 0;
    }
}


