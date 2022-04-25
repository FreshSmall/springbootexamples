package com.example.springbootredis.job;

import com.example.springbootredis.util.DateUtils;
import com.example.springbootredis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Random;

@Service
public class CronJob1 {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Jedis jedis;

//    @Scheduled(cron = "0/5 * * * * *")
    public void executeTask() {
        try {

//            if (RedisUtil.lock_setnx(jedis, RedisUtil.FLAG, "1", 5)) {
            if (true) {
                Random a = new Random();
                int secods = a.nextInt(5);
                System.out.println("定时任务CronJob1执行,执行时间:" + DateUtils.dateToString(new Date()) + ",休眠时间:" + secods);
                Thread.sleep(secods * 1000L);
            } else {
                System.out.println("没有获取到分布式锁");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
