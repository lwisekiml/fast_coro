package com.uno.getinline.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Predicate;

@Getter
@RequiredArgsConstructor
public enum ErrorCode { // 우리 서비스에서 나오는 에러들을 다 한 곳에서 모아 정의

    OK(0, ErrorCategory.NORMAL, "Ok"),

    BAD_REQUEST(10000, ErrorCategory.CLIENT_SIDE, "Bad request"),
    SPRING_BAD_REQUEST(10001, ErrorCategory.CLIENT_SIDE, "Spring-detected bad request"),
    VALIDATION_ERROR(10002, ErrorCategory.CLIENT_SIDE, "Validation error"),
    NOT_FOUND(10003, ErrorCategory.CLIENT_SIDE, "Requested resource is not found"),

    INTERNAL_ERROR(20000, ErrorCategory.SERVER_SIDE, "Internal error"),
    SPRING_INTERNAL_ERROR(20001, ErrorCategory.SERVER_SIDE, "Spring-detected internal error"),
    DATA_ACCESS_ERROR(20002, ErrorCategory.SERVER_SIDE, "Data access error")
    ;

    private final Integer code;
    private final ErrorCategory errorCategory;
    private final String message;


    public String getMessage(Throwable e) { // 예외가 있을 경우에 예외를 받아서 그 메시지를 내보내게끔 되어있다.
        return this.getMessage(this.getMessage() + " - " + e.getMessage());
    }

    public String getMessage(String message) { // 사용자가 직접 입력할 경우
        return Optional.ofNullable(message)
                .filter(Predicate.not(String::isBlank))
                .orElse(this.getMessage());
    }

    public boolean isClientSideError() {
        return this.getErrorCategory() == ErrorCategory.CLIENT_SIDE;
    }

    public boolean isServerSideError() {
        return this.getErrorCategory() == ErrorCategory.SERVER_SIDE;
    }

    @Override
    public String toString() {
        return String.format("%s (%d)", this.name(), this.getCode());
    }


    public enum ErrorCategory {
        NORMAL, CLIENT_SIDE, SERVER_SIDE
    }

}
