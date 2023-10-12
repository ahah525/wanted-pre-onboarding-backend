package com.example.wantedpreonboardingbackend.domain.recruitment.service;

import com.example.wantedpreonboardingbackend.domain.recruitment.domain.Recruitment;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentCreateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentUpdateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.response.RecruitmentDetailResp;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.response.RecruitmentResp;

import java.util.List;

public interface RecruitmentService {
    void registerRecruitment(RecruitmentCreateReq dto);

    void updateRecruitment(Long id, RecruitmentUpdateReq dto);

    void deleteRecruitment(Long id);

    List<RecruitmentResp> getAllRecruitment();

    RecruitmentDetailResp getRecruitmentDetail(Long id);

    Recruitment findById(Long id);
}
