package com.commmon.server.istudy.netty.demo3.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class ClientWriteHandler extends ChannelOutboundHandlerAdapter {


    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {

        try {
            ByteBuf byteBuf = (ByteBuf) msg;
            ByteBuf newBuf = Unpooled.buffer(byteBuf.readableBytes() + 50);
            byte[] bytes = new byte[byteBuf.readableBytes()];
            byteBuf.readBytes(bytes);
            newBuf.writeBytes(bytes);
            newBuf.writeBytes("你好，新加了字节信息".getBytes());
            System.out.println("========你好，新加了字节信息=======");
            ctx.write(newBuf, promise);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.fireExceptionCaught(cause);
    }

}
