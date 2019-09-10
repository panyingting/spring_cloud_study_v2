package com.common.server.istudy.aop.springIntercept.config;

import com.common.server.istudy.aop.springIntercept.service.TargetObjectService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.common.server.istudy.aop.springIntercept")
public class MethodInterceptorConfig {


    @Bean("aopObj")
    public ProxyFactoryBean getProxyFactoryBean() throws ClassNotFoundException {

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setProxyInterfaces( new Class<?>[]{TargetObjectService.class});

        // 如果 没有配置 targetName 属性，则 ProxyFactoryBean 会获取最后一个 interceptorName 是否不是 Advice 和 advisor 的实例，如果不是则作为 targetName, 参见源码：org.springframework.aop.framework.ProxyFactoryBean.checkInterceptorNames
        proxyFactoryBean.setInterceptorNames("springAopInterceptor", "newAopInterceptor", "targetObjectServiceImpl2");
//        proxyFactoryBean.setTargetName("targetObjectServiceImpl2");
        return proxyFactoryBean;
    }

}
