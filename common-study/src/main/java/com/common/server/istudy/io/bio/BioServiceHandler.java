package com.common.server.istudy.io.bio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * BIO 服务端处理器
 * Created by panyingting on 2019/1/25.
 */
public class BioServiceHandler implements Runnable {

    Logger logger = LoggerFactory.getLogger(BioServiceHandler.class);
    private Socket socket;

    public BioServiceHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.println("12312");
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

//            String line = reader.read();
            int num = reader.read();
            int result = num * 2;
            writer.write(result);
            writer.flush();
            System.out.println(String.format("========服务端读取:%s, 结果：%s", num, result));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null)
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
    }
}
