package com.bilgeadam.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterModel implements Serializable {
    private Long authId;
    private String email;
    private String  username;
}
