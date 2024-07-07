package com.bilgeadam.dto.response;

import com.bilgeadam.entity.enums.EStatus;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserProfileFindAllResponseDto {
    private String id;
    private Long authId;
    private String username;
    private String email;
    private String phone;
    private String address;
    private String avatar;
    private String about;
    private String name;
    private String surName;
    private LocalDate birthDate;
    private EStatus status;

}
