package com.rabbitmq.consumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author wangshaojie
 * rabbitmq相关配置
 */
@Configuration
@Slf4j
public class RabbitConfig {



//    public static final String EXCHANGE_NAME = "spring-boot-exchange";
//
//    public static final String ROUTING_KEY = "spring-boot-routingKey";
//
//    public static final String QUEUE_NAME="spring-boot-queue";
//    public static final String EXCHANGE_NAME = "spring-boot-exchange";
//
//    public static final String ROUTING_KEY = "spring-boot-routingKey";
//
//    public static final String QUEUE_NAME="spring-boot-queue";
//
    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses("10.154.0.74:5672");
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root123");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true); //必须要设置
        return connectionFactory;
    }
//@Bean
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//public RabbitTemplate rabbitTemplate() {
//    RabbitTemplate template = new RabbitTemplate(connectionFactory());
//    return template;
//}
//
//    @Bean
//    public DirectExchange defaultExchange() {
//        return new DirectExchange(EXCHANGE_NAME);
//    }
//
//    @Bean
//    public Queue queue() {
//        return new Queue(QUEUE_NAME, true); //队列持久
//    }
//
//    @Bean
//    public SimpleMessageListenerContainer messageContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
//        container.setQueues(queue());
//        container.setExposeListenerChannel(true);
//        container.setMaxConcurrentConsumers(100);
//        container.setConcurrentConsumers(100);
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
//        container.setPrefetchCount(10);
//        container.setMessageListener(new ChannelAwareMessageListener() {
//
//            @Override
//            public void onMessage(Message message, Channel channel) throws Exception {
//                byte[] body = message.getBody();
////                    String result = new String(body).trim();
////                    long timeCount = System.currentTimeMillis() - Long.valueOf(result);
////                    log.info("time count : " + timeCount);
//                log.info("receive msg : " + new String(body));
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
//            }
//        });
//        return container;
//
//    }
    @Bean
    public SimpleRabbitListenerContainerFactory messageContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
}
