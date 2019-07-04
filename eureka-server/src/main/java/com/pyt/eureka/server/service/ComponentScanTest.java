package com.pyt.eureka.server.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component("componentScanTest1")
public class ComponentScanTest implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("一级目录。。。。");
    }
}
