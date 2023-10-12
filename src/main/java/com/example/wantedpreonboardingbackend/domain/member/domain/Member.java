package com.example.wantedpreonboardingbackend.domain.member.domain;

import com.example.wantedpreonboardingbackend.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Member extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;
}
