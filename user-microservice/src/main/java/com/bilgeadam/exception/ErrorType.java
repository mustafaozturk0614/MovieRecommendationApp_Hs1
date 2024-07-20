package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_SERVER_ERROR(5200,"Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),

    BAD_REQUEST(4200,"Parametre Hatası",HttpStatus.BAD_REQUEST),

    UNEXPECTED_ERROR(4210,"Beklenmeyen Hata",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4211,"Kullanıcı bulunamadı",HttpStatus.NOT_FOUND),
    INVALID_TOKEN(4212,"Gecersiz token",HttpStatus.UNAUTHORIZED),
    USER_NOT_CREATED(4213,"Kullanıcı olusturulamadi" , HttpStatus.BAD_REQUEST),;
     private int code;
     private String message;
     private HttpStatus httpStatus;


}
