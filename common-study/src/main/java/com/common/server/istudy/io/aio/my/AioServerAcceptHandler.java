package com.common.server.istudy.io.aio.my;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 接收客户端链接
 * Created by panyingting on 2019/1/28.
 */
public class AioServerAcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Void> {

    private static final Logger logger = LoggerFactory.getLogger(AioServerAcceptHandler.class);

    private final AsynchronousServerSocketChannel assc;

    public AioServerAcceptHandler(AsynchronousServerSocketChannel assc) {
        this.assc = assc;
    }

    @Override
    public void completed(AsynchronousSocketChannel result, Void attachment) {
        logger.info("接收客户端链接请求........");
        assc.accept(null, this);
        ByteBuffer buffer = ByteBuffer.allocate(512);
        result.read(buffer, new StringBuffer(), new AioServerReadHandler());
    }

    @Override
    public void failed(Throwable exc, Void attachment) {

    }
}
