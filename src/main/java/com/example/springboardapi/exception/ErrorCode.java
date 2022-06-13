package com.example.springboardapi.exception;


public enum ErrorCode {

    SUCCESS(200, "응답 성공"),
    INTERNER_SERVER_ERROR(500, "서버 오류, 관리자에 문의하세요."),
    NOT_FOUND(404, "정보를 찾을수 없습니다."),
    VALID_NOT_NULL(501 ,"%s 을(를) 확인해주세요."),
    LENGTH_ERROR(502 ,"%s 은(는) 200 글자를 넘을 수 없습니다."),
    DUPLICATE_ERROR(503 ,"%s 은(는) 중복된 값을 넣을 수 없습니다."),
    MAX_SIZE_ERROR(504 ,"%s 은(는) 5개 까지 입력 할 수 있습니다."),
    FILE_EMPTY(505 ,"파일이 존재하지 않습니다."),
    FILE_EXT_ERROR(506 ,"엑셀 파일이 아닙니다."),
    ;


    private final String message;
    private final int status;


    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}