package com.common.server.istudy.netty.serialize.protosbuf.sub;

import java.io.IOException;

public interface Serializer {

    public byte[] encode(Object msg) throws IOException;

    public <T> T decode(byte[] buf, Class<T> type) throws IOException;
}
