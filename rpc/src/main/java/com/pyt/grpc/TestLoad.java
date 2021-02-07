package com.pyt.grpc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author : Pan Yingting
 * @date : 2021/1/10 6:01 下午
 */
@Service
public class TestLoad implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("hello \n\n hello");
    }
}
