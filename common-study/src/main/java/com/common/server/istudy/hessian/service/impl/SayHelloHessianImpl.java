package com.common.server.istudy.hessian.service.impl;

import com.common.server.istudy.hessian.service.SayHelloHessian;
import org.springframework.stereotype.Service;

@Service("sayHelloHessian")
public class SayHelloHessianImpl implements SayHelloHessian {

    public String sayHello(String name){
        String greet = "hello "+name;

        System.out.println(greet);

        return greet;
    }

    @Override
    public String sayHello(int age) {
        String gree =  " hell for age:"+age;
        System.out.println(gree);
        return gree;

    }
}
