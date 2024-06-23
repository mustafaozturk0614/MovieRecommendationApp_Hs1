package com.bilgeadam.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {



    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessage> handleRunTimeException(RuntimeException e){
        e.printStackTrace();
        log.error("hata mesajı: "+e.getMessage());
        ErrorMessage errorMessage=creteErrorMessage(ErrorType.UNEXPECTED_ERROR,e);
        return  new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserManagerException.class)
    public ResponseEntity<ErrorMessage> handleMovieAppException(UserManagerException e){
        ErrorType errorType=e.getErrorType();
        HttpStatus httpStatus=errorType.getHttpStatus();
        ErrorMessage errorMessage=creteErrorMessage(errorType,e);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
    ErrorType errorType=ErrorType.BAD_REQUEST;
        List<String> fileds=new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e->fileds.add(e.getField()+": "+e.getDefaultMessage()));
        ErrorMessage errorMessage=creteErrorMessage(errorType,ex);
        errorMessage.setFields(fileds);
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        ErrorType errorType=ErrorType.DATA_INTEGRITY_VIOLATION;
        ErrorMessage errorMessage=creteErrorMessage(errorType,e);
        errorMessage.setMessage(errorType.getMessage()+"==>"+e.getCause().getMessage());
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ErrorMessage> handleMessageNotReadableException(
            HttpMessageNotReadableException exception) {
        ErrorType errorType = ErrorType.BAD_REQUEST;
        return new ResponseEntity<>(creteErrorMessage(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public final ResponseEntity<ErrorMessage> handleInvalidFormatException(
            InvalidFormatException exception) {
        ErrorType errorType = ErrorType.BAD_REQUEST;
        return new ResponseEntity<>(creteErrorMessage(errorType, exception), errorType.getHttpStatus());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MethodArgumentTypeMismatchException exception) {

        ErrorType errorType = ErrorType.BAD_REQUEST;
        return new ResponseEntity<>(creteErrorMessage(errorType, exception), errorType.getHttpStatus());
    }

    @ExceptionHandler(MissingPathVariableException.class)
    public final ResponseEntity<ErrorMessage> handleMethodArgumentMisMatchException(
            MissingPathVariableException exception) {
        ErrorType errorType = ErrorType.BAD_REQUEST;
        return new ResponseEntity<>(creteErrorMessage(errorType, exception), errorType.getHttpStatus());
    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorMessage> handleAllExceptions(Exception exception) {
        ErrorType errorType = ErrorType.INTERNAL_SERVER_ERROR;
        List<String> fields = new ArrayList<>();
        fields.add(exception.getMessage());
        ErrorMessage errorMessage = creteErrorMessage(errorType, exception);
        errorMessage.setFields(fields);
        return new ResponseEntity<>(errorMessage, errorType.getHttpStatus());
    }


    private ErrorMessage creteErrorMessage(ErrorType errorType,Exception e){
        log.error("Hata oluştu: "+ e.getMessage());
        return ErrorMessage.builder()
                .code(errorType.getCode())
                .message(e.getMessage())
                .build();

    }
}
