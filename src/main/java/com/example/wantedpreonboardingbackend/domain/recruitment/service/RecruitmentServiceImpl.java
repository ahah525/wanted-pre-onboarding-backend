package com.example.wantedpreonboardingbackend.domain.recruitment.service;

import com.example.wantedpreonboardingbackend.domain.company.domain.Company;
import com.example.wantedpreonboardingbackend.domain.company.service.CompanyService;
import com.example.wantedpreonboardingbackend.domain.recruitment.dto.request.RecruitmentCreateReq;
import com.example.wantedpreonboardingbackend.domain.recruitment.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruitmentServiceImpl implements RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyService companyService;

    public void registerRecruitment(RecruitmentCreateReq dto) {
        Company company = companyService.findById(dto.getCompanyId());
        recruitmentRepository.save(RecruitmentCreateReq.toEntity(company, dto));
    }
}
