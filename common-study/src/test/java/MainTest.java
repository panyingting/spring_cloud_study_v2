import com.alibaba.fastjson.JSON;
import com.common.server.istudy.netty.serialize.marshalling.sub.TestAnnotation;
import com.common.server.istudy.netty.serialize.protosbuf.sub.ProtostuffSerializer;

import java.io.IOException;

public class MainTest {


    public static void main(String[] args) throws NoSuchMethodException, IOException {
        TestAnnotation tokenAnnotation = MainTest.class.getMethod("f").getAnnotation(TestAnnotation.class);
        System.out.println("hello");
        String json = (JSON.toJSONString(tokenAnnotation));

        System.out.println(json);


        TestAnnotation tokenAnnotation1 = JSON.parseObject(json, TestAnnotation.class);
        System.out.println(tokenAnnotation1.requireAuth());
    }

    @TestAnnotation()
    public static void f(){

    }
    
}
