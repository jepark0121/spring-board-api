package com.example.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonResponse {
    private String code = ErrorCode.SUCCESS.getCode();
    private String message = ErrorCode.SUCCESS.getMessage();
    private int status = ErrorCode.SUCCESS.getStatus();
    private Object data;

    public CommonResponse(ErrorCode code, Object data) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.status = code.getStatus();
        this.data = data;
    }

    public CommonResponse(Object data) {
        this.data = data;
    }

}
