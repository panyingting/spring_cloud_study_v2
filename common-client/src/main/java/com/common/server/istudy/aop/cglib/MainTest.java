package com.common.server.istudy.aop.cglib;

import com.common.server.istudy.aop.cglib.intercepter.MyMethodInterceptor;
import com.common.server.istudy.aop.cglib.intercepter.SecondInterceptor;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

public class MainTest {

    public static void main(String[] args) {

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "target/cglib");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallbacks(new MethodInterceptor[]{new MyMethodInterceptor()});
        PersonService object = (PersonService)enhancer.create();
        object.sayHello();
        object.sayHello();
        object.sayOthers(" Messi");

        System.out.println(object.toString());
        System.out.println(object.hashCode());
    }

}
