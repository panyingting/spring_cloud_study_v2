package com.commmon.server.istudy.netty.demo3.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;

/**
 * 处理某个客户端的请求
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    // 读取数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf bb = (ByteBuf) msg;
        // 创建一个和buf同等长度的字节数组
        byte[] reqByte = new byte[bb.readableBytes()];
        // 将buf中的数据读取到数组中
        bb.readBytes(reqByte);
        String reqStr = new String(reqByte, "utf-8");
        System.out.println("server 接收到客户端端口号：" + ((InetSocketAddress) ctx.channel().remoteAddress()).getPort() + "的请求： " + reqStr);
        String respStr = "服务器的响应----：" + reqStr + " ======";

        // 返回给客户端响应                                                                                                                                                       和客户端链接中断即短连接，当信息返回给客户端后中断
        for (int i = 0; i < 1; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(respStr.getBytes()));//.addListener(ChannelFutureListener.CLOSE);
//            try {
//                Thread.sleep(2000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }


    // 数据读取完毕的处理
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.err.println("服务端读取数据完毕");
    }

    // 出现异常的处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("server 读取数据出现异常");
        ctx.close();
    }

}
