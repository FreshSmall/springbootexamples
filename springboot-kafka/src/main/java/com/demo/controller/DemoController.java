package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yinchao
 * @ClassName: DemoController
 * @Description:
 * @team wuhan operational dev.
 * @date: 2024/8/22 09:23
 */
@RestController
public class DemoController {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "kafka-test-topic-name";

    @GetMapping("/publish/{message}")
    public String publishMessage(@PathVariable("message") final String message) {
        // Sending the message
        kafkaTemplate.send(TOPIC, message);
        return "Published Successfully";
    }
}
