package com.commmon.server.istudy.netty;

public class TestMain {


    public static void main(String[] args) {
        System.out.println(maskMobile("13155348521"));
    }

    private static String maskMobile(String mobile) {

        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }
}
