package com.chauncy.blog.common.redis;

public interface RedisService {

    String connectTest();

    <T> T get(String key, Class<T> clazz);

    void set(String key, Object value);

    void setWithExpire(String key, Object value, int expire);

    boolean exist(String key);

    Long incr(String key);

    Long incrBy(String key, Long num);

    Long decr(String key);

    Long decrBy(String key, Long num);

    Long hset(String key, String field, Object value);

    <T> T hget(String key, String field, Class<T> clazz);

}
