package com.example.wantedpreonboardingbackend.global.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 비즈니스 로직 실행 중 오류 발생
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorResponse errorResponse = ErrorResponse.of(e.getErrorCode().getErrorCode(), e.getMessage());
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(errorResponse);
    }
}
