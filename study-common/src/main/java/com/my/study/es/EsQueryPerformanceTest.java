package com.my.study.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class EsQueryPerformanceTest {


    public static void main(String[] args) {
        SpringApplication.run(EsQueryPerformanceTest.class);
    }



}
