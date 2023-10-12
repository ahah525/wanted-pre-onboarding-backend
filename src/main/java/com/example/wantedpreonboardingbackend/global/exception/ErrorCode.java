package com.example.wantedpreonboardingbackend.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "100", "해당 회사가 존재하지 않습니다."),
    RECRUITMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "200", "해당 채용공고가 존재하지 않습니다.");

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
};
