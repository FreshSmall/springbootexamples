package com.example.springbootmybatisplus.controller;


import com.example.springbootmybatisplus.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/test")
    public int testTransaction(){
        return taskService.testTransaction();
    }
}
