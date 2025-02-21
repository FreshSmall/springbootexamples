package com.demo.state.machine.config;

import com.demo.state.machine.dto.AgentEvents;
import com.demo.state.machine.dto.AgentStates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

/**
 * @author: yinchao
 * @ClassName: StateMachineConfig
 * @Description:
 * @team wuhan operational dev.
 * @date: 2024/4/13 18:24
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<AgentStates, AgentEvents> {

    @Override
    public void configure(StateMachineStateConfigurer<AgentStates, AgentEvents> states)
            throws Exception {
        states
                .withStates()
                .initial(AgentStates.FREE)
                .end(AgentStates.READY)
                .states(EnumSet.allOf(AgentStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<AgentStates, AgentEvents> transitions)
            throws Exception {
        transitions
                .withExternal().source(AgentStates.FREE).target(AgentStates.BUSY).event(AgentEvents.BUSY)
                .and()
                .withExternal().source(AgentStates.BUSY).target(AgentStates.FREE).event(AgentEvents.FREE)
                .and()
                .withExternal().source(AgentStates.FREE).target(AgentStates.CALLING).event(AgentEvents.OUTCALL)
                .and()
                .withExternal().source(AgentStates.CALLING).target(AgentStates.RINGING).event(AgentEvents.RINGING)
                .and()
                .withExternal().source(AgentStates.RINGING).target(AgentStates.TWOWAYCHAT).event(AgentEvents.TWOWAYCHAT);
    }
}
