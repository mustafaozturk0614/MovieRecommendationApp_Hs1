package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.RegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterProducer {

    private final RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.auth-exchange}")
    private String exchange ;

    @Value("${rabbitmq.register-binding-key}")
    private String registerBindingKey ;

    public  void sendNewUser(RegisterModel model){
        rabbitTemplate.convertAndSend(exchange,registerBindingKey,model);
    }




}
