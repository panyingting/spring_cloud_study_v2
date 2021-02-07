package com.pyt.grpc.test;

import com.alibaba.fastjson.JSON;
import com.pyt.grpc.cliemt.JavaGrpcClient;
import java_test.Request;
import java_test.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : Pan Yingting
 * @date : 2021/1/10 5:42 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GrpcClientJavaTestApplicationTests {

    @Autowired
    private JavaGrpcClient javaGrpcClient;

    @Test
    public void testServiceBlockingStub() {
        Result result = javaGrpcClient.testServiceBlockingStub();

        System.out.println("result:" + result);
    }

    @Test
    public void methodRequestStream() {
        javaGrpcClient.methodRequestStream();
    }

    @Test
    public void methodResultStream() {
        javaGrpcClient.methodResultStream();
    }

    @Test
    public void methodDoubleStream() {
        javaGrpcClient.methodDoubleStream();
    }

}
