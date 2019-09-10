package com.common.server.istudy.aop.cglib;

import com.common.server.istudy.aop.cglib.intercepter.MyMethodInterceptor;
import org.springframework.cglib.proxy.Enhancer;

public class MainTest {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallbacks(new MyMethodInterceptor[]{new MyMethodInterceptor()});
        PersonService object = (PersonService)enhancer.create();
        object.sayHello();
        object.sayHello();
        object.sayOthers(" Messi");

        System.out.println(object.toString());
        System.out.println(object.hashCode());

    }

}
