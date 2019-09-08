package com.common.server.istudy.intercept.impl;

import com.common.server.istudy.intercept.MethodInterceptorInterface;
import com.common.server.istudy.intercept.MethodInterceptorInterface;
import org.springframework.stereotype.Component;

@Component
public class MethodIntercepterInterfaceImpl2 implements MethodInterceptorInterface {


    @Override
    public String hello() {
        System.out.println("hello Others。。。");

        return "hello others";
    }
}
