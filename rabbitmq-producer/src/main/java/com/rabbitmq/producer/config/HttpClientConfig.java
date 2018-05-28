package com.rabbitmq.producer.config;

import com.rabbitmq.http.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * @author wangshaojie
 *
 * 连接rabbitmq http client 配置类
 */
@Configuration
public class HttpClientConfig {


    @Bean(name = "rabbitmqHttpClient")
    public Client client() throws MalformedURLException, URISyntaxException {
        Client client=new Client("http://10.154.0.59:15672/api/","wangshaojie","wang25");
        return client;
    }
}
