package com.pyt.eureka.client1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
 public class EurekaClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(
                EurekaClientApplication.class)
                .web(true).run(args);
    }
}
