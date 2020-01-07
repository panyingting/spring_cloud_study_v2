package com.common.study.dump;

import com.alibaba.fastjson.JSON;
import com.common.study.bean.UserBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OutOfMemoryAndDumpDemo {

    private static final Map<String, Object> map = new ConcurrentHashMap<>();

    @Resource
    ExecutorService executorService;

    @Test
    public void outOfMemoryTest(){

        executorService.submit(new DemoA());
        executorService.submit(new DemoB());
        executorService.submit(new DemoC());

        executorService.submit(new DemoA());
        executorService.submit(new DemoB());
        executorService.submit(new DemoC());

        System.out.println("Finish.........");

    }


    class DemoA implements Runnable{
        @Override
        public void run() {
            for(int i=0; i<Integer.MAX_VALUE; i++){
                map.put("hepo"+i, i);
            }
        }
    }

    class DemoB implements Runnable{
        @Override
        public void run() {
            for(int i=0; i<Integer.MAX_VALUE; i++){
                map.put("hepo"+i, new UserBean());
            }
        }
    }

    class DemoC implements Runnable{
        @Override
        public void run() {
            for(int i=0; i<Integer.MAX_VALUE; i++){
                map.put("hepo"+i, JSON.toJSONString(new UserBean()));
            }
        }
    }

}
