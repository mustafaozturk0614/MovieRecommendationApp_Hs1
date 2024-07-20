package com.bilgeadam.config.rabbitmq;


import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.register-queue}")
    private String registerQueue = "register-queue";

    Queue registerQueue() {
        return new Queue(registerQueue);
    }

}
