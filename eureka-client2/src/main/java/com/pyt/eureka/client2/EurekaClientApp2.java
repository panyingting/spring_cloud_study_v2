package com.pyt.eureka.client2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;

@EnableDiscoveryClient
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@PropertySource("classpath:9002.properties")
public class EurekaClientApp2 {

    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaClientApp2.class).web(true).run(args);
    }
}
