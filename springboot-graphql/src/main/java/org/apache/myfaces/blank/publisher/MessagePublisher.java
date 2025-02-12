package org.apache.myfaces.blank.publisher;

import org.apache.myfaces.blank.entity.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * @author: yinchao
 * @ClassName: MessagePublisher
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/12 09:38
 */
@Service
public class MessagePublisher {

    // 使用 Sinks 创建多播流（支持背压）
    private final Sinks.Many<Message> sink = Sinks.many().multicast().onBackpressureBuffer();

    // 将 Sink 转换为 Flux
    public Flux<Message> getMessages() {
        return sink.asFlux();
    }

    // 发布新消息的方法
    public void publish(Message message) {
        sink.tryEmitNext(message);
    }
}
