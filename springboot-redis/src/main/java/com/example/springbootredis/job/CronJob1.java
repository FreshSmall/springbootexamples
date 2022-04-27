package com.example.springbootredis.job;

import com.example.springbootredis.util.DateUtils;
import com.example.springbootredis.util.RedisUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class CronJob1 {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    //    @Scheduled(cron = "0/5 * * * * *")
    public void executeTask() {
        String value = redisTemplate.opsForValue().get(RedisUtil.FLAG);
        if (!StringUtils.isEmpty(value)) {
            return;
        }
        try {
            redisTemplate.opsForValue().set(RedisUtil.FLAG, "1", 5, TimeUnit.SECONDS);
            Random a = new Random();
            int secods = a.nextInt(5);
            System.out.println("定时任务CronJob1执行,执行时间:" + DateUtils.dateToString(new Date()) + ",休眠时间:" + secods);
//            Thread.sleep(secods * 1000L);
        } finally {
            redisTemplate.delete(RedisUtil.FLAG);
        }
    }

//    @Scheduled(cron = "0/5 * * * * *")
    public void executeTask1() {
        Boolean result = redisTemplate.opsForValue().setIfAbsent(RedisUtil.FLAG_NX, "1");
        if (Boolean.FALSE.equals(result)) {
            return;
        }
        try {
            Random a = new Random();
            int seconds = a.nextInt(4) + 1;
            System.out.println("定时任务CronJob1执行,执行时间:" + DateUtils.dateToString(new Date()) + ",休眠时间:" + seconds);
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            redisTemplate.delete(RedisUtil.FLAG_NX);
        }
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void executeTask2() {
        //传入Redis的key
        String lockKey = "ticket";
        // redis setnx 操作
        RLock lock = redissonClient.getLock(lockKey);
        try {
            lock.lock();
            Random a = new Random();
            int seconds = a.nextInt(4) + 1;
            System.out.println("定时任务CronJob1执行,执行时间:" + DateUtils.dateToString(new Date()) + ",休眠时间:" + seconds);
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
