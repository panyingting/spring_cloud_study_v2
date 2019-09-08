package com.common.server.istudy.netty.demo2;

import com.common.server.istudy.netty.demo.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class NettyServer {

    public static void start(int port) throws InterruptedException {


        EventLoopGroup acceptGroup = new NioEventLoopGroup(2);

        EventLoopGroup workGroup = new NioEventLoopGroup(4);

        ServerBootstrap bootstrap = new ServerBootstrap();

        ChannelFuture cf = bootstrap.group(acceptGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childHandler(new EchoServerHandler())
                .bind()
                .sync();

        cf.channel().closeFuture().sync();

        acceptGroup.shutdownGracefully();
        workGroup.shutdownGracefully();
    }
}
