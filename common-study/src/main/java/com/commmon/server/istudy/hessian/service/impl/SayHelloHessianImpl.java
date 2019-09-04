package com.commmon.server.istudy.hessian.service.impl;

import com.commmon.server.istudy.hessian.service.SayHelloHessian;
import org.springframework.stereotype.Service;

@Service("sayHelloHessian")
public class SayHelloHessianImpl implements SayHelloHessian {

    public String sayHello(String name){
        String greet = "hello "+name;

        System.out.println(greet);

        return greet;
    }
}
