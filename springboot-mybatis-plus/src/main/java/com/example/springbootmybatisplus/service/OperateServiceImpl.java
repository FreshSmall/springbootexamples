package com.example.springbootmybatisplus.service;

import com.example.springbootmybatisplus.entity.PredictImportRecordEntity;
import com.example.springbootmybatisplus.entity.PredictTaskLogEntity;
import com.example.springbootmybatisplus.mapper.PredictImportRecordMapper;
import com.example.springbootmybatisplus.mapper.PredictTaskLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OperateServiceImpl implements OperateService {

    @Autowired
    private PredictTaskLogMapper predictTaskLogMapper;

    @Autowired
    private PredictImportRecordMapper importRecordMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testSave() {

//        System.out.println("开始执行任务");
//        String sceneId = "c2ac";
//        Long taskId = 28L;
//        String taskName = "测试任务名称";

        /*PredictImportRecordEntity entity = new PredictImportRecordEntity();
        String uData = UUID.randomUUID().toString();
        entity.setSceneId(sceneId);
        entity.setPredictTaskId(0L);
        entity.setUData(uData);
        importRecordMapper.insert(entity);*/
//        save();

        String sceneId = "c2ac";
        Long taskId = -27L;
        String taskName = "测试任务名称";

        PredictImportRecordEntity entity = new PredictImportRecordEntity();
        String uData = UUID.randomUUID().toString();
        entity.setSceneId(sceneId);
        entity.setPredictTaskId(0L);
        entity.setUData(uData);
        importRecordMapper.insert(entity);
        // 保存预测式外呼任务
        PredictTaskLogEntity predictTask = new PredictTaskLogEntity();
        predictTask.setId(279L);
//        predictTask.setSceneId(sceneId);
//        predictTask.setTaskId(taskId);
//        predictTask.setTaskName(taskName);
        predictTask.setTaskId(-2008L);
//        predictTaskLogMapper.insert(predictTask);
        predictTaskLogMapper.updateById(predictTask);

        int a = 1/0;

    }
    @Transactional(rollbackFor = Exception.class)
    public void save() {
        String sceneId = "c2ac";
        Long taskId = -27L;
        String taskName = "测试任务名称";

        PredictImportRecordEntity entity = new PredictImportRecordEntity();
        String uData = UUID.randomUUID().toString();
        entity.setSceneId(sceneId);
        entity.setPredictTaskId(0L);
        entity.setUData(uData);
        importRecordMapper.insert(entity);
        // 保存预测式外呼任务
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        PredictTaskLogEntity predictTask = new PredictTaskLogEntity();
        predictTask.setSceneId(sceneId);
        predictTask.setTaskId(taskId);
        predictTask.setTaskName(taskName);
        predictTaskLogMapper.insert(predictTask);
        int a = 1/0;
    }
}
