package com.example.springboardapi.config;


import com.example.springboardapi.exception.CommonResponse;
import com.example.springboardapi.exception.ErrorCode;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestControllerAdvice
public class ApiExceptionHandler extends RuntimeException{

    private ResponseEntity<CommonResponse> customResponse(String message, int status, HttpStatus httpStatus) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setMessage(message);
        commonResponse.setStatus(status);

        return new ResponseEntity<CommonResponse>(commonResponse, httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> NullPointException(Exception e) {
        log.error("Exception Type : {}", e.getClass().getSimpleName());
        log.error("Exception Message : {}", e.getMessage());

        return customResponse(ErrorCode.INTERNER_SERVER_ERROR.getMessage()
                , HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // PathVariable Valid Check...
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<CommonResponse> badPathException(ConstraintViolationException e) {
               List<Map<String, String>> errors = new ArrayList<>();
        for(ConstraintViolation<?> error : e.getConstraintViolations()) {
            Map<String, String> map = new HashMap<>();
            map.put("fieldName", error.getPropertyPath().toString());
            map.put("errorMsg", error.getMessage());

            errors.add(map);
        }

        String fieldName = errors.stream().findFirst().get().get("fieldName");
        String errorMsg = ErrorCode.valueOf(errors.stream().findFirst().get().get("errorMsg")).getMessage();
        int errorCode = ErrorCode.valueOf(errors.stream().findFirst().get().get("errorMsg")).getStatus();

        return customResponse(String.format(errorMsg, fieldName), errorCode, HttpStatus.BAD_REQUEST);
    }

    // RequestBody Valid Check...
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> badValidException(MethodArgumentNotValidException e) {
        List<FieldError> errors = new ArrayList<FieldError>();
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()){
            log.error(fieldError.getField());
            log.error(fieldError.getCode());
            errors.add(fieldError);
        }

        String fieldName = errors.stream().findFirst().get().getField();
        String errorMsg = ErrorCode.valueOf(errors.stream().findFirst().get().getDefaultMessage()).getMessage();
        int errorCode = ErrorCode.valueOf(errors.stream().findFirst().get().getDefaultMessage()).getStatus();

        return customResponse(String.format(errorMsg, fieldName), errorCode, HttpStatus.BAD_REQUEST);
    }


}
