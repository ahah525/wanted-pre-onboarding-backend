package com.example.wantedpreonboardingbackend.domain.recruitment.service;

import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentCreateReq;

public interface RecruitmentService {
    void registerRecruitment(RecruitmentCreateReq dto);
}
