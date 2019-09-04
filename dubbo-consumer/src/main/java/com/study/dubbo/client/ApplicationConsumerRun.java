package com.study.dubbo.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ApplicationConsumerRun {

    public static void main(String[] args) {
        new SpringApplicationBuilder( ApplicationConsumerRun.class).run(args);
    }
}