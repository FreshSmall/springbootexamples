package com.example.springbootredis.util;

import redis.clients.jedis.Jedis;

public class RedisUtil {

    public static final String FLAG = "cron-flag";

    public static final String FLAG_NX = "cron-flag-nx";

    private long sleepTime = 100;
    /**
     * 直接使用setnx + expire方式获取分布式锁
     * 非原子性
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    public static boolean lock_setnx(Jedis jedis, String key, String value, int timeout) {
        Long result = jedis.setnx(key, value);
        // result = 1时，设置成功，否则设置失败
        if (result == 1L) {
            return jedis.expire(key, timeout) == 1L;
        } else {
            return false;
        }
    }
}
