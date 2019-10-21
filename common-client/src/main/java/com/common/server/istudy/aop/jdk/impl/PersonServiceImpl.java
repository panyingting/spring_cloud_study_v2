package com.common.server.istudy.aop.jdk.impl;

import com.common.server.istudy.aop.jdk.JdkAopInvocationHandler;
import com.common.server.istudy.aop.jdk.PersonService;

import java.lang.reflect.Proxy;

public class PersonServiceImpl implements PersonService {
    @Override
    public void say(String words) {

        System.out.println("hello， I say : "+ words);
    }

    @Override
    public int getAge(String ageStr) {
        System.out.println(" My age is:"+ageStr);
        return Integer.parseInt(ageStr);
    }

    public static void main(String[] args) {
        JdkAopInvocationHandler handler = new JdkAopInvocationHandler(new PersonServiceImpl());


        PersonService object = (PersonService)Proxy.newProxyInstance(handler.getClass().getClassLoader(), new Class[]{PersonService.class}, handler);

        object.say("你好");
        System.out.println("===========");
        int age = object.getAge("32");


    }
}
