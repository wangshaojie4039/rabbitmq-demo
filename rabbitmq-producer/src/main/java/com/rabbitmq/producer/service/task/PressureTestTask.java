package com.rabbitmq.producer.service.task;

import com.rabbitmq.producer.config.RabbitConfig;
import com.rabbitmq.producer.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Random;
import java.util.UUID;

/**
 * @author 压力测试任务类
 */
public class PressureTestTask implements Runnable {

    private Logger logger = LoggerFactory.getLogger(PressureTestTask.class);

    private int maxCount;

    private RabbitTemplate rabbitTemplate;

    public PressureTestTask(int maxCount, RabbitTemplate rabbitTemplate) {
        this.maxCount = maxCount;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Random random = new Random();

    @Override
    public void run() {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setContentEncoding("application/json;charset=utf8");
        for (int i = 0; i < maxCount; i++) {
//            CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
//            String content = "message_ext" + correlationId;
//            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY, content.getBytes(), correlationId);
            rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_NAME, RabbitConfig.ROUTING_KEY, buildUser());
            logger.info("发送成功");
        }
    }

    private User buildUser() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("王少杰");
        user.setAge(26);
        user.setAddress("江苏省苏州市");
        return user;
    }


}
