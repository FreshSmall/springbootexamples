package org.apache.myfaces.blank.controller;

import org.apache.myfaces.blank.entity.Message;
import org.apache.myfaces.blank.publisher.MessagePublisher;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

/**
 * @author: yinchao
 * @ClassName: MessageMutation
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/12 09:40
 */
@Controller
public class MessageMutation {

    private final MessagePublisher messagePublisher;

    public MessageMutation(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @MutationMapping
    public Message sendMessage(String content) {
        Message message = new Message(UUID.randomUUID().toString(), content);
        messagePublisher.publish(message);
        return message;
    }
}
