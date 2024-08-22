package com.demo.consume;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author: yinchao
 * @ClassName: KafKaConsumer
 * @Description:
 * @team wuhan operational dev.
 * @date: 2024/8/19 15:55
 */
@Service
public class KafKaConsumer {

    @KafkaListener(groupId = "duke-databus-group-test", topics = "kafka-test-topic-name")
    public void consume(String message){
        System.out.printf("Message received -> %s%n", message);
    }
}
