package com.rabbitmq.producer.rabbit;

import com.rabbitmq.producer.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author wangshaojie
 */
@Component
@Slf4j
public class Sender implements RabbitTemplate.ConfirmCallback {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send() {
        String content = "hello-world1";
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY, content.getBytes(), correlationId);
        log.info("发送成功");
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String s) {
        log.info("回调id:" + correlationData);
        if (ack) {
            log.info("消息成功消费");
        } else {
            log.info("消息消费失败:" + s);
        }
    }
}