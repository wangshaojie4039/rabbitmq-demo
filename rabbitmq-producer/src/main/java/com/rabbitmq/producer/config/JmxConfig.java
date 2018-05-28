package com.rabbitmq.producer.config;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.impl.StandardMetricsCollector;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author wangshaojie
 * jmx相关配置类
 */

@Configuration
public class JmxConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private MetricRegistry registry;


    @PostConstruct
    public void init(){
        ConnectionFactory rabbitConnectionFactory=connectionFactory.getRabbitConnectionFactory();
        StandardMetricsCollector metricsCollector=new StandardMetricsCollector(registry);
        rabbitConnectionFactory.setMetricsCollector(metricsCollector);
        JmxReporter reporter= JmxReporter.forRegistry(registry).inDomain("com.rabbitmq.client").build();
        reporter.start();

    }

}
