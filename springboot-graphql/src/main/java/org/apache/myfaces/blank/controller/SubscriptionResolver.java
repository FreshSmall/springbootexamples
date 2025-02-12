package org.apache.myfaces.blank.controller;

import org.apache.myfaces.blank.entity.Message;
import org.apache.myfaces.blank.publisher.MessagePublisher;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

/**
 * @author: yinchao
 * @ClassName: SubscriptionResolver
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/12 09:39
 */
@Controller
public class SubscriptionResolver {

    private final MessagePublisher messagePublisher;

    public SubscriptionResolver(MessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @SubscriptionMapping
    public Flux<Message> newMessage() {
        return messagePublisher.getMessages();
    }
}
