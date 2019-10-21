package com.common.server.istudy.aop.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkAopInvocationHandler implements InvocationHandler {
    private final Object target;
    public JdkAopInvocationHandler(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("前置处理");
        Object obj = method.invoke(target, args);
        System.out.println("后置处理");
        return obj;
    }
}
