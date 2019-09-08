package com.common.server.istudy.netty.serialize.protosbuf.sub;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * netty编码实现类
 *
 * @param <T>
 * @param <K>
 */
public final class NettyMessageEncoder<T, K extends Serializer> extends MessageToByteEncoder {
    private final byte type = 0X00;
    private final byte flag = 0X0F;

    private Serializer serializer;
    private Class<T> clazz;

    public NettyMessageEncoder(Class<T> clazz, Class<K> serial) {
        this.clazz = clazz;
        serializer = SerializerFactory.getSerializer(serial);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg,
                          ByteBuf out) throws Exception {
        try {
            out.writeByte(type);
            out.writeByte(flag);
            byte[] data = serializer.encode(msg);
            out.writeInt(data.length);
            out.writeBytes(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
// ————————————————
//         版权声明：本文为CSDN博主「青鸟&飞鱼」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
//         原文链接：https://blog.csdn.net/u014401141/article/details/79224789
