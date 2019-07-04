package com.pyt.eureka.server.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component("componentScanTest2")
public class ComponentScanTest implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("二级目录。。。。");
    }
}
