package com.my.study.imports;

import com.my.study.beans.Person;
import org.springframework.context.annotation.Bean;

public class Config {

    @Bean
    public Person getPseron(){
        return new Person();
    }
}
