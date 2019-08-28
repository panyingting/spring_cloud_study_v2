package com.study.dubbo.client.demo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.study.dubbo.server.demo.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Client {

    @Reference(version = "1.0.0",timeout = 300)
    private TestService testService;

    @RequestMapping("/invoke")
    public void invoke(){
        System.out.println("调用 invoke()。。。。");
        testService.testDubbo();
    }
}
