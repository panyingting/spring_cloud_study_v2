package com.common.server.istudy.aop.springAspect;

import com.common.server.istudy.aop.springAspect.annotation.MyAspectAnnotation;
import org.springframework.stereotype.Service;

@Service
@MyAspectAnnotation
public class AspectAj {

    @MyAspectAnnotation
    public void process(int i){
        System.out.println("Hello Process.........");
    }

    public void enhanceMethod2(int i){

        System.out.println("enhanceMethod2 i"+ i);
    }
}
