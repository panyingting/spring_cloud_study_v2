package com.my.study.feign.service;

import com.my.study.beans.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.my.study.feign.service.impl.FeignFallBack;

@FeignClient(value = "eureka-client2",fallback = FeignFallBack.class)
public interface FeignService {

    //服务中方法的映射路径
    @RequestMapping("/hello")
    String hello();

    @RequestMapping(value = "/hellol", method= RequestMethod.GET)
    String hello(@RequestParam("name") String name) ;

    @RequestMapping(value = "/hello2", method= RequestMethod.GET)
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/hello3", method= RequestMethod.POST)
    String hello(@RequestBody User user);

}
