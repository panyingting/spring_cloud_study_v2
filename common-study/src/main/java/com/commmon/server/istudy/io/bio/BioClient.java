package com.commmon.server.istudy.io.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Bio 客户端
 * Created by panyingting on 2019/1/25.
 */
public class BioClient {

    static final Logger logger = LoggerFactory.getLogger(BioClient.class);

    private static int DEFAULT_SERVER_PORT = 8082;
    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void start() throws InterruptedException {
        System.out.println("客户端请求开始.......");

        try (Socket socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT)) {

            OutputStream out = socket.getOutputStream();
            for (int i = 0; i < 100000; i++) {
                out.write("你好".getBytes());
                Thread.sleep(100L);
                out.write("你好".getBytes());

//                InputStream in = socket.getInputStream();
//
//                byte[] buf = new byte[1024];
//                int num = in.read(buf);
//
//                if(num > 0){
//                    System.out.println("服务端返回结果："+ new String(buf, 0, num, "utf-8"));
//                }
//                else
//                    System.out.println("num: " + num);

//                Thread.sleep(100L);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws InterruptedException {
        BioClient.start();
    }
}
