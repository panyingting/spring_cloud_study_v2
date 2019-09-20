package com.common.study.database.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@MapperScan("com.common.study.database.mybatis.mapper")
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@ImportResource({"classpath:/mybatis/mybatis-config.xml", "classpath:/aop.xml"})
public class MapperApplication {
}
