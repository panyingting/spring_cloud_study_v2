package com.pyt.eureka.client2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;

//@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@PropertySource("classpath:9003.properties")
 public class EurekaClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(
                EurekaClientApplication.class)
                .web(true).run(args);
    }
}
