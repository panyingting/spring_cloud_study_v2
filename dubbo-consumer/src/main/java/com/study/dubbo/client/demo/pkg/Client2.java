package com.study.dubbo.client.demo.pkg;

import com.alibaba.dubbo.config.annotation.Reference;
import com.study.dubbo.server.demo.TestService;
import org.springframework.web.bind.annotation.RequestMapping;

public class Client2 {

    @Reference(version = "1.0.0",timeout = 300)
    private TestService testService;

    @RequestMapping("/invoke")
    public void invoke(){
        System.out.println("调用 invoke2()。。。。");
        testService.testDubbo();
    }

}
