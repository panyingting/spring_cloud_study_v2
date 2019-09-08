package com.common.server.istudy.intercept.conf;

import com.common.server.istudy.intercept.MethodInterceptorInterface;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.common.server.istudy.intercept")
public class MethodInterceptorConf {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MethodInterceptorConf.class);
        MethodInterceptorInterface methodInterceptorInterface = context.getBean("methodIntercepterInterfaceImpl", MethodInterceptorInterface.class);

        System.out.println();
        methodInterceptorInterface.hello();

        MethodInterceptorInterface aopObj = context.getBean("aopObj", MethodInterceptorInterface.class);

        System.out.println();
        aopObj.hello();
//        context
    }

    @Bean("aopObj")
    public ProxyFactoryBean getProxyFactoryBean() throws ClassNotFoundException {

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setProxyInterfaces( new Class<?>[]{MethodInterceptorInterface.class});
        proxyFactoryBean.setInterceptorNames("springAopInterceptor", "newAopInterceptor","methodIntercepterInterfaceImpl2");

        return proxyFactoryBean;
    }

}
