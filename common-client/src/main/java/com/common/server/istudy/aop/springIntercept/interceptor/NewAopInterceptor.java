package com.common.server.istudy.aop.springIntercept.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class NewAopInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String info = methodInvocation.getMethod().getDeclaringClass()+ "." +
                methodInvocation.getMethod().getName() + "()";

        System.out.println("new NewAopInterceptor before");

        try{
            Object result = methodInvocation.proceed();
            return result;
        }
        finally{
            System.out.println("new NewAopInterceptor after");
        }
    }
}