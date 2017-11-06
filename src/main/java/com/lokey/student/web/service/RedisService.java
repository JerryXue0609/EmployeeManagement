package com.lokey.student.web.service;

/**
 * Created by yinhaijin on 15/6/1.
 */
public interface RedisService {
    void set(String groupKey, String key, Object object, long liveTime);
    Object get(String groupKey, String key);
    void del(String groupKey, String key);
}
