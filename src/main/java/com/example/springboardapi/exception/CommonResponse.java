package com.example.springboardapi.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResponse {
    private String code = ErrorCode.SUCCESS.getCode();
    private String message = ErrorCode.SUCCESS.getMessage();
    private int status = ErrorCode.SUCCESS.getStatus();
    private Object data;


    public CommonResponse(String code, String message, int status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }


    public CommonResponse(String code, String message, int status, Object data) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public CommonResponse(Object data) {
        this.data = data;
    }

}
