package com.example.wantedpreonboardingbackend.domain.recruitment.dto.response;

import com.example.wantedpreonboardingbackend.domain.company.domain.Company;
import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentResp {
    private Long id;
    private String companyName;
    private String nation;
    private String region;
    private String position;
    private Integer compensation;
    private String stack;

    public static RecruitmentResp of(Recruitment recruitment) {
        Company company = recruitment.getCompany();
        return RecruitmentResp.builder()
                .id(recruitment.getId())
                .companyName(company.getName())
                .nation(company.getNation())
                .region(company.getRegion())
                .position(recruitment.getPosition())
                .compensation(recruitment.getCompensation())
                .stack(recruitment.getStack())
                .build();
    }

}

