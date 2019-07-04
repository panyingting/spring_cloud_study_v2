package com.pyt.study.imports;


import com.pyt.study.bean.User;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@Import(Config.class)
public class AppConfig {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AppConfig.class, args);

        System.out.println("get bean: "+context.getBean(Config.class));
        System.out.println("get bean: "+context.getBean("getPseron"));
    }
}
