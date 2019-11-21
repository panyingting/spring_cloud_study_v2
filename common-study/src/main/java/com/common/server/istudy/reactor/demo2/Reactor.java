package com.common.server.istudy.reactor.demo2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor {

    final ServerSocketChannel serverSocketChannel;
    final Selector selector;

    public Reactor() throws IOException {

        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();
    }

    private void init() throws IOException {
        serverSocketChannel.configureBlocking(false);
//        serverSocketChannel.bind(new InetSocketAddress(8081));
        serverSocketChannel.socket().bind(new InetSocketAddress(8006));
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new AcceptHandler(serverSocketChannel, selector));
    }

    private void reactor() throws IOException {

        init();
        while (!Thread.interrupted()) {
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                Runnable runnable = (Runnable) selectionKey.attachment();
                System.out.println("获取到数据信息：" + selectionKey.interestOps());
                runnable.run();
                iterator.remove();

            }

        }
    }


    public static void main(String[] args) throws IOException {

        new Reactor().reactor();
    }

}
