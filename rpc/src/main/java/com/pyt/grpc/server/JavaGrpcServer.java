package com.pyt.grpc.server;

import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java_test.Request;
import java_test.Result;
import java_test.TestServiceGrpc;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author : Pan Yingting
 * @date : 2021/1/10 5:36 下午
 */
@Component
public class JavaGrpcServer  extends TestServiceGrpc.TestServiceImplBase implements InitializingBean
{
    @Override
    public void method(Request request, StreamObserver<Result> responseObserver) {
        Result result = Result.newBuilder().setResult1("result1").setResult2("result2").build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }


    /**
     *  rpc methodRequestStream(stream Request) returns (Result) {}
     */
    @Override
    public StreamObserver<Request> methodRequestStream(StreamObserver<Result> responseObserver) {
        return new StreamObserver<Request>() {
            @Override
            public void onNext(Request request) {
                System.out.print("methodRequestStream 收到了请求 ："+request);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                Result result = Result.newBuilder().setResult1("result1").setResult2("result2").build();
                Result result2 = Result.newBuilder().setResult1("result11").setResult2("result22").build();
                responseObserver.onNext(result2);
                responseObserver.onCompleted();
            }
        };
    }


    /**
     *  rpc methodResultStream(Request) returns (stream Result){}
     */
    @Override
    public void methodResultStream(Request request, StreamObserver<Result> responseObserver) {
        System.out.print("methodResultStream 收到了请求 ："+request);
        Result result = Result.newBuilder().setResult1("result1").setResult2("result2").build();
        responseObserver.onNext(result);
        responseObserver.onNext(result);
        try {
            Thread.sleep(2000);
        }
        catch (Exception ex){}
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    /**
     * rpc methodDoubleStream(stream Request) returns (stream Result){}
     */
    @Override
    public StreamObserver<Request> methodDoubleStream(StreamObserver<Result> responseObserver) {
        return new StreamObserver<Request>() {
            @Override
            public void onNext(Request value) {
                System.out.print("methodDoubleStream 收到了请求："+value);
                Result result = Result.newBuilder().setResult1("result1").setResult2("result2").build();
                Result result2 = Result.newBuilder().setResult1("result122").setResult2("result222").build();
                responseObserver.onNext(result);
                responseObserver.onNext(result2);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        ServerBuilder.forPort(2)
                .addService(new JavaGrpcServer())
                .addService(new PerformanceTestServiceServer())
                .build()
                .start();
    }
}
