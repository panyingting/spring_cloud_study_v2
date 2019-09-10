package com.common.server.istudy.aop.springIntercept;

import com.common.server.istudy.aop.springIntercept.interceptor.NewAopInterceptor;
import com.common.server.istudy.aop.springIntercept.interceptor.SpringAopInterceptor;
import com.common.server.istudy.aop.springIntercept.service.TargetObjectService;
import com.common.server.istudy.aop.springIntercept.service.impl.TargetObjectServiceImpl2;
import org.springframework.aop.framework.ProxyFactory;

public class ProxyFactoryMainTest {

    public static void main(String[] args) {

        System.out.println("\n ====proxyByJdk====");
        proxyByJdk();
        System.out.println("\n ====proxyByJdk end ====");
    }

    private static void proxyByJdk(){
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(TargetObjectService.class);
        proxyFactory.setTarget(new TargetObjectServiceImpl2());
        proxyFactory.addAdvice(new NewAopInterceptor());
        proxyFactory.addAdvice(new SpringAopInterceptor());

        TargetObjectService targetObjectService = (TargetObjectService)proxyFactory.getProxy();

        targetObjectService.hello();
    }
}
