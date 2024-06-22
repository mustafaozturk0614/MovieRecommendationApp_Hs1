package com.bilgeadam.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequestDto {
    @NotBlank(message = "Kullanıcı adı boş geçilemez!!!")
    @Size(min = 2,max=32, message = "Kullanıcı adı uzunlugu en az 2 karakter en fazla 32 karakter olabilir !!!")
    private String username;

    @NotBlank(message = "Şifre adı boş geçilemez!!!")
    @Size(min = 5,max=32, message = "Şifre uzunlugu en az 5 karakter en fazla 32 karakter olabilir !!!")
  //  @Pattern(regexp = "^.*(?=.{5,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$")
    private String password;

    @Email
    private String email;
}
