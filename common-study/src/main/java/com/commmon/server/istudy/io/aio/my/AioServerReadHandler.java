package com.commmon.server.istudy.io.aio.my;

import java.nio.channels.CompletionHandler;

/**
 * Created by panyingting on 2019/1/28.
 */
public class AioServerReadHandler implements CompletionHandler<Integer, StringBuffer> {
    @Override
    public void completed(Integer result, StringBuffer attachment) {

    }

    @Override
    public void failed(Throwable exc, StringBuffer attachment) {

    }
}
