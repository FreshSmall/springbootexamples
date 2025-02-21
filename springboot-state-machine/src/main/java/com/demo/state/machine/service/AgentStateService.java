package com.demo.state.machine.service;

import com.demo.state.machine.dto.AgentEvents;
import com.demo.state.machine.dto.AgentStates;
import com.demo.state.machine.dto.StateDto;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author: yinchao
 * @ClassName: AgentStateService
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/21 14:58
 */
@Service
public class AgentStateService {


    @Resource
    private StateMachine<AgentStates, AgentEvents> agentStateMachine;

    private long id = 1L;

    private Map<Long, StateDto> stateMap = new HashMap<>();


    public StateDto pause() {
        StateDto stateDto = new StateDto();
        stateDto.setCno("1001");
        stateDto.setBusinessId("businessId");
        stateDto.setState(AgentStates.BUSY.getCode());
        stateMap.put(id, stateDto);
        if (!sendEvent(MessageBuilder.withPayload(AgentEvents.OUTCALL)
                .setHeader("stateDto", stateDto).build())) {
            System.out.println(" 坐席置忙失败，状态异常,坐席号：" + stateDto.getCno());
        }
        return stateDto;
    }

    public StateDto unPause() {
        StateDto stateDto = new StateDto();
        stateDto.setCno("1001");
        stateDto.setBusinessId("businessId");
        stateDto.setState(AgentStates.FREE.getCode());
        stateMap.put(id, stateDto);
        if (!sendEvent(MessageBuilder.withPayload(AgentEvents.FREE)
                .setHeader("stateDto", stateDto).build())) {
            System.out.println(" 坐席置闲失败，状态异常,坐席号：" + stateDto.getCno());
        }
        return stateDto;
    }

    public Map<Long, StateDto> getStates() {
        return stateMap;
    }

    /**
     * 发送状态转换事件
     *
     * @param message
     * @return
     */
    private synchronized boolean sendEvent(Message<AgentEvents> message) {
        boolean result = false;
        try {
            agentStateMachine.start();
            result = agentStateMachine.sendEvent(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (Objects.nonNull(message)) {
                StateDto stateDto = (StateDto) message.getHeaders().get("order");
                if (Objects.nonNull(stateDto) && Objects.equals(stateDto.getState(), AgentStates.READY)) {
                    agentStateMachine.stop();
                }
            }
        }
        return result;
    }

}
