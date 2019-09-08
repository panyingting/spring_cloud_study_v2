package com.common.server.istudy.hessian.config;

import com.common.server.istudy.hessian.service.SayHelloHessian;
import com.common.server.istudy.hessian.service.SayHelloHessian;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianServiceExporter;

@Configuration
public class MyHessianConfig {

    @Autowired
    private SayHelloHessian sayHelloHessian;

    @Bean("/sayHello")
    public HessianServiceExporter hessianServiceExporter(){
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(sayHelloHessian);
        exporter.setServiceInterface(SayHelloHessian.class);
        return exporter;
    }
}
