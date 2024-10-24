package com.example.springbootmybatisplus;

import com.example.springbootmybatisplus.entity.PredictImportRecordEntity;
import com.example.springbootmybatisplus.mapper.PredictImportRecordMapper;
import com.example.springbootmybatisplus.service.TaskService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
class SpringbootMybatisPlusApplicationTests {

    @Autowired
    private PredictImportRecordMapper predictImportRecordMapper;

    @Autowired
    private TaskService taskService;

    @Test
    public void contextLoads() {
        PredictImportRecordEntity predictImportRecordEntity = predictImportRecordMapper.selectById(46);
        Assert.assertNotNull(predictImportRecordEntity);
    }

    @Test
    public void update() {
        PredictImportRecordEntity entity = new PredictImportRecordEntity();
        entity.setId(46L);
        entity.setUData("1234567");
        int update = predictImportRecordMapper.updateById(entity);
        Assert.assertNotNull(update);
    }

    @Test
    public void query() {
        final PredictImportRecordEntity entity = predictImportRecordMapper.selectById("46");
        Assertions.assertNotNull(entity);
    }

    @Test
    public void testUpdateTransaction() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    final int count = taskService.testUpdate();
                    System.out.println("线程" + Thread.currentThread().getName() + ",执行完毕，count=" + count);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("测试方法执行完毕");
    }


}
