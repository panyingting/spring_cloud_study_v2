package com.common.server.istudy.netty.serialize.protosbuf.sub;

public class SerializerFactory {

    public static <T> Serializer getSerializer(Class<T> t) {
        Serializer serializer = null;
        try {
            serializer = (Serializer) t.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return serializer;
    }

// ————————————————
//    版权声明：本文为CSDN博主「青鸟&飞鱼」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/u014401141/article/details/79224789
}
