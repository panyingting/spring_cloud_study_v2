package com.common.study.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;

public class PeopleBean {

    @Autowired
    private DriverManagerDataSource dataSource;

    @Qualifier("dataSource")
    @Resource
    private DriverManagerDataSource driverManagerDataSource1;

}
