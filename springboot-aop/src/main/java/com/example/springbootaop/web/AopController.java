package com.example.springbootaop.web;

import com.example.springbootaop.request.AopRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yinchao
 * @ClassName: AopController
 * @Description: aop 测试 controller
 * @team wuhan operational dev.
 * @date: 2022/9/22 09:33
 */
@RestController
public class AopController {

    @PostMapping("/test")
    public String testAop(@RequestBody AopRequest request){
        return request.getAddress();
    }
}
