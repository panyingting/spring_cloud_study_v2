package com.common.study.jedis;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

@Component
@SpringBootApplication
public class JedisAttrLockTest {

    private static final String SUCCESS = "OK";


    @Resource
    private RedisTemplate<Object, Object> redisTemplate;


    public boolean tryLock(String key, String requestId) {



        return redisTemplate.execute((RedisCallback<Boolean> )connection ->{

            Jedis jedis = (Jedis)connection.getNativeConnection();

            /**
             *
             * EX代表秒，PX代表毫秒
             */
            String val = jedis.set(key, requestId, "NX", "EX", 10);
            return SUCCESS.equals(val);
        });

    }






}


/*
*
* /**
 * 存储数据到缓存中，并制定过期时间和当Key存在时是否覆盖。
 *
 * @param key
 * @param value
 * @param nxxx
 *            nxxx的值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，则只有当key已经存在时才进行set
 *
 * @param expx expx的值只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒。
 * @param time 过期时间，单位是expx所代表的单位。
 * @return
 *
String set(String key, String value, String nxxx, String expx, long time);

* */
