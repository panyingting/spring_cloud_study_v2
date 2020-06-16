package com;

import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

//@SpringBootApplication
public class TopCompileMain {

//    public static void main(String[] args) {
//        System.out.println("模块主类");
//    }



    @Test
    public void test(){

        System.out.println( maskMobile("1267"));

        List<Long> ls = new ArrayList<>();
        ls.stream().filter(e -> {
            System.out.println(e+ "ewe");
            return true;
        });
    }

    private static String maskMobile(String mobile) {
        if(mobile.length() >= 11){
            return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }else {
            return mobile.replaceAll("(\\d{2})\\d{3}(\\d*)", "$1****$2");
        }
    }

}
