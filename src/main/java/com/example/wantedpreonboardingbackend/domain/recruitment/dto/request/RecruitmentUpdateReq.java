package com.example.wantedpreonboardingbackend.domain.recruitment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentUpdateReq {
    @NotBlank
    private String position;
    @NotNull
    private Integer compensation;
    @NotBlank
    private String content;
    @NotBlank
    private String stack;
}
