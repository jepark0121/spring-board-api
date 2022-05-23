package com.example.springboardapi.config;


import com.example.springboardapi.exception.CommonResponse;
import com.example.springboardapi.exception.ErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@Log4j2
@RestControllerAdvice
public class ApiExceptionHandler extends RuntimeException{


    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<CommonResponse> NullPointException(Exception e) {
        log.error("Exception Type : {}", e.getClass().getSimpleName());
        log.error("Exception Message : {}", e.getMessage());

        return new ResponseEntity<CommonResponse>(
                new CommonResponse(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND.value())
                , HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> Exception(Exception e) {
        log.error("Exception Type : {}", e.getClass().getSimpleName());
        log.error("Exception Message : {}", e.getMessage());

        return new ResponseEntity<CommonResponse>(
                new CommonResponse(ErrorCode.INVALID_PARAMETER.getCode(), ErrorCode.INVALID_PARAMETER.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value())
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
