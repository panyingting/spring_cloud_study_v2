package com.study.dubbo.server.demo.bean;

@MyAnnotation
public class TestBean {

    private  String  name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestBean(){
        System.out.println("TestBean 初始化成功、你、n\n\n");
    }
}
