package com.commmon.server.istudy.reactor.demo2;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class AcceptHandler implements Runnable {

    final ServerSocketChannel ssc;
    final Selector selector;

    public AcceptHandler(ServerSocketChannel socketChannel, Selector selector) {
        this.ssc = socketChannel;
        this.selector = selector;
    }

    @Override
    public void run() {

        try {

            SocketChannel socketChannel = ssc.accept();
            ssc.close();
            socketChannel.configureBlocking(false);
            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
            selectionKey.attach(new ReadHandler(selector, socketChannel));

            System.out.println("接收到新的连接成功！");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
