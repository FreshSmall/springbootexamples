package com.example.springboot.actuator.controller;

import com.example.springboot.actuator.dto.Student;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *  IndexController
 * </pre>
 *
 * <pre>
 * @author mazq
 * 修改记录
 *    修改后版本:     修改人：  修改日期: 2020/07/23 17:53  修改内容:
 * </pre>
 */
@RestController
public class IndexController {

    @RequestMapping(value = {"/test"})
    public String test(){
        return "success";
    }

    @PostMapping(value = {"/test2"})
    public Student test2(@RequestBody Student student) throws InterruptedException {
        final Gson gson = new Gson();
        final String json = gson.toJson(student);
        System.out.println(json);
        return gson.fromJson(json, Student.class);
    }
}
