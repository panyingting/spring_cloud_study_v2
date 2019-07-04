package com.pyt.eureka.client2.controller;

import com.pyt.study.bean.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("访问来1了......");
        return "hello1";
    }

    @RequestMapping("/hjcs")
    public List<String> laowangs(String ids){
        List<String> list = new ArrayList<>();
        list.add("laowang1");
        list.add("laowang2");
        list.add("laowang3");
        return list;
    }

    //新增的方法
    @RequestMapping(value = "/hellol", method= RequestMethod.GET)
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }

    @RequestMapping(value = "/hello2", method= RequestMethod.GET)
    public User hello(@RequestParam String name, @RequestParam Integer age) {
        return new User(name, age);
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public String hello (@RequestBody User user) {
        return "Hello "+ user. getName () + ", " + user. getAge ();
    }


}
