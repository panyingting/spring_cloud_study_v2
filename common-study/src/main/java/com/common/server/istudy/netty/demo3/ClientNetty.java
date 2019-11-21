package com.common.server.istudy.netty.demo3;

import com.common.server.istudy.netty.demo3.handler.ClientRead1Handler;
import com.common.server.istudy.netty.demo3.handler.ClientRead2Handler;
import com.common.server.istudy.netty.demo3.handler.ClientWriteHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.UnsupportedEncodingException;


/**
 * 客户端发送请求
 */
public class ClientNetty {

    // 要请求的服务器的ip地址
    private String ip;
    // 服务器的端口
    private int port;

    public ClientNetty(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    // 请求端主题
    private void action() throws InterruptedException, UnsupportedEncodingException {

        EventLoopGroup bossGroup = new NioEventLoopGroup(2);

        Bootstrap bs = new Bootstrap();

        bs.group(bossGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 处理来自服务端的响应信息
                        socketChannel.pipeline().addLast(new ClientRead2Handler());
                        socketChannel.pipeline().addLast(new ClientRead1Handler());
//                        socketChannel.pipeline().addLast(new ClientWriteHandler());
                    }
                });

        // 客户端开启
        ChannelFuture cf = null;
        cf = bs.connect(ip, port).sync();
        for (int i = 0; i < 100; i++) {
            // 客户端开启

            // 发送客户端的请求
            String reqStr = "我是客户端" + i + "号===";
//            cf.channel().writeAndFlush(Unpooled.copiedBuffer(reqStr.getBytes("utf-8")));

        }
        // 等待直到连接中断
        cf.channel().closeFuture().sync();
    }

    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
        new ClientNetty("127.0.0.1", 8006).action();
    }

}

