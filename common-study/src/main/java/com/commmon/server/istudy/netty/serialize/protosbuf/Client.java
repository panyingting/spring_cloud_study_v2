package com.commmon.server.istudy.netty.serialize.protosbuf;

import com.commmon.server.istudy.netty.bean.AttachmentRequest;
import com.commmon.server.istudy.netty.bean.ResponseBean;
import com.commmon.server.istudy.netty.serialize.protosbuf.sub.ClientHandler;
import com.commmon.server.istudy.netty.serialize.protosbuf.sub.NettyMessageDecoder;
import com.commmon.server.istudy.netty.serialize.protosbuf.sub.NettyMessageEncoder;
import com.commmon.server.istudy.netty.serialize.protosbuf.sub.ProtostuffSerializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.File;
import java.io.FileInputStream;


public class Client {
    public static void main(String[] args) throws Exception {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc.pipeline().addLast(new NettyMessageDecoder<>(ResponseBean.class, ProtostuffSerializer.class, 1 << 20, 2, 4));
                        sc.pipeline().addLast(new NettyMessageEncoder<>(AttachmentRequest.class, ProtostuffSerializer.class));
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });

        ChannelFuture cf = b.connect("127.0.0.1", 8765).sync();

        for (int i = 0; i < 50; i++) {
            AttachmentRequest req = new AttachmentRequest();
            req.setId(i + "");
            req.setName("pro" + i);
            req.setRequestMessage("数据信息" + i);
            String path = "/Users/macbook/Documents/下单时序图.pdf";
            File file = new File(path);
            FileInputStream in = new FileInputStream(file);
            byte[] data = new byte[in.available()];
            int len = in.read(data);
            in.close();
            req.setAttachment(data);
            cf.channel().writeAndFlush(req);
            System.out.println("读取数据长度：" + len);
        }

        cf.channel().closeFuture().sync();
        group.shutdownGracefully();
    }

// ————————————————
//    版权声明：本文为CSDN博主「青鸟&飞鱼」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/u014401141/article/details/79224789
//
}
