package com.bilgeadam.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserProfileSaveRequestDto {
    private String id;
    private Long authId;
    private String username;
    private String email;

}
