package com.common.server.istudy.aop.springAspect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AopApplicationTester.class)
@EnableAspectJAutoProxy
@ComponentScan("com.common.server.istudy.aop")
public class AopApplicationTester {

   @Resource
    private AspectAj aspectAj;

   @Test
   public void testAspectAj(){

       System.out.println("\n");
       aspectAj.process(12);
       System.out.println("\n");

       aspectAj.enhanceMethod2(13);

       System.out.println("\n");
   }
}
