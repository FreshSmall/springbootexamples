package com.demo.state.machine.listener;

import com.demo.state.machine.dto.AgentStates;
import com.demo.state.machine.dto.StateDto;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: yinchao
 * @ClassName: AgentStatesListener
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/21 14:48
 */
@Component
@WithStateMachine
@Transactional
public class AgentStatesListener {

    @OnTransition(source = "FREE", target = "BUSY")
    public boolean pause(Message message) {
        StateDto order = (StateDto) message.getHeaders().get("stateDto");
        order.setState(AgentStates.BUSY.getCode());
        System.out.println("坐席置忙，状态机反馈信息：" + message.getHeaders().toString());
        return true;
    }

    @OnTransition(source = "BUSY", target = "FREE")
    public boolean unPause(Message message) {
        StateDto order = (StateDto) message.getHeaders().get("stateDto");
        order.setState(AgentStates.FREE.getCode());
        System.out.println("坐席置闲，状态机反馈信息：" + message.getHeaders().toString());
        return true;
    }
}
