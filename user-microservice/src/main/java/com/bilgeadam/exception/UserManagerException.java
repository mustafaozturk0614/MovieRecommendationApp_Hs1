package com.bilgeadam.exception;

import lombok.Getter;

@Getter
public class UserManagerException extends RuntimeException{

    private ErrorType errorType;

    public UserManagerException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType=errorType;
    }


    public UserManagerException(ErrorType errorType, String customMessage){
        super(customMessage);
        this.errorType=errorType;
    }

}
