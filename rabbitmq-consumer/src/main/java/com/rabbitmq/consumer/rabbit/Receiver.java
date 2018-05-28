package com.rabbitmq.consumer.rabbit;

import com.rabbitmq.consumer.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangshaojie
 * 消息接收者
 */
@Component
@Slf4j
public class Receiver {

    @RabbitListener(queues = "spring-boot-queue",containerFactory = "messageContainer")
    public void process(User user) {
        log.info("Receiver : " + "id"+user.getId()+"name"+user.getName()+"age"+user.getAge()+"address"+user.getAddress());
    }
}
