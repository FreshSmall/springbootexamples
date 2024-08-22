package com.example.controller;

import com.example.dto.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yinchao
 * @ClassName: StudentController
 * @Description:
 * @team wuhan operational dev.
 * @date: 2024/7/31 11:41
 */
@RestController
public class StudentController {


    @PostMapping(value = {"/test"})
    public String test(@RequestBody Student student){
        return "success";
    }
}
