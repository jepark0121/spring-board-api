package com.example.exception;

public enum ErrorCode {

    SUCCESS(200, "COO0", "응답 성공"),
    INVALID_PARAMETER(500, "COO1", "파라미터 오류, 관리자에 문의하세요."),
    NOT_FOUND(404, "C002", "정보를 찾을수 없습니다."),
    BAD_REQUEST(400, "C003", "잘못된 요청, 관리자에 문의하세요.");

    private final String code;
    private final String message;
    private final int status;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}