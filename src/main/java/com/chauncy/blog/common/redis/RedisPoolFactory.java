package com.chauncy.blog.common.redis;

import com.chauncy.blog.common.util.PropertyUtil;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisPoolFactory {

    private static final String HOST = PropertyUtil.getProperty("redis.host");

    private static final Integer PORT = Integer.valueOf(PropertyUtil.getProperty("redis.port"));

    private static final String PASSWORD = PropertyUtil.getProperty("redis.password");

    private static final Integer TIMEOUT = Integer.valueOf(PropertyUtil.getProperty("redis.timeout"));

    private static final Integer MAXTOTAL = Integer.valueOf(PropertyUtil.getProperty("redis.poolMaxTotal"));

    private static final Integer MAXIDLE = Integer.valueOf(PropertyUtil.getProperty("redis.poolMaxIdle"));

    private static final Integer MINIDLE = Integer.valueOf(PropertyUtil.getProperty("redis.poolMinIdle"));

    private static final Integer MAXWAIT = Integer.valueOf(PropertyUtil.getProperty("redis.poolMaxWait"));

    private static JedisPool pool = null;

    static {
        init();
    }

    private static void init() {

        // JedisPool 的配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        // 最大连接数
        jedisPoolConfig.setMaxTotal(MAXTOTAL);
        // 最大的空闲状态的 jedis 实例的个数
        jedisPoolConfig.setMaxIdle(MAXIDLE);
        // 最小的空闲状态的 jedis 实例的个数
        jedisPoolConfig.setMinIdle(MINIDLE);
        // 在 borrow 一个 jedis 实例的时候，是否要进行验证操作，如果赋值 true。则得到的 jedis 实例肯定是可以用的。
        jedisPoolConfig.setTestOnBorrow(true);
        // 在 return 一个 jedis 实例的时候，是否要进行验证操作，如果赋值 true。则放回 jedispool 的 jedis 实例肯定是可以用的。
        jedisPoolConfig.setTestOnReturn(true);
        // 最大等待时间
        jedisPoolConfig.setMaxWaitMillis(MAXWAIT);

        pool = new JedisPool(jedisPoolConfig, HOST, PORT, TIMEOUT, PASSWORD);
    }

    /**
     * 获取 Jedis 连接
     *
     * @return
     */
    public Jedis getJedis() {
        return pool.getResource();
    }

    /**
     * 归还 Jedis 连接
     *
     * @param jedis
     */
    public void returnResource(Jedis jedis) {
        jedis.close();
    }

}
