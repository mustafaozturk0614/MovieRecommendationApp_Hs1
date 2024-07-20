package com.bilgeadam.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterResponseDto {
    private String token;
    private Long id;
    private String activationCode;
    private String username;
}
