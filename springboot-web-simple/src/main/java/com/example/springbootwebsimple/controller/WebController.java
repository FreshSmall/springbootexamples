package com.example.springbootwebsimple.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yinchao
 * @ClassName: WebController
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/8/8 21:11
 */
@RestController
public class WebController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world!";
    }
}
