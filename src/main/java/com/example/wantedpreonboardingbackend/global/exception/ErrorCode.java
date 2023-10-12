package com.example.wantedpreonboardingbackend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ;
    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
};
