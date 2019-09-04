package com.commmon.server.istudy.reactor.demo2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteHandler implements Runnable {

    private final SocketChannel channel;
    private final SelectionKey selectionKey;

    public WriteHandler(SocketChannel channel, SelectionKey selectionKey) {
        this.channel = channel;
        this.selectionKey = selectionKey;
    }

    @Override
    public void run() {

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("你好，已收到消息！".getBytes());

        buffer.flip();

        try {
            System.out.println("通道关闭！");
            channel.write(buffer);
            selectionKey.selector();
            channel.register(selectionKey.selector(), SelectionKey.OP_READ);
            selectionKey.attach(new ReadHandler(selectionKey.selector(), channel));
//            channel.close();
//            selectionKey.cancel();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws ParseException {
        System.out.println(new Date(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss.SSS").parse("2019/08/22-12:42:45.541").getTime()));
    }
}
