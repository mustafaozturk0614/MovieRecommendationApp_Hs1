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
    DATA_INTEGRITY_VIOLATION(4211,"Veri Butunlüğü ihlali: Girdi verileri eksik veya geçersiz" ,HttpStatus.BAD_REQUEST),

    ;
     private int code;
     private String message;
     private HttpStatus httpStatus;


}
