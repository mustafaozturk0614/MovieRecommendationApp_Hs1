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
    USERNAME_ALREADY_EXISTS(4113,"Kullanıcı adı zaten mevcut",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4114,"Kullanıcı bulunamadı",HttpStatus.NOT_FOUND),
    USER_AlREADY_ACTIVE(4115,"Kullanıcı zaten aktif",HttpStatus.BAD_REQUEST),
    INVALID_ACTIVATION_CODE(4116,"Gecersiz aktivasyon kodu",HttpStatus.BAD_REQUEST),



    ;

     private int code;
     private String message;
     private HttpStatus httpStatus;


}
