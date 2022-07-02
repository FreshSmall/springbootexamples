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
public class OperateServiceImpl implements OperateService{

    @Autowired
    private PredictTaskLogMapper predictTaskLogMapper;

    @Autowired
    private PredictImportRecordMapper importRecordMapper;

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void testSave() {
        String sceneId = "c2ac";
        Long taskId = 27L;
        String taskName = "测试任务名称";

        /*PredictImportRecordEntity entity = new PredictImportRecordEntity();
        String uData = UUID.randomUUID().toString();
        entity.setSceneId(sceneId);
        entity.setPredictTaskId(0L);
        entity.setUData(uData);
        importRecordMapper.insert(entity);*/
        save();

        // 保存预测式外呼任务
        PredictTaskLogEntity predictTask = new PredictTaskLogEntity();
        predictTask.setSceneId(sceneId);
        predictTask.setTaskId(taskId);
        predictTask.setTaskName(taskName);
        predictTaskLogMapper.insert(predictTask);
    }

    public void save(){
        String sceneId = "c2ac";
        Long taskId = 27L;
        String taskName = "测试任务名称";

        PredictImportRecordEntity entity = new PredictImportRecordEntity();
        String uData = UUID.randomUUID().toString();
        entity.setSceneId(sceneId);
        entity.setPredictTaskId(0L);
        entity.setUData(uData);
        importRecordMapper.insert(entity);
    }
}
