package com.pyt.grpc.server;

import demo.test.DemoModel;
import demo.test.DemoResult;
import demo.test.PerformanceTestServiceGrpc;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Pan Yingting
 * @date : 2021/8/23 4:41 下午
 */
@Service
public class PerformanceTestServiceServer extends PerformanceTestServiceGrpc.PerformanceTestServiceImplBase {

    /**
     */
    @Override
    public void performanceTest(demo.test.RequestDemo request,
                                 io.grpc.stub.StreamObserver<demo.test.DemoResult> responseObserver) {

        int max = 50;
        List<DemoModel> list = new ArrayList<>(max);


        for (int i=0; i<max; i++) {

            DemoModel demoModel = DemoModel.newBuilder()
                    .setBuyWay("buyWay"+i)
                    .setPriceChannel(i*10)
                    .setCount(i)
                    .setProductCode(i+30000)
                    .setSn("sn")
                    .setUsedPeriod("usedPeriod"+i)
                    .build();
            list.add(demoModel);
        }
        DemoResult demoResult = DemoResult.newBuilder().addAllDemoModels(list).build();
        responseObserver.onNext(demoResult);
        responseObserver.onCompleted();
    }
}
