package com.example.springbootredis.job;

import com.example.springbootredis.util.DateUtils;
import com.example.springbootredis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@Service
public class CronJob2 {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Scheduled(cron = "0/5 * * * * *")
    public void executeTask() {
        String value = redisTemplate.opsForValue().get(RedisUtil.FLAG);
        if (!StringUtils.isEmpty(value)) {
            return;
        }
        try {
            redisTemplate.opsForValue().set(RedisUtil.FLAG, "1", 5, TimeUnit.SECONDS);
            Random a = new Random();
            int secods = a.nextInt(10)+5;
            int b = 1/0;
            System.out.println("定时任务CronJob2执行,执行时间:" + DateUtils.dateToString(new Date()) + ",休眠时间:" + secods);
            Thread.sleep(secods * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            redisTemplate.delete(RedisUtil.FLAG);
        }
    }
}
