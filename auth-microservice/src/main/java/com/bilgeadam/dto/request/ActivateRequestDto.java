package com.bilgeadam.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ActivateRequestDto {

    private long id;
    private String activationCode;
}
