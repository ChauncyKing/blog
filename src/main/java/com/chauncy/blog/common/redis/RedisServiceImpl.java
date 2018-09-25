package com.chauncy.blog.common.redis;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * Redis 操作 API 实现类
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisPoolFactory poolFactory;

    @Override
    public String connectTest() {
        Jedis resource = null;
        try {
            // 获取连接
            resource = poolFactory.getJedis();
            return resource.ping();
        } finally {
            // 归还连接
            poolFactory.returnResource(resource);
        }
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        Jedis resource = null;
        try {
            // 获取连接
            resource = poolFactory.getJedis();
            String value = resource.get(key);
            T t = JSONObject.parseObject(value, clazz);
            return t;
        } finally {
            // 归还连接
            poolFactory.returnResource(resource);
        }
    }

    @Override
    public void set(String key, Object value) {
        Jedis resource = null;
        try {
            // 获取连接
            resource = poolFactory.getJedis();
            String strValue = JSONObject.toJSONString(value);
            resource.set(key, strValue);
        } finally {
            // 归还连接
            poolFactory.returnResource(resource);
        }
    }

    @Override
    public void setWithExpire(String key, Object value, int expire) {
        Jedis resource = null;
        try {
            // 获取连接
            resource = poolFactory.getJedis();
            if (value instanceof String) {
                resource.setex(key, expire, value.toString());
            } else {
                String strValue = JSONObject.toJSONString(value);
                resource.setex(key, expire, strValue);
            }
        } finally {
            // 归还连接
            poolFactory.returnResource(resource);
        }
    }

    @Override
    public boolean exist(String key) {
        Jedis resource = null;
        try {
            // 获取连接
            resource = poolFactory.getJedis();
            return resource.exists(key);
        } finally {
            // 归还连接
            poolFactory.returnResource(resource);
        }
    }

    @Override
    public Long incr(String key) {
        Jedis resource = null;
        try {
            // 获取连接
            resource = poolFactory.getJedis();
            return resource.incr(key);
        } finally {
            // 归还连接
            poolFactory.returnResource(resource);
        }
    }

    @Override
    public Long incrBy(String key, Long num) {
        Jedis resource = null;
        try {
            // 获取连接
            resource = poolFactory.getJedis();
            return resource.incrBy(key, num);
        } finally {
            // 归还连接
            poolFactory.returnResource(resource);
        }
    }

    @Override
    public Long decr(String key) {
        Jedis resource = null;
        try {
            // 获取连接
            resource = poolFactory.getJedis();
            return resource.decr(key);
        } finally {
            // 归还连接
            poolFactory.returnResource(resource);
        }
    }

    @Override
    public Long decrBy(String key, Long num) {
        Jedis resource = null;
        try {
            // 获取连接
            resource = poolFactory.getJedis();
            return resource.decrBy(key, num);
        } finally {
            // 归还连接
            poolFactory.returnResource(resource);
        }
    }

    @Override
    public Long hset(String key, String field, Object value) {
        Jedis resource = null;
        try {
            // 获取连接
            resource = poolFactory.getJedis();
            String strValue = JSONObject.toJSONString(value);
            return resource.hset(key, field, strValue);
        } finally {
            // 归还连接
            poolFactory.returnResource(resource);
        }
    }

    @Override
    public <T> T hget(String key, String field, Class<T> clazz) {
        Jedis resource = null;
        try {
            // 获取连接
            resource = poolFactory.getJedis();
            String value = resource.hget(key, field);
            T t = JSONObject.parseObject(value, clazz);
            return t;
        } finally {
            // 归还连接
            poolFactory.returnResource(resource);
        }
    }
}
