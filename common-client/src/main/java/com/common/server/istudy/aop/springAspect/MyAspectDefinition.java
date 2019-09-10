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
    public void aspectTest(int i){
        System.out.println("---------  @Pointcut(\"@annotation(com.common.server.istudy.aop.springAspect.annotation.MyAspectAnnotation )&& args(i)\")");
    }

    @Around("aspectTest(i)")
    public Object around(ProceedingJoinPoint joinPoint, int i) throws Throwable {
        System.out.println("Before Proceed。。。。。。。"+ i);
        Object obj = joinPoint.proceed();
        joinPoint.getArgs();
        System.out.println("After Proceed。。。。。。。");
        return new Object();
    }

    @Before("aspectTest(int)")
    public void doBefore(JoinPoint jp){
        System.out.println("Do before invoke Method.");
        Object[] arg = jp.getArgs();
        System.out.println("args。。。。。。。。。。："+arg);
    }


    @After("aspectTest(i2)")
    public void doAfter(int i2){
        System.out.println("Do after invoke method。。。。。"+i2);
    }

    @AfterReturning("aspectTest(int)")
    public void doAfterReturn(JoinPoint joinPoint){
        System.out.println("Do after return .....");
    }

    @Around("within( @com.common.server.istudy.aop.springAspect.annotation.MyAspectAnnotation *)")
    public Object withinProcess0(ProceedingJoinPoint process) throws Throwable {

        System.out.println("within before");
        Object obj = process.proceed();
        System.out.println("within after");
        return obj;
    }
    @Around("@within( com.common.server.istudy.aop.springAspect.annotation.MyAspectAnnotation )")
    public Object withinProcess1(ProceedingJoinPoint process) throws Throwable {

        System.out.println("@within before");
        Object obj = process.proceed();
        System.out.println("@within after");
        return obj;
    }
    @Around("@target(com.common.server.istudy.aop.springAspect.annotation.MyAspectAnnotation )")
    public Object withinProcess2(ProceedingJoinPoint process) throws Throwable {

        System.out.println("@target before");
        Object obj = process.proceed();
        System.out.println("@target after");
        return obj;
    }

    @Before("execution(* com.common.server.istudy.aop.springAspect.AspectAj.enhanceMethod2(..)) && args(i))")
    public void doBefore2(int i){
        System.out.println("Do before invoke Method."+i);
    }

}
