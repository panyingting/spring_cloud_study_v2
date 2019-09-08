package com.common.server.istudy.hessian.client.controller;

import com.common.server.istudy.hessian.service.SayHelloHessian;
import com.common.server.istudy.hessian.service.SayHelloHessian;
import com.common.server.istudy.hessian.service.SayHelloHessian;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URL;

@RestController
public class HessianController {

    @Resource
    private SayHelloHessian sayHelloHessian;

    @RequestMapping("/sayHello")
    public String hello(){

        String greet = sayHelloHessian.sayHello(" Messi");

        System.out.println(greet);

        return  greet;
    }

    @GetMapping(value = "/classLoader")
    public ResponseEntity<String> getResource() {
        String resourcePath = "lisp/helloworld.lisp";
        URL systemResource = ClassLoader.getSystemResource(resourcePath);
        System.out.println(">>>" + systemResource);

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("ClassLoader.getSystemClassLoader():" + systemClassLoader.toString());
        System.out.println("systemClassLoader.getResource(resourcePath): " + systemClassLoader.getResource(resourcePath));
        ClassLoader cuurentThreadClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("cuurentThreadClassLoader.toString():" + cuurentThreadClassLoader.toString());
        System.out.println("cuurentThreadClassLoader.getResource(resourcePath):" + cuurentThreadClassLoader.getResource(resourcePath));
        ClassLoader parentClassLoader = cuurentThreadClassLoader.getParent();
        System.out.println("cuurentThreadClassLoader.getParent():" + parentClassLoader);
        ClassLoader thisClassLoader = HessianController.class.getClassLoader();
        System.out.println("HessianController.class.getClassLoader():" + thisClassLoader);
        return new ResponseEntity<String>("ok", HttpStatus.OK);
    }

}
