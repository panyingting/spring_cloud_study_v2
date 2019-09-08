package com.common.server.istudy.aop.cglib.intercepter;

import com.common.server.istudy.aop.cglib.PersonService;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodInterceptor implements MethodInterceptor {

    /**
     * sub：cglib生成的代理对象
     * method：被代理对象方法
     * objects：方法入参
     * methodProxy: 代理方法
     */
    @Override
    public Object intercept(Object sub, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("======插入前置通知======");

//        method.invoke(sub, objects);  这么调用出现异常

        Object object = methodProxy.invokeSuper(sub, objects);
        System.out.println("======插入后者通知======");
        return object;
    }

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PersonService.class);
        enhancer.setCallbacks(new MyMethodInterceptor[]{new MyMethodInterceptor()});
        PersonService object = (PersonService)enhancer.create();
        object.sayHello();
        object.sayHello();
        object.sayOthers(" Messi");

        System.out.println(object.toString());
        System.out.println(object.hashCode());

    }

}




