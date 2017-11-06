package com.lokey.student.web.service.impl;


import com.lokey.student.web.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by lokey on 17/6/1.
 */
@Component(value = "redisService")
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param key
     * @param object
     * @param liveTime
     */
    @Override
    public void set(String groupKey, String key,  Object object, long liveTime) {
        redisTemplate.opsForHash().put(groupKey,key,object);
        redisTemplate.expire(key, liveTime, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String groupKey, String key) {
        Object object = redisTemplate.opsForHash().get(groupKey, key);
        return object;
    }


    @Override
    public void del(String groupKey, String key) {
        redisTemplate.opsForHash().delete(groupKey,key);
    }

}
