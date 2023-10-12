package com.example.wantedpreonboardingbackend.domain.recruitment.domain;

import com.example.wantedpreonboardingbackend.domain.company.domain.Company;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentUpdateReq;
import com.example.wantedpreonboardingbackend.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recruitment extends BaseEntity {
    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private Integer compensation;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String stack;

    @ManyToOne
    private Company company;

    public void updateInfo(RecruitmentUpdateReq dto) {
        this.position = dto.getPosition();
        this.compensation = dto.getCompensation();
        this.content = dto.getContent();
        this.stack = dto.getStack();
    }
}
