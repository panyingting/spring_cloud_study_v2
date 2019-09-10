package com.common.server.istudy.aop.springIntercept.service.impl;

import com.common.server.istudy.aop.springIntercept.service.TargetObjectService;
import org.springframework.stereotype.Component;


@Component
public class TargetObjectServiceImpl1 implements TargetObjectService {

    @Override
    public String hello() {
        System.out.println("==== hello from TargetObjectServiceImpl1");

        return "hello";
    }
}
