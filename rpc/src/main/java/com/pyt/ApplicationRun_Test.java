package com.pyt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.concurrent.CountDownLatch;


@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class ApplicationRun_Test {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ApplicationRun_Test.class);

        CountDownLatch countDownLatch = new CountDownLatch(1);

        countDownLatch.await();
    }

}
