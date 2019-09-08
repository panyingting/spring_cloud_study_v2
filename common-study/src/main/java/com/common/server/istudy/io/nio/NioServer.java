package com.common.server.istudy.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by panyingting on 2019/1/26.
 */
public class NioServer {

    private final static Logger logger = LoggerFactory.getLogger(NioServer.class);

    public static void start(int port) throws IOException, InterruptedException {


        Selector selector = Selector.open();
        Selector subSelector = Selector.open();

        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.socket().bind(new InetSocketAddress(port), 80);
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        int num = 0;
        while (true) {
            num++;
            selector.select();
            Thread.sleep(300L);
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();

            logger.info("\n\n==== size:{}", selectionKeySet.size());
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socket = ssc.accept();
                    socket.configureBlocking(false);
                    SelectionKey selectionKey1 = socket.register(subSelector, SelectionKey.OP_READ);
                    selectionKey1.attach("hello world");

                }


            }
            if (num % 3 == 0) {
                subSelector.select();
                Set<SelectionKey> keys = subSelector.selectedKeys();
                System.out.println("keys size--------:" + keys.size());
                Iterator<SelectionKey> subIterator = keys.iterator();

                while (subIterator.hasNext()) {
                    SelectionKey selectionKey = subIterator.next();
                    subIterator.remove();


                    if (selectionKey.isReadable()) {
                        SocketChannel socket = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int read = socket.read(buffer);
                        buffer.flip();

                        System.out.println(selectionKey.attachment() + "/" + buffer.get());
//                        if(read > 0){
//                            byte[] bytes = new byte[buffer.remaining()];
//                            int intVal = buffer.get();
//                            logger.info("服务器接收信息：intVal:{}", intVal);
//                            intVal = intVal * 2;
//                            buffer.flip();
//                            buffer.putInt(intVal);
//                            buffer.flip();
//                            socket.write(buffer);
//
//                        }

                    }

                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        logger.info("heklllooo??????\n\n");
        start(8001);

        RandomAccessFile file = new RandomAccessFile("", "R");
//        file.getChannel().read();
    }
}
