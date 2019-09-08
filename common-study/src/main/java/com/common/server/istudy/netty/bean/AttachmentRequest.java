package com.common.server.istudy.netty.bean;

import java.util.Arrays;

public class AttachmentRequest {

    private String id;
    private String name;
    private String requestMessage;
    private byte[] attachment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "AttachmentRequest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", requestMessage='" + requestMessage + '\'' +
                ", attachment=" + Arrays.toString(attachment) +
                '}';
    }

    // ————————————————
//    版权声明：本文为CSDN博主「青鸟&飞鱼」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/u014401141/article/details/79224789
//
}
