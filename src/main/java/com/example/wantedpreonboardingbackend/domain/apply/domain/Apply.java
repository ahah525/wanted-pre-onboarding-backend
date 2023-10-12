package com.example.wantedpreonboardingbackend.domain.apply.domain;

import com.example.wantedpreonboardingbackend.domain.member.domain.Member;
import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Apply extends BaseEntity {
    @ManyToOne
    private Member member;

    @ManyToOne
    private Recruitment recruitment;
}
