package com.bilgeadam.dto.request;

import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserProfileUpdateRequestDto {
    private String token;
    private String username;
    @Email
    private String email;
    private String name;
    private String surName;
    private String phone;
    private String address;
    private String avatar;
    private String about;
    private LocalDate birthDate;
}
