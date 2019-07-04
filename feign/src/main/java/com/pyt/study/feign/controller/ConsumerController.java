package com.pyt.study.feign.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.pyt.study.bean.User;
import com.pyt.study.feign.service.FeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);


    @Autowired
    FeignService feignService;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/consumer")
    public String helloConsumer(){
        return feignService.hello();
    }

    @RequestMapping("/consumer2")
    public String helloConsumer2(){
        String r1 = feignService.hello("hjc");
        String r2 = feignService.hello("hjc", 23).toString();
        String r3 = feignService.hello(new User("hjc", 23));
        return r1 + "-----" + r2 + "----" + r3;
    }

    @GetMapping("/ribbon/test")
    public String ribbonTest(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client2");

        LOGGER.info("serviceInstance serviceId:{} IP:{}, port:{} uri:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort(), serviceInstance.getUri());
        return serviceInstance.toString();
    }


    @GetMapping("/ribbon/restTemplate")
    public String restTemplateBalance(){
        User user = restTemplate.getForObject("http://eureka-client2/hello2?name={?}&age={?}", User.class, "name", "34");

        return user.toString();
    }

    @GetMapping("/ribbon/restTemplate2")
    public String restTemplateBalance2(){
        return restTemplate.getForObject("http://eureka-client2/hellol?name=12312", String.class);
    }

}
