package com.example.wantedpreonboardingbackend.domain.recruitment.dto.request;

import com.example.wantedpreonboardingbackend.domain.company.domain.Company;
import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
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
public class RecruitmentCreateReq {
    @NotNull
    private Long companyId;
    @NotBlank
    private String position;
    @NotNull
    private Integer compensation;
    @NotBlank
    private String content;
    @NotBlank
    private String stack;

    public static Recruitment toEntity(Company company, RecruitmentCreateReq dto) {
        return Recruitment.builder()
                .company(company)
                .position(dto.getPosition())
                .compensation(dto.getCompensation())
                .content(dto.getContent())
                .stack(dto.getStack())
                .build();
    }
}
