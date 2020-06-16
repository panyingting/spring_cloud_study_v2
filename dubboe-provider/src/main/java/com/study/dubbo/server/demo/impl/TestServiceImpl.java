package com.study.dubbo.server.demo.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.study.dubbo.server.demo.TestService;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

//@Service(version = "1.0.0",timeout = 3000)
public class TestServiceImpl implements TestService {


    public TestServiceImpl(){
        System.out.println("hello");
    }
    @Override
    public void testDubbo() {
        System.out.println("hello invoke。。。");
    }

    @Test
    public void testMap(){
        HashMap<String, String> map = new HashMap<>(1);
        map.put("","");
        System.out.println(map);
    }
}

// ————————————————
//    版权声明：本文为CSDN博主「赏花同学」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/weixin_41843053/article/details/83687289
