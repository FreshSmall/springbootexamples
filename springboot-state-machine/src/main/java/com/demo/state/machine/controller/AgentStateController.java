package com.demo.state.machine.controller;

import com.demo.state.machine.service.AgentStateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: yinchao
 * @ClassName: AgentStateController
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/21 14:58
 */
@RestController
public class AgentStateController {

    @Resource
    private AgentStateService agentStateService;

    @RequestMapping("/testStateChange")
    public String testOrderStatusChange(){
        agentStateService.pause();
        agentStateService.unPause();
        System.out.println("全部订单状态：" + agentStateService.getStates());
        return "success";
    }
}
