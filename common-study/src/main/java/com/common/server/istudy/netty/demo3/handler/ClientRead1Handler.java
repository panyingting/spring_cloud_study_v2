package com.common.server.istudy.netty.demo3.handler;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * 读取服务器返回的响应信息
 */
public class ClientRead1Handler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            ByteBuf bb = (ByteBuf) msg;
            byte[] respByte = new byte[bb.readableBytes()];
            bb.readBytes(respByte);
            String respStr = new String(respByte, "utf-8");
            System.out.println("client-1--收到响应：" + respStr);

            if (!respStr.contains("1008611")) {
                System.out.println("再次回复服务端...");
                ctx.writeAndFlush(Unpooled.copiedBuffer("1008611".getBytes()));
            }
        } finally {
            // 必须释放msg数据
            ReferenceCountUtil.release(msg);

        }

    }


    // 数据读取完毕的处理
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端-1-读取数据完毕");
    }

    // 出现异常的处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("client-1 读取数据出现异常");
        ctx.close();
    }

}

