package com.common.server.istudy.io.aio.my;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AIO 服务端代码
 * Created by panyingting on 2019/1/28.
 */
public class AioServer {
    private static final Logger logger = LoggerFactory.getLogger(AioServer.class);

    private static ExecutorService executorService = Executors.newFixedThreadPool(8, (runnable) -> new Thread(runnable, "Aio服务端 Group线程"));

    public static void start(int port) {

        try {
            AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(executorService);
            AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel
                    .open(group)
                    .bind(new InetSocketAddress(port));

            assc.accept(null, new AioServerAcceptHandler(assc));

            logger.info("服务器启动中。。。。。。。");
            CountDownLatch latch = new CountDownLatch(1);
            latch.await();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
