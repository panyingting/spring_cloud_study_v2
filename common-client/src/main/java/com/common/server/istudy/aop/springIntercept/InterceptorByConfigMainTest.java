package com.common.server.istudy.aop.springIntercept;

import com.common.server.istudy.aop.springIntercept.config.MethodInterceptorConfig;
import com.common.server.istudy.aop.springIntercept.service.TargetObjectService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InterceptorByConfigMainTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MethodInterceptorConfig.class);
        TargetObjectService methodInterceptorInterface = context.getBean("targetObjectServiceImpl1", TargetObjectService.class);

        System.out.println("\n1231");
        methodInterceptorInterface.hello();

        System.out.println("\n1232======");
        TargetObjectService aopObj = context.getBean("aopObj", TargetObjectService.class);

        System.out.println();
        aopObj.hello();
//        context
    }
}
