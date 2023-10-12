package com.example.wantedpreonboardingbackend.domain.recruitment.service;

import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentCreateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentUpdateReq;

public interface RecruitmentService {
    void registerRecruitment(RecruitmentCreateReq dto);

    void updateRecruitment(Long id, RecruitmentUpdateReq dto);

    void deleteRecruitment(Long id);
}
