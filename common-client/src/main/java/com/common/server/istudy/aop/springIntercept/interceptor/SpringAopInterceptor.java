package com.common.server.istudy.aop.springIntercept.interceptor;

import com.common.server.istudy.aop.springIntercept.service.impl.TargetObjectServiceImpl1;
import com.common.server.istudy.aop.springIntercept.service.impl.TargetObjectServiceImpl2;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class SpringAopInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        String info = methodInvocation.getMethod().getDeclaringClass()+ "." +
                methodInvocation.getMethod().getName() + "()";

        System.out.println(info);

        try{
            Object result = methodInvocation.proceed();
            methodInvocation.getMethod().invoke(new TargetObjectServiceImpl2(), methodInvocation.getArguments());
            methodInvocation.getMethod().invoke(new TargetObjectServiceImpl1(), methodInvocation.getArguments());
            return result;
        }
        finally{
            System.out.println(info);
        }
    }
}
