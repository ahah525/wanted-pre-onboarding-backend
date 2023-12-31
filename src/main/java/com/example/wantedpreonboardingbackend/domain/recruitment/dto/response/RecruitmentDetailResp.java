package com.example.wantedpreonboardingbackend.domain.recruitment.dto.response;

import com.example.wantedpreonboardingbackend.domain.company.domain.Company;
import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecruitmentDetailResp {
    private Long id;
    private String companyName;
    private String nation;
    private String region;
    private String position;
    private Integer compensation;
    private String content;
    private String stack;
    private List<Long> ids; // 회사가 올린 다른 채용공고 id 목록

    public static RecruitmentDetailResp of(Recruitment recruitment, List<Long> ids) {
        Company company = recruitment.getCompany();
        return RecruitmentDetailResp.builder()
                .id(recruitment.getId())
                .companyName(company.getName())
                .nation(company.getNation())
                .region(company.getRegion())
                .position(recruitment.getPosition())
                .compensation(recruitment.getCompensation())
                .content(recruitment.getContent())
                .stack(recruitment.getStack())
                .ids(ids)
                .build();
    }
}

