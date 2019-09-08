package com.common.server.istudy.intercept.impl;

import com.common.server.istudy.intercept.MethodInterceptorInterface;
import com.common.server.istudy.intercept.MethodInterceptorInterface;
import org.springframework.stereotype.Component;


@Component
public class MethodIntercepterInterfaceImpl implements MethodInterceptorInterface {

    @Override
    public String hello() {
        System.out.println("hello");

        return "hello";
    }
}
