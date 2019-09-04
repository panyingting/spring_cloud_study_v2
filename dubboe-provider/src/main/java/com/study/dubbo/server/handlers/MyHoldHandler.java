package com.study.dubbo.server.handlers;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class MyHoldHandler extends NamespaceHandlerSupport {

    public MyHoldHandler(){
        System.out.println("\n还好师德师风\n");
    }
    @Override
    public void init() {
        registerBeanDefinitionParser("custom", new TestCustomBeanDefinitionParser());
        System.out.println("调用 spring handlers 成功");
    }
}
