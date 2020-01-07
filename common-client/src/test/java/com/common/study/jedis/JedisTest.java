package com.common.study.jedis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JedisTest {

    @Resource
    private JedisAttrLockTest jedisAttrLockTest;

    @Test
    public void test2() throws InterruptedException {
        for(int i=0; i<=100; i++){
            Boolean bb = jedisAttrLockTest.tryLock("1231,", "32");

            System.out.println(bb);

            Thread.sleep(1000L);
        }

    }

}