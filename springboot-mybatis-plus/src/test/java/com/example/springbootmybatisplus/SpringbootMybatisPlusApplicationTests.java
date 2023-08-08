package com.example.springbootmybatisplus;

import com.example.springbootmybatisplus.entity.PredictImportRecordEntity;
import com.example.springbootmybatisplus.mapper.PredictImportRecordMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMybatisPlusApplicationTests {

    @Autowired
    private PredictImportRecordMapper predictImportRecordMapper;

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

}
