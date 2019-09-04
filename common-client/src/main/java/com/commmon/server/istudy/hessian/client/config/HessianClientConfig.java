package com.commmon.server.istudy.hessian.client.config;

import com.commmon.server.istudy.hessian.service.SayHelloHessian;
import com.commmon.server.istudy.hessian.service.SayHelloHessian;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

@Configuration
public class HessianClientConfig {

    @Bean("sayHelloHessian")
    public HessianProxyFactoryBean proxyFactoryBean(){
        HessianProxyFactoryBean proxyFactoryBean = new HessianProxyFactoryBean();
        proxyFactoryBean.setServiceUrl("http://localhost:8080/sayHello");
        proxyFactoryBean.setServiceInterface(SayHelloHessian.class);
        return proxyFactoryBean;
    }

}
