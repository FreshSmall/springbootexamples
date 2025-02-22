package com.example.springbootmybatisplus.controller;


import com.example.springbootmybatisplus.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/test")
    public int testTransaction() {
        return taskService.testTransaction();
    }

    @GetMapping("/update")
    public int testUpdate() throws InterruptedException {
        return taskService.testUpdate();
    }

    @GetMapping("/query")
    public int testQuery() {
        return taskService.testQuery();
    }
}
