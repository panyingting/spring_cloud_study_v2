package com.commmon.server.istudy.netty.demo2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettryClient {

    public static void start(String ip, int serverPort) {


        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(new NioEventLoopGroup(2))
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(null);
    }
}
