package com.commmon.server.istudy.netty.demo3.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientRead2Handler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf bb = (ByteBuf) msg;
        byte[] respByte = new byte[bb.readableBytes()];

        bb.readBytes(respByte);
        // 直接转成对象
        String respStr = new String(respByte, "utf-8");
        System.out.println("client-2--收到响应：" + respStr);
        bb.readerIndex(0);
        ctx.fireChannelRead(bb);

    }


    // 数据读取完毕的处理
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端client-2读取数据完毕");
    }

    // 出现异常的处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("client-2 读取数据出现异常：");
        cause.printStackTrace();
        ctx.close();
    }

    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" ==== ClientRead2Handler handlerAdded 方法被调用");
    }
}
