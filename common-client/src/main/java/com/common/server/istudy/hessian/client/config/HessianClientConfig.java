package com.common.server.istudy.hessian.client.config;

import com.caucho.hessian.client.HessianProxyFactory;
import com.common.server.istudy.hessian.service.SayHelloHessian;
import com.common.server.istudy.hessian.service.SayHelloHessian;
import com.common.server.istudy.hessian.service.SayHelloHessian;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import java.net.MalformedURLException;

@Configuration
public class HessianClientConfig {

    @Bean("sayHelloHessian")
    public HessianProxyFactoryBean proxyFactoryBean(){
        HessianProxyFactoryBean proxyFactoryBean = new HessianProxyFactoryBean();
        proxyFactoryBean.setServiceUrl("http://localhost:8080/sayHello");
        proxyFactoryBean.setServiceInterface(SayHelloHessian.class);
        return proxyFactoryBean;
    }

    @Bean("sayHelloHessian2")
    public SayHelloHessian getHessianProxyFactory(){

        HessianProxyFactory proxyFactory = new HessianProxyFactory();
        try {
            SayHelloHessian obj = (SayHelloHessian)proxyFactory.create(SayHelloHessian.class, "http://localhost:8080/sayHello" );
            return obj;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HessianClientConfig.class);

        SayHelloHessian sayHelloHessian = (SayHelloHessian)context.getBean("sayHelloHessian");
        sayHelloHessian.sayHello("hellosdds ");

        System.out.println("123");
    }

}
