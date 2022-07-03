package com.example.springbootmybatisplus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

@Service
public class TaskService {

    @Autowired
    private OperateService operateService;

    private static ExecutorService service = Executors.newFixedThreadPool(1);

    public int testTransaction() {
        /*service.submit(new Runnable() {
            @Override
            public void run() {
                operateService.testSave();
            }
        });*/
        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                operateService.testSave();
            }
        },service).exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable throwable) {
                System.out.println("出现异常12");
                return null;
            }
        });
        return 1;
    }
}
