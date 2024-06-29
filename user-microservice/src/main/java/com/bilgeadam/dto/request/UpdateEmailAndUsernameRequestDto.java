package com.bilgeadam.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateEmailAndUsernameRequestDto {
    private Long id;
    private String email;
    private String username;
}
