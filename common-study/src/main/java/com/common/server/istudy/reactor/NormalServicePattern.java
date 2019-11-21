package com.common.server.istudy.reactor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NormalServicePattern implements Runnable {

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8082);
            while (true) {
                Socket socket = serverSocket.accept();

                RequestHandler handler = new RequestHandler(socket);

                new Thread(handler).start();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class RequestHandler implements Runnable {

        Socket socket;

        RequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            byte[] buf = new byte[256];
            try {
                socket.getInputStream().read(buf);
                System.out.println("结果：" + new String(buf, "utf-8"));

                socket.getOutputStream().write("收到回复".getBytes());
                socket.getOutputStream().flush();
                socket.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();
        NormalServicePattern servicePattern = new NormalServicePattern();
        new Thread(servicePattern).start();

//        lock.lock();
//        condition.await();
//        lock.unlock();
    }


}
