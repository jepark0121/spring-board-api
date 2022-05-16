package com.example.springboardapi;

import com.example.exception.CommonResponse;
import com.example.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice extends RuntimeException{


    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> internalServerException(Exception e) {
        CommonResponse errorResponse = new CommonResponse();
        errorResponse.setCode(ErrorCode.INVALID_PARAMETER.getCode());
        errorResponse.setMessage(ErrorCode.INVALID_PARAMETER.getMessage());
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<CommonResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<CommonResponse> Exception(Exception e) {
//        CommonResponse errorResponse = new CommonResponse();
//        errorResponse.setCode(ErrorCode.INVALID_PARAMETER.getCode());
//        errorResponse.setMessage(ErrorCode.INVALID_PARAMETER.getMessage());
//        errorResponse.setStatus(HttpStatus.);
//        return new ResponseEntity<CommonResponse>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public ResponseEntity<CommonResponse> notFoundException(Exception e) {
//        CommonResponse errorResponse = new CommonResponse();
//        errorResponse.setCode("C002");
//        errorResponse.setMessage(e.getMessage());
//        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(errorResponse);
//    }
}
