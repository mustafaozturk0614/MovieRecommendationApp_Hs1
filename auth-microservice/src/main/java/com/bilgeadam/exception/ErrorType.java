package com.bilgeadam.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_SERVER_ERROR(5100,"Sunucu Hatası", HttpStatus.INTERNAL_SERVER_ERROR),

    BAD_REQUEST(4100,"Parametre Hatası",HttpStatus.BAD_REQUEST),

    UNEXPECTED_ERROR(4110,"Beklenmeyen Hata",HttpStatus.BAD_REQUEST),
    DATA_INTEGRITY_VIOLATION(4111,"Veri Butunlüğü ihlali: Girdi verileri eksik veya geçersiz" ,HttpStatus.BAD_REQUEST),
    LOGIN_ERROR(4112,"Kullancı adı veye Şifre Hatalı !!!",HttpStatus.UNAUTHORIZED),




    ;

     private int code;
     private String message;
     private HttpStatus httpStatus;


}