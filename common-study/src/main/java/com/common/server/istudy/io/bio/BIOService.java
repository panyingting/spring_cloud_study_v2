package com.common.server.istudy.io.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by panyingting on 2019/1/25.
 */
public class BIOService {

    static Logger logger = LoggerFactory.getLogger(BIOService.class);

    private static int DEFAULT_SERVER_PORT = 8001;

    static ExecutorService executor = Executors.newFixedThreadPool(8, (runnable) -> {
        Thread thread = new Thread(runnable, "服务线程");
        return thread;
    });

    public static void start() {
        try (ServerSocket socket = new ServerSocket(DEFAULT_SERVER_PORT)) {

            socket.setSoTimeout(60000);
            System.out.println("服务器即将启动.......");

            while (true) {
                Socket client = socket.accept();
                System.out.println("有新连接.........");
                executor.execute(new BioServiceHandler(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BIOService.start();
    }

}
