package com.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.xxx.serial.UserProtoBuf;
import java_test.ReqUser;
import java_test.Request;
import org.junit.Test;

/**
 * @author : Pan Yingting
 * @date : 2021/1/7 10:55 下午
 */

public class UserProtoMain {

    public static void main(String[] args) throws InvalidProtocolBufferException{
        UserProtoBuf.User user = UserProtoBuf.User.newBuilder()
                .setTall(179)
                .setMoney(320)
                .setAge2(32)
                .setAge(1231)
                .setAge3(32)
                .setAge4(1)
                .setId(1)
                .setMobile("16485511770")
                .setName("11")
                .build();

        com.google.protobuf.ByteString byteString = user.toByteString();
        String utf8Str = byteString.toStringUtf8();
        System.out.println(utf8Str);
        com.google.protobuf.ByteString byteString2 = com.google.protobuf.ByteString.copyFromUtf8(utf8Str);
        UserProtoBuf.User user2 = UserProtoBuf.User.parseFrom(byteString2);
        System.out.println(user2);


    }

    @Test
    public void test2() throws InvalidProtocolBufferException {
        Request request = Request.newBuilder()
                .setRequest1("1231")
                .setRequest2("你哈")
                .setAge(1)
                .setAge2(1)
                .setAge3(1)
                .setTall(179f)
                .setMoney(320D)
                .setAge2(32)
                .build();
        String str = request.toByteString().toStringUtf8();
        System.out.println(str);

        ByteString byteString = ByteString.copyFromUtf8(str);
        Request request2 = Request.parseFrom(byteString);
        System.out.println(request2);

    }

    @Test
    public void test3() throws InvalidProtocolBufferException {
        System.out.println(2.00-1.10f);
    }
}


