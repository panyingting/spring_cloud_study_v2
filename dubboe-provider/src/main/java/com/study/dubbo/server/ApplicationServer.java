package com.study.dubbo.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class} )
@ImportResource(locations = "/spring-dubbo.xml")
public class ApplicationServer {

    private static CountDownLatch latch = new CountDownLatch(1);
    public static void main(String[] args) {
        new SpringApplicationBuilder(
                ApplicationRun.class).run(args);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nlatched ????");

    }
}