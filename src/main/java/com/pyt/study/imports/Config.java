package com.pyt.study.imports;

import com.pyt.study.bean.Person;
import org.springframework.context.annotation.Bean;

public class Config {

    @Bean
    public Person getPseron(){
        return new Person();
    }
}
