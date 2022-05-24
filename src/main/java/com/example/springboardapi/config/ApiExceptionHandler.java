package com.example.springboardapi.config;


import com.example.springboardapi.exception.CommonResponse;
import com.example.springboardapi.exception.ErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@Log4j2
@RestControllerAdvice
public class ApiExceptionHandler extends RuntimeException{

    private ResponseEntity<CommonResponse> customResponse(String code, String message, int status, HttpStatus httpStatus) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode(code);
        commonResponse.setMessage(message);
        commonResponse.setStatus(status);

        return new ResponseEntity<CommonResponse>(commonResponse, httpStatus);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<CommonResponse> NullPointException(Exception e) {
        log.error("Exception Type : {}", e.getClass().getSimpleName());
        log.error("Exception Message : {}", e.getMessage());

        return customResponse(ErrorCode.NOT_FOUND.getCode(), ErrorCode.NOT_FOUND.getMessage()
                , HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({
            IllegalArgumentException.class, TypeMismatchException.class,
            ArrayIndexOutOfBoundsException.class, NumberFormatException.class
    })
    public ResponseEntity<CommonResponse> Exception(Exception e) {
        log.error("Exception Type : {}", e.getClass().getSimpleName());
        log.error("Exception Message : {}", e.getMessage());

        return customResponse(ErrorCode.INVALID_PARAMETER.getCode(), ErrorCode.INVALID_PARAMETER.getMessage()
                , HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
