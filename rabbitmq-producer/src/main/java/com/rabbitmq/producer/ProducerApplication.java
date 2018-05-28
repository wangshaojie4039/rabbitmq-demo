package com.rabbitmq.producer;

import com.rabbitmq.producer.service.task.PressureTestTask;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executor;

@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Autowired
	@Qualifier("taskAsyncPool")
	private Executor taskAsyncPool;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void run(String... args) {
		for(int i=0;i<1000;i++){
			taskAsyncPool.execute(new PressureTestTask(100000,rabbitTemplate));
		}
	}
}

