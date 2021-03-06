package com.study.dubbo.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class} )
//@ImportResource(locations = "test.xml")
public class ApplicationRun {

    public static void main(String[] args) {
        new SpringApplicationBuilder(
                ApplicationRun.class).run(args);
    }
}
