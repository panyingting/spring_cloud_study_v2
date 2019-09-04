package com.commmon.server.istudy.netty.serialize.protosbuf.sub;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * netty解码实现类
 *
 * @param <T>
 * @param <K>
 */
public class NettyMessageDecoder<T, K extends Serializer> extends LengthFieldBasedFrameDecoder {

    private Logger logger = LoggerFactory.getLogger(getClass());
    //判断传送客户端传送过来的数据是否按照协议传输，头部信息的大小应该是 byte+byte+int = 1+1+4 = 6
    private static final int HEADER_SIZE = 6;

    private Serializer serializer;
    private Class<T> clazz;

    public NettyMessageDecoder(Class<T> clazz, Class<K> serial, int maxFrameLength, int lengthFieldOffset,
                               int lengthFieldLength) throws IOException {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        this.clazz = clazz;

        this.serializer = SerializerFactory.getSerializer(serial);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in)
            throws Exception {

        if (in.readableBytes() < HEADER_SIZE) {
            return null;
        }
        in.markReaderIndex();
        //注意在读的过程中，readIndex的指针也在移动
        byte type = in.readByte();
        byte flag = in.readByte();
        int dataLength = in.readInt();
        if (in.readableBytes() < dataLength) {
            logger.error("body length < {}\n", dataLength);
            in.resetReaderIndex();
            return null;
        }

        byte[] data = new byte[dataLength];
        in.readBytes(data);

        try {
            return serializer.decode(data, clazz);
        } catch (Exception e) {
            throw new RuntimeException("serializer decode error");
        }
    }
}

// ————————————————
//         版权声明：本文为CSDN博主「青鸟&飞鱼」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
//         原文链接：https://blog.csdn.net/u014401141/article/details/79224789
