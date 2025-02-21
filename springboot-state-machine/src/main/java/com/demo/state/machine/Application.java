package com.demo.state.machine;

import com.demo.state.machine.dto.AgentEvents;
import com.demo.state.machine.dto.AgentStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

/**
 * @author: yinchao
 * @ClassName: Application
 * @Description:
 * @team wuhan operational dev.
 * @date: 2024/4/13 18:12
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
