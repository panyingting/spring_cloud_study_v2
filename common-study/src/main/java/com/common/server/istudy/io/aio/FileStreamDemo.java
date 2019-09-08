package com.common.server.istudy.io.aio;

import java.io.*;

public class FileStreamDemo {

    public static void main(String[] args) throws IOException {

        File file = new File("/Users/macbook/workspace/files/Test.txt");

        FileInputStream inputStream = new FileInputStream(file);

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        byte[] buff = new byte[1024];
        bufferedInputStream.read(buff, 0, buff.length);

        System.out.println(new String(buff, "UTF-8"));
    }


}
