package com.common.server.istudy.aop.springAspect;

import com.common.server.istudy.aop.bean.PureBean;
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

   @Resource
   private PureBean pureBean;

   @Test
   public void testAspectAj(){

       System.out.println("\n\nClassInfo:"+ aspectAj.getClass() + "  "+pureBean.getClass());
       System.out.println("\n========== process ===============\n\n");
       aspectAj.process(12);
       System.out.println("aspectAj:"+aspectAj.getClass());


       System.out.println("\n========== toString ===============\n\n");

       System.out.println("toString:"+aspectAj.toString());

       System.out.println("\n=======Pure Bean =========");

       pureBean.f();
       System.out.println("\n========== enhanceMethod2 ===============\n\n");
       aspectAj.enhanceMethod2(13);

       System.out.println("\n========== end ===============\n\n");
   }
}
