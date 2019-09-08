package com.common.server.istudy.netty.serialize.protosbuf;

import com.common.server.istudy.netty.bean.AttachmentRequest;
import com.common.server.istudy.netty.bean.ResponseBean;
import com.common.server.istudy.netty.serialize.protosbuf.sub.NettyMessageDecoder;
import com.common.server.istudy.netty.serialize.protosbuf.sub.NettyMessageEncoder;
import com.common.server.istudy.netty.serialize.protosbuf.sub.ProtostuffSerializer;
import com.common.server.istudy.netty.serialize.protosbuf.sub.ServerHandler;
import com.common.server.istudy.netty.serialize.protosbuf.sub.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Server {

    public static void main(String[] args) throws Exception {
        EventLoopGroup pGroup = new NioEventLoopGroup();
        EventLoopGroup cGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(pGroup, cGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                //设置日志
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new NettyMessageDecoder<>(AttachmentRequest.class, ProtostuffSerializer.class, 1 << 20, 2, 4));
                        sc.pipeline().addLast(new NettyMessageEncoder<>(ResponseBean.class, ProtostuffSerializer.class));
                        sc.pipeline().addLast(new ServerHandler());
                    }
                });

        ChannelFuture cf = b.bind(8765).sync();

        cf.channel().closeFuture().sync();
        pGroup.shutdownGracefully();
        cGroup.shutdownGracefully();

    }

// ————————————————
//    版权声明：本文为CSDN博主「青鸟&飞鱼」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/u014401141/article/details/79224789
//
}
