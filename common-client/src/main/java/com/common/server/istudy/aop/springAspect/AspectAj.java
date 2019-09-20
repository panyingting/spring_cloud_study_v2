package com.common.server.istudy.aop.springAspect;

import com.common.server.istudy.aop.springAspect.annotation.MyAspectAnnotation;
import org.springframework.stereotype.Service;

@Service
public class AspectAj {

    @MyAspectAnnotation
    public void process(int i){
        System.out.println("Hello Process......... this:"+this.getClass());
    }

    public void enhanceMethod2(int i){

        System.out.println("enhanceMethod2 i"+ i);

        System.out.println("=======INNER=====");
        this.process(10000000);
        System.out.println("=======INNER====");
    }
}
