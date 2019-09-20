package com.common.study.bean;

import com.common.study.reflect.IntrospectorDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

public class UserBean {


    @Autowired( required = false)
    private DriverManagerDataSource driverManagerDataSource;

    private IntrospectorDemo introspectorDemo;

    private DriverManagerDataSource dataSource2;



    private int id;

    private int age;

    private String name;

    private boolean adult;

    private Boolean married;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public Boolean isMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

    public DriverManagerDataSource getDataSource() {
        return dataSource2;
    }

    public void setDataSource(DriverManagerDataSource dataSource2) {
        this.dataSource2 = dataSource2;
    }
}
