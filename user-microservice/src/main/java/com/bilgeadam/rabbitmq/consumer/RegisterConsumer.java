package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.RegisterModel;
import com.bilgeadam.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterConsumer {


    private final UserProfileService userProfileService;

    @RabbitListener(queues = "${rabbitmq.register-queue}")
    public void newUserCreate(RegisterModel model){
        log.info("User={}",model);
        userProfileService.createNewUserWithRabbitmq(model);
    }

}
