package com.example.wantedpreonboardingbackend.domain.recruitment.domain;

import com.example.wantedpreonboardingbackend.domain.company.domain.Company;
import com.example.wantedpreonboardingbackend.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

@Entity
@Builder
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
}
