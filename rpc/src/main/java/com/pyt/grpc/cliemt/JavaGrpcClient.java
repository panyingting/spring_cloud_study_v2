package com.pyt.grpc.cliemt;

/**
 * @author : Pan Yingting
 * @date : 2021/1/10 5:41 下午
 */
import demo.test.DemoResult;
import demo.test.PerformanceTestServiceGrpc;
import demo.test.RequestDemo;
import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java_test.Request;
import java_test.Result;
import java_test.TestServiceGrpc;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class JavaGrpcClient
{
    private Channel channel = channel();

    TestServiceGrpc.TestServiceBlockingStub testServiceBlockingStub = TestServiceGrpc.newBlockingStub(channel);
    TestServiceGrpc.TestServiceStub testServiceStub = TestServiceGrpc.newStub(channel);
    PerformanceTestServiceGrpc.PerformanceTestServiceBlockingStub performanceTestServiceBlockingStub = PerformanceTestServiceGrpc.newBlockingStub(channel);

    public Result testServiceBlockingStub()
    {


        Request request = Request.newBuilder().setRequest1("test1").setRequest2("test2").build();
        java_test.Result result = testServiceBlockingStub.method(request);

        System.out.println("result:" + result);
        return result;
    }

    public void methodRequestStream() {
        Request request = Request.newBuilder().setRequest1("test1").setRequest2("test2").build();
        StreamObserver<Result> responseObserver = new StreamObserver<Result>() {
            @Override
            public void onNext(Result result) {
                System.out.print("返回了结果: "+ result);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        } ;

        StreamObserver<Request> result =testServiceStub.methodRequestStream(responseObserver);
        result.onNext(request);
        result.onNext(request);
        result.onNext(request);
        result.onCompleted();

        try {
            Thread.sleep(6000);
        }
        catch (Exception ex){}
    }

    public void methodResultStream() {

        StreamObserver<Result> responseObserver = new StreamObserver<Result>() {
            @Override
            public void onNext(Result result) {
                System.out.print("返回了结果 : "+ result);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        };
        Request request = Request.newBuilder().setRequest1("test1").setRequest2("test2").build();
        testServiceStub.methodResultStream(request, responseObserver);
        try {
            Thread.sleep(6000);
        }
        catch (Exception ex){}
    }

    public void methodDoubleStream() {
        Request request = Request.newBuilder().setRequest1("test1").setRequest2("test2").build();
        StreamObserver<Result> responseObserver = new StreamObserver<Result>() {
            @Override
            public void onNext(Result result) {
                System.out.print("返回了结果 : "+ result);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        };
        StreamObserver<Request> result = testServiceStub.methodDoubleStream(responseObserver);
        result.onNext(request);
        result.onNext(request);
        result.onNext(request);
        result.onCompleted();

        try {
            Thread.sleep(6000);
        }
        catch (Exception ex){}
    }

    public void getFromRpc(){
        RequestDemo requestDemo = RequestDemo.newBuilder().setCategoryId(1231).setUid("3wer2").build();
        for (int i = 0; i < 1000; i++) {
            DemoResult demoResult = performanceTestServiceBlockingStub.performanceTest(requestDemo);
        }

        long mills = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            DemoResult demoResult = performanceTestServiceBlockingStub.performanceTest(requestDemo);
        }
        long cost = System.currentTimeMillis() - mills;

        System.out.println("商城rpc调用100次花费时间：" + cost);

    }


    private Channel channel()
    {
        return ManagedChannelBuilder
                .forAddress("127.0.0.1",2)
                .usePlaintext(true)
                .build();
    }
}

