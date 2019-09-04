package com.commmon.server.istudy.io.aio;


import com.commmon.server.istudy.io.aio.helper.CharsetHelper;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * 异步IO 客户端
 * Created by panyingting on 2019/1/28.
 */
public class AioClient {
    private AsynchronousSocketChannel channel;
    private CountDownLatch latch;

    private AioClient(AsynchronousChannelGroup channelGroup, CountDownLatch latch) throws IOException, InterruptedException {
        this.latch = latch;
        initChannel(channelGroup);
    }

    private void initChannel(AsynchronousChannelGroup channelGroup) throws IOException {
        //在默认channel group下创建一个socket channel
        channel = AsynchronousSocketChannel.open(channelGroup);
        //设置Socket选项
        channel.setOption(StandardSocketOptions.TCP_NODELAY, true);
        channel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
        channel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
    }


    private void connect(int port) {
        System.out.println(Thread.currentThread().getName() + "---run");

        //连接服务器
        channel.connect(new InetSocketAddress("localhost", port), null, new CompletionHandler<Void, Void>() {
            final ByteBuffer readBuffer = ByteBuffer.allocateDirect(1024);

            @Override
            public void completed(Void result, Void attachment) {

                //helper.sleep();//等待写异步调用完成
                readBuffer.clear();
                //异步调用OS读取服务器发送的消息
                read(channel, latch, readBuffer);

                //异步写
                ByteBuffer buffer = ByteBuffer.allocate(128);
                buffer.put("Hello World over".getBytes());
                buffer.flip();

                writeBuffer(buffer, channel);
            }

            /**
             * 连接失败处理
             */
            @Override
            public void failed(Throwable exc, Void attachment) {
                System.out.println("client connect to server failed: " + exc);

                try {
                    shutdown(channel, latch);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static void shutdown(AsynchronousSocketChannel channel, CountDownLatch latch) throws IOException {
        if (channel != null) {
            channel.close();
        }

        latch.countDown();
    }


    private static void writeBuffer(ByteBuffer buffer, AsynchronousSocketChannel channel) {
        channel.write(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer buffer) {
                if (buffer.hasRemaining()) {
                    channel.write(buffer, buffer, this);
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
            }
        });
    }

    private static void read(AsynchronousSocketChannel channel, CountDownLatch latch, ByteBuffer readBuffer) {

        channel.read(readBuffer, null, new CompletionHandler<Integer, Object>() {

            @Override
            public void completed(Integer result, Object attachment) {
                try {
                    //异步读取完成后处理
                    if (result > 0) {
                        readBuffer.flip();
                        CharBuffer charBuffer = CharsetHelper.decode(readBuffer);
                        String answer = charBuffer.toString();
//                        byte[] bytes = answer.getBytes();
                        answer = URLDecoder.decode(answer, "utf-8");

                        System.out.println(Thread.currentThread().getName() + "---" + answer);
                        readBuffer.clear();

                    } else {
                        //对方已经关闭channel，自己被动关闭，避免空循环
                        shutdown(channel, latch);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * 读取失败处理
             */
            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("client read failed: " + exc);
                try {
                    shutdown(channel, latch);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup
                .withFixedThreadPool(Runtime.getRuntime().availableProcessors(), Executors.defaultThreadFactory());

        //只能跑一个线程，第二个线程connect会挂住，暂时不明原因
        CountDownLatch latch = new CountDownLatch(1);

        AioClient client = new AioClient(channelGroup, latch);
        client.connect(8083);


        latch.await();

        if (channelGroup != null) {
            channelGroup.shutdown();
        }
    }

}