package com.example.springbootmybatisplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TaskService {

    @Autowired
    private OperateService operateService;

    private static ExecutorService service = Executors.newFixedThreadPool(1);

    @Transactional
    public int testTransaction() {
        service.execute(new Runnable() {
            @Override
            public void run() {
                operateService.testSave();
            }
        });
        return 1;
    }
}
