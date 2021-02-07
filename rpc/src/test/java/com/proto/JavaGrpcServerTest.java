package com.proto;

import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java_test.Request;
import java_test.Result;
import java_test.TestServiceGrpc;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author : Pan Yingting
 * @date : 2021/1/10 5:33 下午
 */
//@Component
public class JavaGrpcServerTest extends TestServiceGrpc.TestServiceImplBase implements InitializingBean
{
    @Override
    public void method(Request request, StreamObserver<Result> responseObserver) {
        Result result = Result.newBuilder().setResult1("result1").setResult2("result2").build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ServerBuilder.forPort(2)
                .addService(new JavaGrpcServerTest())
                .build()
                .start();
    }
}