package com.commmon.server.istudy.netty.serialize.marshalling.sub;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingEncoder;

public class MarshallingEncoderExt extends MarshallingEncoder {
    public MarshallingEncoderExt(MarshallerProvider provider) {
        super(provider);
    }

    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        try {
            super.encode(ctx, msg, out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
