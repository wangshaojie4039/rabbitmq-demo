package com.rabbitmq.producer.config;

import com.codahale.metrics.MetricRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricConfig {

    @Bean
    public MetricRegistry metricRegistry(){

        return new MetricRegistry();
    }


}
