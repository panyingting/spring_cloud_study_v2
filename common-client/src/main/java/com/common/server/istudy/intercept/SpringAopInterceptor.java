package com.common.server.istudy.intercept;

import com.common.server.istudy.intercept.impl.MethodIntercepterInterfaceImpl;
import com.common.server.istudy.intercept.impl.MethodIntercepterInterfaceImpl2;
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
            methodInvocation.getMethod().invoke(new MethodIntercepterInterfaceImpl2(), methodInvocation.getArguments());
            methodInvocation.getMethod().invoke(new MethodIntercepterInterfaceImpl(), methodInvocation.getArguments());
            return result;
        }
        finally{
            System.out.println(info);
        }
    }
}
