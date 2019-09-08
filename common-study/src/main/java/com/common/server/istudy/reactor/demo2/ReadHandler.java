package com.common.server.istudy.reactor.demo2;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ReadHandler implements Runnable {

    private final Selector selector;

    private final SocketChannel channel;

    public ReadHandler(Selector selector, SocketChannel channel) {
        this.selector = selector;
        this.channel = channel;
    }

    @Override
    public void run() {

        ByteBuffer bytebuffer = ByteBuffer.allocate(1024);

        try {

            int count = channel.read(bytebuffer);

            StringBuilder stringBuilder = new StringBuilder();
            while (count > 0) {
                byte[] data = new byte[count];
                bytebuffer.flip();
                bytebuffer.get(data);
                stringBuilder.append(new String(data, "utf-8"));
                bytebuffer.clear();
                count = channel.read(bytebuffer);
            }
            System.out.println("read收到消息为：" + stringBuilder.toString());

            SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_WRITE);

            selectionKey.attach(new WriteHandler(channel, selectionKey));

            selector.wakeup();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
