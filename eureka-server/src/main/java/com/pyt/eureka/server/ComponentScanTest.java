package com.pyt.eureka.server;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component("componentScanTest")
public class ComponentScanTest implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("同级目录。。。。");
    }
}
