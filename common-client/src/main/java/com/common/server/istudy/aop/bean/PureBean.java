package com.common.server.istudy.aop.bean;

import com.common.server.istudy.aop.springAspect.annotation.MyAspectAnnotation;
import org.springframework.stereotype.Service;

@Service
public class PureBean {

    public PureBean(){
        System.out.println(" PureBean constructor init");
    }

    public void f(){
        System.out.println(" PureBean f()。。。。。。");
    }
}
