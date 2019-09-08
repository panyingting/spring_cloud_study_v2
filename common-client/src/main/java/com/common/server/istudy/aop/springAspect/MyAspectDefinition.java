package com.common.server.istudy.aop.springAspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspectDefinition {

    public MyAspectDefinition(){
        System.out.println("\n\nMyAspectDefinition init------------ \n\n");
    }

    @Pointcut("@annotation(com.common.server.istudy.aop.springAspect.annotation.MyAspectAnnotation )&& args(i)")
    public void aspectTest(int i){}

    @Around("aspectTest(int)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before Proceed。。。。。。。");
        Object obj = joinPoint.proceed();
        joinPoint.getArgs();
        System.out.println("After Proceed。。。。。。。");
        return new Object();
    }

    @Before("aspectTest(i)")
    public void doBefore(int i){
        System.out.println("Do before invoke Method."+i);
    }

    @Before("execution(* com.common.server.istudy.aop.springAspect.AspectAj.enhanceMethod2(..)) && args(i))")
    public void doBefore2(int i){
        System.out.println("Do before invoke Method."+i);
    }


    @After("aspectTest(i2)")
    public void doAfter(int i2){
        System.out.println("Do after invoke method。。。。。"+i2);
    }

    @AfterReturning("aspectTest(int)")
    public void doAfterReturn(JoinPoint joinPoint){
        System.out.println("Do after return .....");
    }

}
