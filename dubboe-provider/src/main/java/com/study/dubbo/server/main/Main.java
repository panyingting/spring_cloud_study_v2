package com.study.dubbo.server.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public  static  void  main(String[]  args)  {    
                String  xml  =  "classpath:test.xml";
                ApplicationContext context  =  new ClassPathXmlApplicationContext(new  String[]  {  xml  });
                System.out.println(context.getBean("testCustom"));    
        }    
    
}
