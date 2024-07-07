package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_SERVER_ERROR(5300,"Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),

    BAD_REQUEST(4300,"Parametre Hatası",HttpStatus.BAD_REQUEST),

    UNEXPECTED_ERROR(4310,"Beklenmeyen Hata",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4311,"Kullanıcı bulunamadı",HttpStatus.NOT_FOUND),
    INVALID_TOKEN(4312,"Gecersiz token",HttpStatus.UNAUTHORIZED)
    ;
     private int code;
     private String message;
     private HttpStatus httpStatus;


}
