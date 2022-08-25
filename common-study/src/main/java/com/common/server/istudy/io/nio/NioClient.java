package com.common.server.istudy.io.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by panyingting on 2019/1/26.
 */
public class NioClient {

    private final static Logger logger = LoggerFactory.getLogger(NioClient.class);

    public static void connet(String ip, int port) {
        try {
            final Selector selector = Selector.open();
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);

            boolean conn = sc.connect(new InetSocketAddress(ip, port));
            if (conn) {
                sc.register(selector, SelectionKey.OP_READ);
                for (int i = 0; i < 10; i += 10) {
                    Thread.sleep(100);
                    send(sc, (int) (Math.random() * i));
                }
            } else {
                sc.register(selector, SelectionKey.OP_CONNECT);
            }

            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            selector.select();
                            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                            while (iterator.hasNext()) {
                                SelectionKey selectionKey = iterator.next();
                                iterator.remove();

                                SocketChannel channel = (SocketChannel) selectionKey.channel();
                                if (selectionKey.isReadable()) {
                                    ByteBuffer buffer = ByteBuffer.allocate(8);
                                    int read = channel.read(buffer);
                                    if (read > 0) {
                                        buffer.flip();
                                        int val = buffer.getInt();
                                        logger.info("服务器返回成功，val:{}", val);
                                    }
                                }
                                if (selectionKey.isConnectable()) {
                                    if (channel.finishConnect()) {
                                        sc.register(selector, SelectionKey.OP_READ);
                                        for (int i = 0; i < 1; i += 1) {
                                            Thread.sleep(1000);
                                            send(sc, (int) (Math.random() * i));
                                        }
                                    } else {
                                        logger.info("链接失败.........");
                                        System.exit(1);
                                    }
                                }
                            }
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();


            logger.info("Out。。。。。。。");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void send(SocketChannel sc, int val) throws IOException {

        for (int i = 0; i < 10; i++) {
            ByteBuffer buffer = ByteBuffer.allocate(12);
            buffer.putInt(i*10);
            buffer.putInt(i*10+1);
            buffer.putInt(i*10+2);
            buffer.flip();
            sc.write(buffer);
            if (!buffer.hasRemaining()) {
                logger.info("给服务器发送信息成功！");
            }
        }


    }

    public static void main(String[] args) throws InterruptedException {

        connet("127.0.0.1", 8001);

        Thread.sleep(20000L);
    }
}
